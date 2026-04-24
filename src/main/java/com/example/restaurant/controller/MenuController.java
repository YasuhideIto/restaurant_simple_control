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
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @PostMapping("/menu/delete/{id}")// メニュー削除処理
    public String deleteMenuItem(@PathVariable Long id) {// これはURLから来たidという値を受け取りますよ。という宣言
    	
    	menuItemRepository.deleteById(id);// ↑で受け取ったidにはmenu.htmlのメニューidの番号が入っている
    	//で、↑はJPAを使ったメソッド。
    	return "redirect:/menu";
    	
    	
    }
    @GetMapping("/menu/edit/{id}")// メニュー編集画面にうつる処理
    public String editMenuItemForm(@PathVariable Long id, Model model) {
    	MenuItem menuItem = menuItemRepository.findById(id).orElse(null);// 受け取ったidでDBから1件取得する。見つからなければnullを返す
    	model.addAttribute("menuItem", menuItem);// 取得したメニューのデータをHTMLに渡す（これにより編集フォームに現在の値が入る）
    	return "edit";// edit.htmlを表示する
    }
    
    @PostMapping("/menu/edit/{id}")// 編集画面DB操作処理
    public String editMenuItem(@PathVariable Long id, @RequestParam String name, @RequestParam int price) {
        MenuItem menuItem = menuItemRepository.findById(id).orElse(null);// 編集対象のメニューをDBから取得
        if (menuItem != null) {
            menuItem.setName(name);// 
            menuItem.setPrice(price);// フォームで入力した値をセット
            menuItemRepository.save(menuItem);// 既存のidがあるのでINSERTではなくUPDATEとして保存する
        }
        return "redirect:/menu";
    }
}