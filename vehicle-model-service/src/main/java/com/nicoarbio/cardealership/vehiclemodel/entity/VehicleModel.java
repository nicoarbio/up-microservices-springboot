package com.nicoarbio.cardealership.vehiclemodel.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"brand", "model", "manufactureYear"}))
public class VehicleModel implements Serializable {

    @Id @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 30)
    private String brand;
    @Column(nullable = false, length = 30)
    private String model;
    @Column(nullable = false)
    private Integer manufactureYear;

    @Column(nullable = false, length = 20)
    private String type;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer warrantyYears;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public String getVehicleModel() {
        return brand + " " + model + " (" + manufactureYear + ")";
    }

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public Integer getManufactureYear() { return manufactureYear; }
    public void setManufactureYear(Integer year) { this.manufactureYear = year; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getWarrantyYears() { return warrantyYears; }
    public void setWarrantyYears(Integer warrantyYears) { this.warrantyYears = warrantyYears; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", manufactureYear=" + manufactureYear +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", warrantyYears=" + warrantyYears +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
