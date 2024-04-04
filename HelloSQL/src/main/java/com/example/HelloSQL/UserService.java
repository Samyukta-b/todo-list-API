package com.example.HelloSQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public Iterable<UserModel> getAll() {
        return repository.findAll();
    }

    public void addUser(String name, String email) {
        UserModel model = new UserModel();
        model.setName(name);
        model.setEmail(email);
        repository.save(model);
    }

    public void updateUser(int id, String name, String email) {
        Optional<UserModel> query = repository.findById(Integer.valueOf(id));
        if (query.isPresent()) {
            UserModel user = query.get();
            user.setName(name);
            user.setEmail(email);
            repository.save(user);
        }
    }

    public void delete(int id) {
        Optional<UserModel> query = repository.findById(Integer.valueOf(id));
        UserModel user = query.get();
        repository.delete(user);
    }
}
