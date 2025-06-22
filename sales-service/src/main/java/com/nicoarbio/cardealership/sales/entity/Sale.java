package com.nicoarbio.cardealership.sales.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"id", "vehicleUnitId"}))
public class Sale implements Serializable {

    @Id @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private LocalDate saleDate;
    @Column(nullable = false)
    private LocalDate deliveryDate;
    @Column(nullable = false)
    private UUID employeeId;
    @Column(nullable = false)
    private UUID customerId;
    @Column(nullable = false, unique = true)
    private UUID vehicleUnitId;
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

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDate getSaleDate() { return saleDate; }
    public void setSaleDate(LocalDate saleDate) { this.saleDate = saleDate; }

    public LocalDate getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(LocalDate deliveryDate) { this.deliveryDate = deliveryDate; }

    public UUID getEmployeeId() { return employeeId; }
    public void setEmployeeId(UUID employeeId) { this.employeeId = employeeId; }

    public UUID getCustomerId() { return customerId; }
    public void setCustomerId(UUID customerId) { this.customerId = customerId; }

    public UUID getVehicleUnitId() { return vehicleUnitId;}
    public void setVehicleUnitId(UUID vehicleUnitId) { this.vehicleUnitId = vehicleUnitId; }

    public UUID getBranchId() { return branchId; }
    public void setBranchId(UUID branchId) { this.branchId = branchId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", amount=" + amount +
                ", saleDate=" + saleDate +
                ", deliveryDate=" + deliveryDate +
                ", employeeId=" + employeeId +
                ", customerId=" + customerId +
                ", vehicleUnitId=" + vehicleUnitId +
                ", branchId=" + branchId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
