package com.example.restaurant.controller;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
        model.addAttribute("menuItem", new MenuItem()); //追加
        return "menu";
    }
    @PostMapping("/menu/add")
    public String addMenuItem(@Valid @ModelAttribute MenuItem item, BindingResult result, Model model) {
    	
    	
    	// ➀バリデーションエラー時の処理
    	if(result.hasErrors()) {// 入力欄が空欄または0以下の時に行われる処理
    		List<MenuItem> menuItems = menuItemRepository.findAll();
    		model.addAttribute("menuItems", menuItems);
    		model.addAttribute("menuItem", new MenuItem());//これ追加したが分からん
    		
    		
    		return "menu"; //エラー時はフォームを送る
    		
    	}
    	
    	
    	// ➁重複チェック時の処理
    	Optional<MenuItem> existing = menuItemRepository.findByName(item.getName());
    		if(existing.isPresent()) {
    			List<MenuItem> menuItems = menuItemRepository.findAll();
    			model.addAttribute("menuItems", menuItems);
    			model.addAttribute("menuItem", new MenuItem()); //追加理由が分からん
    			model.addAttribute("duplicate", true);
    		
			//すでにある商品名だったら→フォームに戻す
			return"menu";
    		
		}
		
		//重複なし　→　保存
    	menuItemRepository.save(item);
    	return "redirect:/menu";
    }
}