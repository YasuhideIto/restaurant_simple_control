package com.example.restaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restaurant.entity.MenuItem;


public interface MenuItemRepository extends JpaRepository<MenuItem, Long>{

	
	  
	 
	Optional<MenuItem> findByName(String name);
	//このOptionalは「結果があるかも？ないかも？」を表す入れ物
}
