package com.example.geographical.repository;

import com.example.geographical.dto.projection.GeographicalDataProjectionDTO;
import com.example.geographical.model.GeographicalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GeographicalRepository extends JpaRepository<GeographicalData, Long> {

    // Get data by UUID (ONLY ACTIVE RECORDS)
    @Query("""
        SELECT NEW com.example.geographical.dto.projection.GeographicalDataProjectionDTO(
            gd.userName,
            gd.latitude,
            gd.longitude,
            gd.description,
            gd.remarks
        )
        FROM GeographicalData gd
        WHERE gd.uuid = :uuid
          AND gd.status = 1
    """)
    List<GeographicalDataProjectionDTO> findByUuid(@Param("uuid") String uuid);

    // Soft delete (1 → 0)
    @Modifying
    @Query("""
        UPDATE GeographicalData gd
        SET gd.status = 0
        WHERE gd.gdId = :id
          AND gd.status = 1
    """)
    int softDeleteById(@Param("id") Long id);


    List<GeographicalData> findAllByOrderByGdIdDesc();
}
