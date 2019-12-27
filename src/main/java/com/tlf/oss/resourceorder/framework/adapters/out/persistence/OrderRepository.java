package com.tlf.oss.resourceorder.framework.adapters.out.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


interface OrderRepository extends CrudRepository<OrderMongoEntity, String> {

    OrderMongoEntity getOrderMongoEntityById(String id);

}
