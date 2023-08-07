package com.example.testserviceone;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MynumberRepository extends JpaRepository<Mynumber, Integer> {
}