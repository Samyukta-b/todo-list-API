package com.example.HelloSQL;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CloudVModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendorId;
    private String vendorName;
    private String vendorEmail;
    @OneToMany(mappedBy = "vendorId", cascade = CascadeType.ALL)
    private List<TodoItemModel> todoList;

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public List<TodoItemModel> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<TodoItemModel> todoList) {
        this.todoList = todoList;
    }
}
