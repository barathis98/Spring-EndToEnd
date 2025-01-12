package com.pulse.persist.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name="product", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    @Id
    @Column(name = "product_id")
    private UUID productId;
    private String name;
    private String description;
}
