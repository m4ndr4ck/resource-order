package com.tlf.oss.resourceorder.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.List;
import java.util.Optional;

/**
 * Uma ordem pode ter um ou mais componentes de rede.
 * O objeto {@link Order} deve conter somente componentes de rede que estão ativados.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {


    /**
     * ID único da ordem.
     */
    @Getter
    private final String id;

    /**
     * Componentes de rede que fazem parte da ordem.
     */
    @Getter private final List<NetworkComponent> networkComponent;


    /**
     * Cria uma {@link Order} sem o ID. Utilizado quando é necessário persistir um nova ordem.
     */
    public static Order withoutId(
            List<NetworkComponent>  networkComponent) {
        return new Order(null, networkComponent);
    }


    /**
     * Cria uma {@link Order} com ID. Utilizado para reconstituir um ordem existinte.
     */
    public static Order withId(
            String orderId,
            List<NetworkComponent>  networkComponent) {
        return new Order(orderId, networkComponent);
    }

    public Optional<String> getId(){
        return Optional.ofNullable(this.id);
    }

    /**
     * Tenta criar uma nova ordem.
     * @return TRUE se a ordem for criada com sucesso, FALSE caso contrário.
     */
    public boolean validateOrder() {

        if (!mayCreateOrder(this.networkComponent)) {
            return false;
        }
        return true;
    }

    /**
     * Regra de negócio que verifica se algum componente de rede está desativao
     * @return TRUE se todos os componentes de rede estão ativados, FALSE caso contrário.
     */
    private boolean mayCreateOrder(List<NetworkComponent> networkComponent) {
            boolean validation = networkComponent.stream().allMatch(component -> component.getStatus() == NetworkComponent.STATUS.ATIVADO);
            return validation;
    }

    @Value
    public static class OrderId {
        private String orderId;
    }

}


