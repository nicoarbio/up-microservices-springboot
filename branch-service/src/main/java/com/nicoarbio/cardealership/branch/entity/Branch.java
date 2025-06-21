package com.nicoarbio.cardealership.branch.entity;

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
public class Branch implements Serializable {

    @Id @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @Column(nullable = false, length = 50)
    private String country;
    @Column(nullable = false, length = 50)
    private String province;
    @Column(nullable = false, length = 50)
    private String city;
    @Column(nullable = false, length = 255)
    private String address;
    @Column(nullable = false)
    private LocalDate openingDate;
    @Column(nullable = false)
    private Integer localDeliveryTimeDays;
    @Column(nullable = false)
    private Integer centralDeliveryTimeDays;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;


    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public LocalDate getOpeningDate() { return openingDate; }
    public void setOpeningDate(LocalDate openingDate) { this.openingDate = openingDate; }

    public Integer getLocalDeliveryTimeDays() { return localDeliveryTimeDays; }
    public void setLocalDeliveryTimeDays(Integer localDeliveryTimeDays) { this.localDeliveryTimeDays = localDeliveryTimeDays; }

    public Integer getCentralDeliveryTimeDays() { return centralDeliveryTimeDays; }
    public void setCentralDeliveryTimeDays(Integer centralDeliveryTimeDays) { this.centralDeliveryTimeDays = centralDeliveryTimeDays; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", openingDate=" + openingDate +
                ", localDeliveryTimeDays=" + localDeliveryTimeDays +
                ", centralDeliveryTimeDays=" + centralDeliveryTimeDays +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
