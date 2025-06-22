package com.nicoarbio.cardealership.maintenance.integration.branch;

import com.nicoarbio.cardealership.branch.dto.BranchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "branch-service", path = "/api/v1/branches")
public interface BranchClient {

    @GetMapping("/{id}")
    BranchResponse getBranchById(@PathVariable UUID id);

}
