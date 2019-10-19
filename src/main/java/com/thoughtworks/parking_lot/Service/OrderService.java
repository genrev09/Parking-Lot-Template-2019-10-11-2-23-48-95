package com.thoughtworks.parking_lot.Service;

import com.thoughtworks.parking_lot.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
}
