package com.freshsip.freshsip.controller;

import com.freshsip.freshsip.dto.OrderDTO;
import com.freshsip.freshsip.repository.OrderRepository;
import com.freshsip.freshsip.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "FreshSip/Order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping(value="/Orders", produces = "application/json")
    public List<OrderDTO> getOrder() {

        return orderService.getAllOrders();
    }

    @PostMapping(value="/adminUser/Order", produces = "application/json")
    public OrderDTO saveOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.saveOrder(orderDTO);
    }


    @GetMapping(value="/adminUser/{u_email}", produces = "application/json")
    public ResponseEntity<OrderDTO> getLatestOrderIdForUser(@PathVariable String u_email) {
        OrderDTO latestOrder = orderService.getLatestOrderByIndexNo(u_email);
        if (latestOrder != null) {
            return ResponseEntity.ok(latestOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping(value="/adminUser/OrderByOrderID/{cart_id}", produces = "application/json")
    public OrderDTO updateOrderByOrderID( @PathVariable Long cart_id, @RequestBody OrderDTO orderDTO){
        return orderService.updateOrderByOrderID(cart_id,orderDTO);
    }

    @GetMapping(value="/adminUser/CartById/{cart_id}", produces = "application/json")
    public OrderDTO getCartById(@PathVariable Long cart_id) {
        return orderService.getCartById(cart_id);
    }


    @GetMapping("/total/{cartId}")
    public ResponseEntity<Double> getFullTotalByCartId(@PathVariable Long cartId) {
        try {
            Double fullTotal = orderRepository.findFullTotalByCartId(cartId);
            if (fullTotal != null) {
                return ResponseEntity.ok(fullTotal);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Handle errors
        }
    }
}
