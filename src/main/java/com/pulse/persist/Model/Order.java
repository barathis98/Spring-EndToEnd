package com.pulse.persist.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name="`order`")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    @Id
    @Column(name = "order_id")
    private UUID orderId;

    @OneToMany(mappedBy = "order", orphanRemoval = true)
    private List<OrderProduct> orderProducts;
    private Date orderDate;
}
