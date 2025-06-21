package com.nicoarbio.cardealership.sales.integration.branch.connector;

import com.nicoarbio.cardealership.sales.integration.branch.dto.Branch;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "branch-service", fallbackFactory = BranchClientFallbackFactory.class)
public interface BranchClient {

    @GetMapping("/api/v1/branches/{id}")
    Branch getBranchById(@PathVariable String id);

}
