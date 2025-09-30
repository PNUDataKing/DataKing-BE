package com.pnudataking.backend.toilet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pnudataking.backend.toilet.entity.Toilet;

public interface ToiletRepository extends JpaRepository<Toilet, Long> {

    @Query("""
SELECT t FROM Toilet t
WHERE t.lat BETWEEN :lat1 AND :lat2
  AND t.lng BETWEEN :lng1 AND :lng2
""")
    List<Toilet> findAllByRange(
            @Param("lat1") double lat1,
            @Param("lng1") double lng1,
            @Param("lat2") double lat2,
            @Param("lng2") double lng2);
}
