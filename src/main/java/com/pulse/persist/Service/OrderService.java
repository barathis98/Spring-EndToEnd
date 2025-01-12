package com.pulse.persist.Service;

import com.pulse.persist.DTO.OrderDTO;
import com.pulse.persist.DTO.OrderProductDTO;
import com.pulse.persist.DTO.ProductRequestDTO;
import com.pulse.persist.Model.Order;
import com.pulse.persist.Model.OrderProduct;
import com.pulse.persist.Model.Product;
import com.pulse.persist.Repository.OrderProductRepository;
import com.pulse.persist.Repository.OrderRepository;
import com.pulse.persist.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;
    private final RedisService redisService;
    private final Logger logger =  LogManager.getLogger(OrderService.class);

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderProductRepository orderProductRepository, RedisService redisService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;

        this.orderProductRepository = orderProductRepository;
        this.redisService = redisService;
    }

    @Transactional
    public void createOrder(OrderDTO orderDTO){
        Order order = new Order();
        order.setOrderDate(orderDTO.getOrderDate());

        for (ProductRequestDTO productRequest: orderDTO.getProducts()) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            Optional<Product> product = productRepository.findById(productRequest.getProductId());

            if (product.isPresent()){
                orderProduct.setProduct(product.get());
            }
            else{
                throw new IllegalArgumentException("Product not found");
            }

            orderProductRepository.save(orderProduct);

        }

        orderRepository.save(order);


    }

    public List<Order> getOrdersByProductName(String productName){
        return orderRepository.findByOrderProductsProductName(productName);
    }

    public void deleteOrderById(UUID orderId){
        orderRepository.deleteById(orderId);
    }

    public List<Order> getAllOrders() {
        String cacheKey = "orders";

        List<Order> orders = (List<Order>) redisService.get(cacheKey);

        if (orders != null) {
            logger.info("Orders retrieved from cache");
            return orders;
        }
        else {
            logger.info("Orders retrieved from DB");
            orders = orderRepository.findAll();
            redisService.set(cacheKey, orders);
        }

        return orders;
    }
}
