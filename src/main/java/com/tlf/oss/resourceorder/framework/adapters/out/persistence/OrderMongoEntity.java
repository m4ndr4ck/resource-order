package com.tlf.oss.resourceorder.framework.adapters.out.persistence;

import java.util.ArrayList;
import java.util.List;

import com.tlf.oss.resourceorder.domain.NetworkComponent;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order")
@AllArgsConstructor
@NoArgsConstructor
public class OrderMongoEntity {

    @Getter
    @Setter
    @Id
    private String id;

   // @Transient
    @Getter
    @Setter
    private List<NetworkComponent> networkComponent;

}