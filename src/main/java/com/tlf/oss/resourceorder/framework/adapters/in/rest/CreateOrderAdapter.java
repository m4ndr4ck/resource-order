package com.tlf.oss.resourceorder.framework.adapters.in.rest;

import com.tlf.oss.resourceorder.application.queries.GetOrderQuery;
import com.tlf.oss.resourceorder.application.queries.GetOrderQuery.GetOrderCommand;
import com.tlf.oss.resourceorder.application.usecases.CreateOrderUseCase;
import com.tlf.oss.resourceorder.application.usecases.CreateOrderUseCase.CreateOrderCommand;
import com.tlf.oss.resourceorder.domain.NetworkComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tlf.oss.resourceorder.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
public class CreateOrderAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateOrderAdapter.class);

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderQuery getOrderQuery;

    @PostMapping("/v1/createorder")
    public Order createOrder(@RequestBody NetworkComponent[] networkComponent) {
        LOGGER.info("Criação de ordem com os componentes de rede: {}", networkComponent);

        CreateOrderCommand command = new CreateOrderCommand(
                Arrays.asList(networkComponent)
        );

        return createOrderUseCase.createOrder(command);
    }

    @GetMapping("/v1/getorder/{id}")
    public Order getOrder(@PathVariable("id") String id) {
        LOGGER.info("Obtendo de ordem : {}", id);

        GetOrderCommand command = new GetOrderQuery.GetOrderCommand(id);

        return  getOrderQuery.getOrder(command);
    }

}
