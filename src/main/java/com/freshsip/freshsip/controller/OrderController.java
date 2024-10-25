package com.freshsip.freshsip.controller;

import com.freshsip.freshsip.dto.OrderDTO;
import com.freshsip.freshsip.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "FreshSip/Order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/getOrders")
    public List<OrderDTO> getOrder() {

        return orderService.getAllOrders();
    }

    @PostMapping("/saveOrder")
    public OrderDTO saveOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.saveOrder(orderDTO);
    }


    @GetMapping("/getLatestOrderIdForUser/{u_email}")
    public ResponseEntity<OrderDTO> getLatestOrderIdForUser(@PathVariable String u_email) {
        OrderDTO latestOrder = orderService.getLatestOrderByIndexNo(u_email);
        if (latestOrder != null) {
            return ResponseEntity.ok(latestOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/updateOrderByOrderID/{cart_id}")
    public OrderDTO updateOrderByOrderID( @PathVariable Long cart_id, @RequestBody OrderDTO orderDTO){
        return orderService.updateOrderByOrderID(cart_id,orderDTO);
    }

    @GetMapping("/getCartById/{cart_id}")
    public OrderDTO getCartById(@PathVariable Long cart_id) {
        return orderService.getCartById(cart_id);
    }

}
