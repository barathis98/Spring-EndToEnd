package com.pulse.persist.Repository;

import com.pulse.persist.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    public List<Order> findByOrderProductsProductName(String productName);
}
