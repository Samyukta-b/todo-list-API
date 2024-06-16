/* Concurrent Data Fetcher in Go: Fetching Data Concurrently from APIs */
package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
	"sync"
	"time"
)

type Todo struct {
	TaskName  string `json:"taskName"`
	Completed bool   `json:"completed"`
}

type Request struct {
	Method   string
	Endpoint string
	Data     interface{}
}

func fetchData(req Request, wg *sync.WaitGroup, ch chan<- string) {
	defer wg.Done()

	var resp *http.Response
	var err error

	client := &http.Client{
		Timeout: 4 * time.Second,
	}

	if req.Method == "POST" { // POST
		jsonData, err := json.Marshal(req.Data)
		if err != nil {
			ch <- fmt.Sprintf("error %s: %v", req.Endpoint, err)
			return
		}
		resp, err = client.Post(req.Endpoint, "application/json", bytes.NewBuffer(jsonData))
	} else {
		resp, err = client.Get(req.Endpoint) // GET
	}

	if err != nil {
		ch <- fmt.Sprintf("error %s: %v", req.Endpoint, err)
		return
	}
	defer resp.Body.Close()

	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		ch <- fmt.Sprintf("error %s: %v", req.Endpoint, err)
		return
	}

	ch <- fmt.Sprintf("\nResponse from '%s' %s:\n\n %s\n", req.Method, req.Endpoint, body)
	time.Sleep(500 * time.Millisecond)
}

func main() {

	//t := true
	endpoints := []Request{
		{Method: "GET", Endpoint: "http://localhost:8090/cloudvendor/1"},
		{Method: "GET", Endpoint: "http://localhost:8090/cloudvendor/todo/1/getTodo"},
		{Method: "POST", Endpoint: "http://localhost:8090/cloudvendor/addVendor", Data: map[string]string{
			"vendorName":  "Roman Cali",
			"vendorEmail": "romanc@gmail.com",
		}},

		{Method: "POST", Endpoint: "http://localhost:8090/cloudvendor/todo/2/addTodo", Data: Todo{
			TaskName:  "Finish shopping clothes",
			Completed: true,
		}},
	}

	numRequests := len(endpoints)
	ch := make(chan string, numRequests)

	var wg sync.WaitGroup
	wg.Add(numRequests)

	for i := 0; i < numRequests; i++ {
		go fetchData(endpoints[i], &wg, ch)
	}

	go func() {
		wg.Wait()
		close(ch)
	}()

	for result := range ch {
		fmt.Println(result)
	}

	fmt.Println("Main has Ended!")
}
