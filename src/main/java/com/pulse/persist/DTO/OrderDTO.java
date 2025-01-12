package com.pulse.persist.DTO;

import com.pulse.persist.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Date orderDate;
    private List<ProductRequestDTO> products;

}
