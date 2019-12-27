package com.tlf.oss.resourceorder.framework.adapters.out.persistence;

import com.tlf.oss.resourceorder.domain.Order;
import org.springframework.stereotype.Component;

@Component
class OrderMapper {

    OrderMongoEntity mapToMongoEntity(Order order) {
        return new OrderMongoEntity(
                order.getId().isPresent() ? order.getId().get() : null,
                order.getNetworkComponent());
    }

    Order mapToDomainEntity(OrderMongoEntity order){
        return Order.withId(
                order.getId(),
                order.getNetworkComponent());
    }

}