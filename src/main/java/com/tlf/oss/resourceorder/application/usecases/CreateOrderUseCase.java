package com.tlf.oss.resourceorder.application.usecases;
import com.tlf.oss.resourceorder.domain.Order;
import com.tlf.oss.resourceorder.framework.helpers.SelfValidating;
import com.tlf.oss.resourceorder.domain.NetworkComponent;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface CreateOrderUseCase {

    Order createOrder(CreateOrderCommand command);
    List<NetworkComponent> retrieveNetworkComponents(List<NetworkComponent> networkComponents);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class CreateOrderCommand extends SelfValidating<CreateOrderCommand> {

        @NotNull
        private final List<NetworkComponent> networkComponent;

        public CreateOrderCommand(List<NetworkComponent> networkComponent) {
            this.networkComponent = networkComponent;
            this.validateSelf();
        }
    }

}
