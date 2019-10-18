package com.thoughtworks.parking_lot.Service;

import com.thoughtworks.parking_lot.Repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.core.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {

    @Autowired
    ParkingLotRepository parkingLotRepository;

    public boolean addParkingLot(ParkingLot parkingLot){
        if(parkingLotRepository.save(parkingLot) != null)
            return true;
        return false;
    }

    public boolean deleteParkingLot(String parkingLotName){
        ParkingLot parkingLot = parkingLotRepository.findByName(parkingLotName);
        List<ParkingLot> parkingLotList = parkingLotRepository.findAll();
        if (parkingLotList.contains(parkingLot)) {
            parkingLotRepository.delete(parkingLot);
            return true;
        }
        return false;
    }
}
