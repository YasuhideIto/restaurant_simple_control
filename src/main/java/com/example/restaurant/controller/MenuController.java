package com.example.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.restaurant.entity.MenuItem;
import com.example.restaurant.repository.MenuItemRepository;

@Controller
public class MenuController {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @GetMapping("/menu")
    public String menu(Model model) {
        List<MenuItem> menuItems = menuItemRepository.findAll();
        model.addAttribute("menuItems", menuItems);
        return "menu";
    }
    @PostMapping("/menu/add")
    public String addMenuItem(@RequestParam String name, @RequestParam int price) {
    	MenuItem item = new MenuItem();
    	item.setName(name);
    	item.setPrice(price);
    	menuItemRepository.save(item);
    	
    	return "redirect:/menu";
    }
}