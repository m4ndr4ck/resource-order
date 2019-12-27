package com.tlf.oss.resourceorder.application.queries;

import com.tlf.oss.resourceorder.domain.Order;
import com.tlf.oss.resourceorder.framework.helpers.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;

public interface GetOrderQuery {

    Order getOrder(GetOrderCommand getOrderCommand);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class GetOrderCommand  extends SelfValidating<GetOrderCommand> {

        @NotNull
        private final String id;


        public GetOrderCommand(String id) {
            this.id = id;
        }
    }
}
