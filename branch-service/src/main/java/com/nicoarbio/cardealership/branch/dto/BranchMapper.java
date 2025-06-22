package com.nicoarbio.cardealership.branch.dto;

import com.nicoarbio.cardealership.branch.entity.Branch;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BranchMapper {

    public Branch toEntity(BranchRequest req) {
        return toEntity(req, null);
    }

    public Branch toEntity(BranchRequest req, Branch existing) {
        if (existing == null) {
            existing = new Branch();
        }
        existing.setName(req.name());
        existing.setCountry(req.country());
        existing.setProvince(req.province());
        existing.setCity(req.city());
        existing.setAddress(req.address());
        existing.setOpeningDate(req.openingDate());
        existing.setLocalDeliveryTimeDays(req.localDeliveryTimeDays());
        existing.setCentralDeliveryTimeDays(req.centralDeliveryTimeDays());
        return existing;
    }

    public BranchResponse toResponse(Branch branch) {
        return new BranchResponse(
                branch.getId(),
                branch.getName(),
                branch.getCountry(),
                branch.getProvince(),
                branch.getCity(),
                branch.getAddress(),
                branch.getOpeningDate(),
                branch.getLocalDeliveryTimeDays(),
                branch.getCentralDeliveryTimeDays(),
                branch.getCreatedAt(),
                branch.getUpdatedAt()
        );
    }

    public List<BranchResponse> toResponseList(List<Branch> branches) {
        return branches.stream().map(this::toResponse).collect(Collectors.toList());
    }

}
