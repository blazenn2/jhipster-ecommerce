package com.blazenn.ecommerce.service.mapper;


import com.blazenn.ecommerce.domain.*;
import com.blazenn.ecommerce.service.dto.OrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Order} and its DTO {@link OrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {


    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "removeOrderItems", ignore = true)
    Order toEntity(OrderDTO orderDTO);

    default Order fromId(Long id) {
        if (id == null) {
            return null;
        }
        Order order = new Order();
        order.setId(id);
        return order;
    }
}
