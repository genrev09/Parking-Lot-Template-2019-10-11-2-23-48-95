package com.thoughtworks.parking_lot.Service;

import com.thoughtworks.parking_lot.Repository.OrderRepository;
import com.thoughtworks.parking_lot.core.Order;
import com.thoughtworks.parking_lot.core.ParkingLot;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {
    public static final String CLOSED = "Closed";
    public static final String ORDER_NAME_DOES_NOT_EXISTS = "Order name does not exists!";
    public static final String PARKING_LOT_IS_FULL = "Parking lot is full. Please try again later.";
    @Autowired
    OrderRepository orderRepository;

    public Order createOrder(ParkingLot parkingLot, Order order) throws Exception {
        if(parkingLot.getOrderList().size() < parkingLot.getCapacity()){
            order.setParkingLotName(parkingLot.getName());
            orderRepository.save(order);
            return order;
        }
        throw new Exception(PARKING_LOT_IS_FULL);
    }

    public Order updateOrder(int plateNumber) throws NotFoundException {
        Order order = orderRepository.findOneByPlatenumber(plateNumber);
        if (order != null){
            order.setOrderStatus("Closed");
            order.setCloseTime(new Date());
            orderRepository.save(order);
            return order;
        }
        throw new NotFoundException(ORDER_NAME_DOES_NOT_EXISTS);
    }
}
