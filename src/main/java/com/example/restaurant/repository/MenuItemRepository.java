package com.example.restaurant.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restaurant.entity.MenuItem;


public interface MenuItemRepository extends JpaRepository<MenuItem, Long>{

}
