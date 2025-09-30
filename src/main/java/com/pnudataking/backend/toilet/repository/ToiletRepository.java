package com.pnudataking.backend.toilet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pnudataking.backend.toilet.entity.Toilet;

public interface ToiletRepository extends JpaRepository<Toilet, Long> {

    @Query("SELECT n FROM Toilet n WHERE " +
            "n.lat BETWEEN :swLat AND :neLat AND " +
            "n.lng BETWEEN :swLng AND :neLng")
    List<Toilet> findByBounds(
            @Param("swLat") Double swLat,  // 남서쪽 위도
            @Param("swLng") Double swLng,  // 남서쪽 경도
            @Param("neLat") Double neLat,  // 북동쪽 위도
            @Param("neLng") Double neLng   // 북동쪽 경도
    );
}
