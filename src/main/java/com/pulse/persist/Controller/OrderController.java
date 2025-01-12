package com.pulse.persist.Controller;


import com.pulse.persist.DTO.OrderDTO;
import com.pulse.persist.Model.Order;
import com.pulse.persist.Service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/order")
@Controller
public class OrderController {

    private final OrderService orderService;
    private final Logger logger = LogManager.getLogger(OrderController.class);

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    public <T> ResponseEntity<T> createOrder(@RequestBody OrderDTO orderDTO){
        logger.info("Order creation started : {}", orderDTO.toString());

        orderService.createOrder(orderDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public  ResponseEntity<List<Order>> getAllOrders(@RequestParam(name = "product", required = false) String productName){
        try {

            List<Order> orders = new ArrayList<>();

            if ( productName != null && !productName.isEmpty()) {
                System.out.println("Getting orders by product name : " + productName);
                orders = orderService.getOrdersByProductName(productName);

            }
            else {
                System.out.println("Getting all orders");
                orders = orderService.getAllOrders();
            }

            return ResponseEntity.ok().body(orders);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") UUID orderId){

        orderService.deleteOrderById(orderId);
        return ResponseEntity.status(204).body("deleted");

    }



}
