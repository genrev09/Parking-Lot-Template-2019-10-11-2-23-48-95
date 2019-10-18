package com.thoughtworks.parking_lot.Repository;

import com.thoughtworks.parking_lot.core.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, String> {

}
