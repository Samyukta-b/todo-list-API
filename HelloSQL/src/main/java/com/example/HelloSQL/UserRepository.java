package com.example.HelloSQL;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends
        CrudRepository<UserModel, Integer> {

}
