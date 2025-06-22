package com.nicoarbio.cardealership.vehicleunit.entity;

import com.nicoarbio.cardealership.vehicleunit.dto.LocationType;
import com.nicoarbio.cardealership.vehicleunit.dto.VehicleUnitStatus;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class VehicleUnit implements Serializable {

    @Id @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 9, unique = true)
    private String licencePlate;
    @Column(nullable = false, length = 30)
    private String color;
    @Column(nullable = false, columnDefinition = "varchar(20) default 'AVAILABLE'")
    @Enumerated(EnumType.STRING)
    private VehicleUnitStatus status = VehicleUnitStatus.AVAILABLE;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LocationType location;
    @Column(nullable = false)
    private UUID vehicleModelId;
    @Column
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

    public String getLicencePlate() { return licencePlate; }
    public void setLicencePlate(String licencePlate) { this.licencePlate = licencePlate; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public VehicleUnitStatus getStatus() { return status; }
    public void setStatus(VehicleUnitStatus status) { this.status = status; }

    public LocationType getLocation() { return location; }
    public void setLocation(LocationType locationType) { this.location = locationType; }

    public UUID getVehicleModelId() { return vehicleModelId; }
    public void setVehicleModelId(UUID vehicleModel) { this.vehicleModelId = vehicleModel;}

    public UUID getBranchId() { return branchId; }
    public void setBranchId(UUID branch) { this.branchId = branch; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "VehicleUnit{" +
                "id=" + id +
                ", licencePlate='" + licencePlate + '\'' +
                ", color='" + color + '\'' +
                ", status=" + status +
                ", vehicleModelId=" + vehicleModelId +
                ", branchId=" + branchId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
