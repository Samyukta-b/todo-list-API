package com.example.HelloSQL;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItemModel, Long> {
    List<TodoItemModel> findByVendorId(Long vendorId);
}
