package com.thoughtworks.parking_lot.Controller;

import com.thoughtworks.parking_lot.Service.OrderService;
import com.thoughtworks.parking_lot.Service.ParkingLotService;
import com.thoughtworks.parking_lot.core.Order;
import com.thoughtworks.parking_lot.core.ParkingLot;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/parkinglots/{name}/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    ParkingLotService parkingLotService;

    @PostMapping(headers = {"Content-type=application/json"})
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    public Order createOrder(@PathVariable("name") String name,
                                @RequestBody Order order) throws Exception {
        ParkingLot parkingLot = parkingLotService.getParkingLotByName(name);
        return orderService.createOrder(parkingLot,order);
    }

    @PatchMapping(path = "/{plateNumber}",headers = {"Content-type=application/json"})
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    public Order updateOrder(@PathVariable("name") String name,
                             @PathVariable("plateNumber") int plateNumber) throws NotFoundException {
        ParkingLot parkingLot = parkingLotService.getParkingLotByName(name);
        return orderService.updateOrder(plateNumber);
    }
}
