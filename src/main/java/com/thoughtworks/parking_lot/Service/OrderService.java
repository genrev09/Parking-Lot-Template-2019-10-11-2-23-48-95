package com.thoughtworks.parking_lot.Service;

import com.thoughtworks.parking_lot.Repository.OrderRepository;
import com.thoughtworks.parking_lot.core.Order;
import com.thoughtworks.parking_lot.core.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public Order createOrder(ParkingLot parkingLot, Order order){
        if(parkingLot.getOrderList().size() < parkingLot.getCapacity()){
            order.setParkingLotName(parkingLot.getName());
            orderRepository.save(order);
            return order;
        }
        return null;
    }
}
