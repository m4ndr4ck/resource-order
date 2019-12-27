package com.tlf.oss.resourceorder.application.queries;

import com.tlf.oss.resourceorder.domain.NetworkComponent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


// Bate na API do Kubernetes para descobrir onde está o serviço (Service Discovery)
@FeignClient(name = "resource-order-orchestration")
//@FeignClient(name = "resource-order-orchestration", url="http://localhost:8081")
public interface ResourceOrderOrchestrationQuery {

    @GetMapping("/v1/getnetworkcomponent/{componentId}")
    NetworkComponent findByComponentId(@PathVariable("componentId") String componentId);

}