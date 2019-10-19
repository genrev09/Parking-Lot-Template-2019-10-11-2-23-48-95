package com.thoughtworks.parking_lot.Service;

import com.thoughtworks.parking_lot.Repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.core.ParkingLot;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotService {
    public static final String INVALID_NAME = "Parking lot name does not exists!";

    @Autowired
    ParkingLotRepository parkingLotRepository;

    public boolean addParkingLot(ParkingLot parkingLot){
        if(parkingLotRepository.save(parkingLot) != null)
            return true;
        return false;
    }

    public String deleteParkingLot(String parkingLotName) throws NotFoundException {
        ParkingLot parkingLot = parkingLotRepository.findByName(parkingLotName);
        if (parkingLot != null) {
            parkingLotRepository.delete(parkingLot);
            return "Parking lot successfully deleted.";
        }
        throw new NotFoundException(INVALID_NAME);
    }

    public Page<ParkingLot> getAllParkingLot(int page, int pagesize){
        return parkingLotRepository.findAll(PageRequest.of(page,pagesize));
    }

    public ParkingLot getParkingLotByName(String name) throws NotFoundException {
        ParkingLot parkingLot = parkingLotRepository.findByName(name);
        if (parkingLot != null)
            return parkingLot;
        throw new NotFoundException(INVALID_NAME);
    }

    public ParkingLot updateParkingLotCapacity(String name, ParkingLot updatedParkingLot) throws NotFoundException {
        ParkingLot parkingLot = parkingLotRepository.findByName(name);
        if (parkingLot != null) {
            parkingLot.setCapacity(updatedParkingLot.getCapacity());
            parkingLotRepository.save(parkingLot);
            return parkingLot;
        }
        throw new NotFoundException(INVALID_NAME);
    }
}
