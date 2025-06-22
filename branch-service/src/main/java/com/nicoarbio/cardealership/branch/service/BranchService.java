package com.nicoarbio.cardealership.branch.service;

import com.nicoarbio.cardealership.branch.dto.BranchRequest;
import com.nicoarbio.cardealership.branch.dto.BranchResponse;

import java.util.List;
import java.util.UUID;

public interface BranchService {

    List<BranchResponse> getAll();

    BranchResponse getById(UUID id);

    BranchResponse create(BranchRequest request);

    BranchResponse update(UUID id, BranchRequest request);

    void delete(UUID id);

}
