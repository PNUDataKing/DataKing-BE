package com.pnudataking.backend.nursingRoom.repository;

import com.pnudataking.backend.nursingRoom.entity.NursingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NursingRoomRepository extends JpaRepository<NursingRoom, Long> {
}
