package com.example.restaurant.controller;// オリジナルと同じ
// パッケージ構成
import java.util.List;

import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
//1.JunitにMockitoを組み込むためのインポート
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

//2.モックをつくって、注入するためのアノテーション
import org.mockito.Mock;
import org.mockito.InjectMocks;

//3. モックの挙動（偽のデータ返却、破棄など）の設定
import static org.mockito.Mockito.when;

//@Testでつかう
import org.junit.jupiter.api.Test;
//assertEqualをつかう
import static org.junit.jupiter.api.Assertions.*;

import com.example.restaurant.entity.MenuItem;
import com.example.restaurant.entity.Order;
import com.example.restaurant.repository.MenuItemRepository;
import com.example.restaurant.repository.OrderRepository;


@ExtendWith(MockitoExtension.class)// 今からこのクラスは@Mockや@InjectMocks
// を使うよって宣言
public class OrderControllerTest {
   

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @InjectMocks
    private OrderController orderController;
    
    @Test 
    void 注文一覧を全件取得(){
        Order order1 = new Order();// Entityのインポートもしてるからインスタンス化できる。
        order1.setMenuName("カレー");
        order1.setPrice(1000);
        order1.setStatus("未対応");
        order1.setQuantity(1);

        MenuItem menuItem1 = new MenuItem();
        menuItem1.setName("カレー");
        menuItem1.setPrice(1000);
        // 上のインスタンスをDB１レコードとして↓でいれる
        List<Order> orderList = List.of(order1);// 入ってるデータ("カレー",1000,"未対応",1)
        List<MenuItem> menuitemList = List.of(menuItem1);

        when(orderRepository.findAll()).thenReturn(orderList);
        when(menuItemRepository.findAll()).thenReturn(menuitemList);

        Model model = new ExtendedModelMap(); //
        String viewName = orderController.order(model);
        
        assertEquals("order", viewName);

        assertEquals(orderList, model.getAttribute("orders"));
        assertEquals(menuitemList, model.getAttribute("menuItems"));
    }
}
