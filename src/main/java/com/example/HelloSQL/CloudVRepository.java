package com.example.HelloSQL;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudVRepository extends JpaRepository<CloudVModel, Long> {
    // You can define additional query methods here if needed
}

