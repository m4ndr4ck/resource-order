package com.tlf.oss.resourceorder.framework.adapters.out.persistence;

import com.tlf.oss.resourceorder.application.ports.out.CreateOrderOutPort;
import com.tlf.oss.resourceorder.application.ports.out.GetOrderOutPort;
import com.tlf.oss.resourceorder.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class OrderMongoAdapter implements CreateOrderOutPort, GetOrderOutPort {

    @Autowired
    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    @Override
    public Order createOrder(Order order){
        OrderMongoEntity entity = orderRepository.save(orderMapper.mapToMongoEntity(order));
        return orderMapper.mapToDomainEntity(entity);
    }

    @Override
    public  Order getOrder(String orderId){
        OrderMongoEntity entity = orderRepository.findById(orderId).get();
        return orderMapper.mapToDomainEntity(entity);
    }
}
