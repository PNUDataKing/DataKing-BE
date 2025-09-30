package com.pnudataking.backend.nursingRoom.repository;

import com.pnudataking.backend.nursingRoom.entity.NursingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NursingRoomRepository extends JpaRepository<NursingRoom, Long> {

    @Query("SELECT n FROM NursingRoom n WHERE " +
            "n.lat BETWEEN :swLat AND :neLat AND " +
            "n.lng BETWEEN :swLng AND :neLng")
    List<NursingRoom> findByBounds(
            @Param("swLat") Double swLat,  // 남서쪽 위도
            @Param("swLng") Double swLng,  // 남서쪽 경도
            @Param("neLat") Double neLat,  // 북동쪽 위도
            @Param("neLng") Double neLng   // 북동쪽 경도
    );
}
