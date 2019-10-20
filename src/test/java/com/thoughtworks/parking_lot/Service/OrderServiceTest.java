package com.thoughtworks.parking_lot.Service;

import com.thoughtworks.parking_lot.Repository.OrderRepository;
import com.thoughtworks.parking_lot.core.Order;
import com.thoughtworks.parking_lot.core.ParkingLot;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderService.class)
@ActiveProfiles(profiles = "test")
public class OrderServiceTest {

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Test
    public void should_create_an_order() {
        Order acceptedOrder = new Order();
        acceptedOrder.setParkingLotName("Genrev");
        acceptedOrder.setPlateNumber(234);

        List<Order> orderList = new ArrayList<>();
        orderList.add(acceptedOrder);

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setCapacity(3);
        parkingLot.setName("Genrev");
        parkingLot.setOrderList(orderList);

        Order order = new Order();
        order.setPlateNumber(123);

        when(orderRepository.save(order)).thenReturn(order);

        Order actualOrder = orderService.createOrder(parkingLot,order);

        Assertions.assertThat(actualOrder.getParkingLotName()).isEqualTo(parkingLot.getName());
        Assertions.assertThat(actualOrder.getPlateNumber()).isEqualTo(order.getPlateNumber());



    }
}
