package com.nicoarbio.cardealership.maintenance.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"entryDate", "vehicleUnitId"}))
public class Maintenance implements Serializable {

    @Id @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private LocalDate entryDate;
    @Column(nullable = false)
    private Integer kilometrage;
    @Column(nullable = false)
    private Boolean isUnderWarranty;
    @Column(nullable = false, length = 50)
    private String serviceType;
    @Column(nullable = false)
    private UUID vehicleUnitId;
    @Column(nullable = false)
    private UUID customerId;
    @Column(nullable = false)
    private UUID employeeId;
    @Column(nullable = false)
    private UUID branchId;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public LocalDate getEntryDate() { return entryDate; }
    public void setEntryDate(LocalDate entryDate) { this.entryDate = entryDate; }

    public Integer getKilometrage() { return kilometrage;}
    public void setKilometrage(Integer kilometrage) { this.kilometrage = kilometrage; }

    public Boolean getUnderWarranty() { return isUnderWarranty; }
    public void setUnderWarranty(Boolean underWarranty) { isUnderWarranty = underWarranty; }

    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }

    public UUID getVehicleUnitId() { return vehicleUnitId; }
    public void setVehicleUnitId(UUID vehicleUnitId) { this.vehicleUnitId = vehicleUnitId; }

    public UUID getCustomerId() { return customerId; }
    public void setCustomerId(UUID customerId) { this.customerId = customerId; }

    public UUID getEmployeeId() { return employeeId; }
    public void setEmployeeId(UUID employeeId) { this.employeeId = employeeId; }

    public UUID getBranchId() { return branchId; }
    public void setBranchId(UUID branchId) { this.branchId = branchId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "Maintenance{" +
                "id=" + id +
                ", entryDate=" + entryDate +
                ", kilometrage=" + kilometrage +
                ", isUnderWarranty=" + isUnderWarranty +
                ", serviceType='" + serviceType + '\'' +
                ", vehicleUnitId=" + vehicleUnitId +
                ", customerId=" + customerId +
                ", employeeId=" + employeeId +
                ", branchId=" + branchId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
