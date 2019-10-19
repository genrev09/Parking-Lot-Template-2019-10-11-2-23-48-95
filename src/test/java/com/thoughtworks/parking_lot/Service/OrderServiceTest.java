package com.thoughtworks.parking_lot.Service;

import com.thoughtworks.parking_lot.Repository.OrderRepository;
import com.thoughtworks.parking_lot.Repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.core.Order;
import javassist.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Test
    public void should_create_an_order() throws NotFoundException {
        Order order = new Order();
        order.setPlateNumber(123);

//        when(orderRepository.save(order)).thenReturn(parkingLot);

    }
}
