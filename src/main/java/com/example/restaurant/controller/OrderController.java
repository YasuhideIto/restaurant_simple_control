package com.example.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;//Step8:トランザクション処理、原子性
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.restaurant.entity.MenuItem;
import com.example.restaurant.entity.Order;
import com.example.restaurant.repository.MenuItemRepository;
import com.example.restaurant.repository.OrderRepository;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    // 注文一覧表示
    @GetMapping("/order")
    public String order(Model model) {
        List<Order> orders = orderRepository.findAll();
        List<MenuItem> menuItems = menuItemRepository.findAll();
        model.addAttribute("orders", orders);
        model.addAttribute("menuItems", menuItems);
        return "order";
    }

    // 注文追加
    @Transactional// ← この１行がトランザクション処理のアノテーション
    @PostMapping("/order/add")
    public String addOrder(@RequestParam String menuName, @RequestParam int quantity, Model model) {
        MenuItem menuItem = menuItemRepository.findAll()
            .stream()
            .filter(item -> item.getName().equals(menuName))
            .findFirst()
            .orElse(null);

        if (menuItem != null) {
            Order order = new Order();
            order.setMenuName(menuName);
            order.setPrice(menuItem.getPrice());
            order.setStatus("未対応");
            order.setQuantity(quantity);// ←数量機能追加
            orderRepository.save(order);
        }
        return "redirect:/order";
    }
    
    //　ステータス管理
    @PostMapping("/order/status/{id}")
    public String updateStatus(@PathVariable Long id, @RequestParam String status) {
    	Order order = orderRepository.findById(id).orElse(null);
    	if(order != null) {
    		order.setStatus(status);
    		orderRepository.save(order);
    	}
    	
    	return "redirect:/order";
    }
    
}