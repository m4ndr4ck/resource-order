package com.tlf.oss.resourceorder.application.ports.in;

import com.tlf.oss.resourceorder.application.ports.out.GetOrderOutPort;
import com.tlf.oss.resourceorder.application.queries.GetOrderQuery;
import com.tlf.oss.resourceorder.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
@Transactional
public class GetOrderInPort implements GetOrderQuery {

    private final GetOrderOutPort getOrderOutPort;

    @Override
    public Order getOrder(GetOrderCommand getOrderCommand){
        return getOrderOutPort.getOrder(getOrderCommand.getId());
    }
}
