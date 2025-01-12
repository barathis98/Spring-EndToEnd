package com.pulse.persist.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name = "order_product")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProduct {
    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    @Id
    private UUID orderProductId;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;
}
