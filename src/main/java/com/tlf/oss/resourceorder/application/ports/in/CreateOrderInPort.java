package com.tlf.oss.resourceorder.application.ports.in;

import com.tlf.oss.resourceorder.application.ports.out.CreateOrderOutPort;
import com.tlf.oss.resourceorder.application.queries.ResourceOrderOrchestrationQuery;
import com.tlf.oss.resourceorder.application.usecases.CreateOrderUseCase;
import com.tlf.oss.resourceorder.domain.NetworkComponent;
import com.tlf.oss.resourceorder.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
@Transactional
public class CreateOrderInPort implements CreateOrderUseCase {

    private Order order;
    private final CreateOrderOutPort createOrderOutPort;

    @Autowired
    ResourceOrderOrchestrationQuery resourceOrderOrchestrationClient;
    /**
     * Persiste ordem.
     * @param
     * @return
     */
    @Override
    public Order createOrder(CreateOrderCommand command){
        order = Order.withoutId(retrieveNetworkComponents(command.getNetworkComponent()));

        if(order.validateOrder())
        return createOrderOutPort.createOrder(order);

        return order;
    }

    /**
     * Obtém componentes de rede através da porta de saída.
     * @param networkComponents
     * @return
     */
    @Override
    public List<NetworkComponent> retrieveNetworkComponents(List<NetworkComponent> networkComponents){
        List<NetworkComponent> nc = new ArrayList<>();

        networkComponents.forEach(x -> nc.add(resourceOrderOrchestrationClient.findByComponentId(x.getId())));

        return nc;
    }

}
