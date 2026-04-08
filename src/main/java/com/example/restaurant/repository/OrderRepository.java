package com.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restaurant.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}