package com.tlf.oss.resourceorder.application.ports.out;

import com.tlf.oss.resourceorder.domain.Order;

public interface GetOrderOutPort {

    Order getOrder(String id);
}
