package com.thoughtworks.parking_lot.Controller;

import com.thoughtworks.parking_lot.Service.ParkingLotService;
import com.thoughtworks.parking_lot.core.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/parkinglots")
public class ParkingLotController {

    @Autowired
    ParkingLotService parkingLotService;

    @PostMapping(headers = {"Content-type=application/json"})
    public ResponseEntity addParkingLot(ParkingLot parkingLot){
        if (parkingLotService.addParkingLot(parkingLot))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/{name}", headers = {"Content-type=application/json"})
    public ResponseEntity deleteParkingLot(@PathVariable("name") String name){
        if (parkingLotService.deleteParkingLot(name))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
