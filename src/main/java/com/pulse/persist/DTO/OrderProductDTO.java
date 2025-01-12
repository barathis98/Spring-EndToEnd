package com.pulse.persist.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderProductDTO {
    private Integer quantity;
    private UUID productId;
}
