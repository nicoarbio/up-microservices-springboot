package com.nicoarbio.cardealership.sales.integration.branch.connector;

import com.nicoarbio.cardealership.branch.dto.BranchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient("branch-service")
public interface BranchClient {

    @GetMapping("/api/v1/branches/{id}")
    BranchResponse getBranchById(@PathVariable UUID id);

}
