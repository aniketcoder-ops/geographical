package com.example.geographical.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Builder
@Accessors(chain = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "geographical_data")
public class GeographicalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gd_id")
    private Long gdId;

    @Column(name = "uuid", nullable = false)
    private String uuid;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "description")
    private String description;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @Column(name = "status", nullable = false)
    private Integer status;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = 1;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
