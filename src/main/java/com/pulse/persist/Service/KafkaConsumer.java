package com.pulse.persist.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pulse.persist.DTO.OrderDTO;
import com.pulse.persist.DTO.ProductDTO;
import com.pulse.persist.Model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private final ObjectMapper objectMapper;
    private final OrderService orderService;

    public KafkaConsumer(ObjectMapper objectMapper, OrderService orderService) {
        this.objectMapper = objectMapper;
        this.orderService = orderService;
    }

//    @KafkaListener(topics = "order-topic", groupId = "consumer1")
//    public void consumeMessage(String message){
//        System.out.println(message);
//        OrderDTO orderDTO = objectMapper.convertValue(message, OrderDTO.class);
//        orderService.createOrder(orderDTO);
//
//    }
}
