package com.thoughtworks.parking_lot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.Controller.OrderController;
import com.thoughtworks.parking_lot.Service.OrderService;
import com.thoughtworks.parking_lot.Service.ParkingLotService;
import com.thoughtworks.parking_lot.core.Order;
import com.thoughtworks.parking_lot.core.ParkingLot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
@ActiveProfiles(profiles = "test")
public class OrderControllerTest {

    @MockBean
    OrderService orderService;

    @MockBean
    ParkingLotService parkingLotService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_create_order() throws Exception {
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

        Order savedOrder = new Order();
        savedOrder.setParkingLotName(parkingLot.getName());
        savedOrder.setPlateNumber(order.getPlateNumber());

        when(parkingLotService.getParkingLotByName("Genrev")).thenReturn(parkingLot);
        when(orderService.createOrder(parkingLot,order)).thenReturn(savedOrder);

        ResultActions result = mockMvc.perform(post("/parkinglots/{name}/orders","Genrev")
                .content(objectMapper.writeValueAsString(order))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
    }

    @Test
    public void should_update_order() throws Exception {
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

        Order savedOrder = new Order();
        savedOrder.setParkingLotName(parkingLot.getName());
        savedOrder.setPlateNumber(order.getPlateNumber());

        when(parkingLotService.getParkingLotByName("Genrev")).thenReturn(parkingLot);
        when(orderService.updateOrder(123)).thenReturn(savedOrder);

        ResultActions result = mockMvc.perform(patch("/parkinglots/{name}/orders/{plateNumber}","Genrev",123)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated())
        .andExpect(jsonPath("$.parkingLotName",is(savedOrder.getParkingLotName())))
                .andExpect(jsonPath("$.plateNumber",is(savedOrder.getPlateNumber())))
                .andExpect(jsonPath("$.orderStatus",is(savedOrder.getOrderStatus())));
    }

    @Test
    public void should_not_create_order_when_parking_lot_is_full() throws Exception {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order());

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setCapacity(1);
        parkingLot.setName("Genrev");
        parkingLot.setOrderList(orderList);

        Order order = new Order();
        order.setPlateNumber(123);

        when(parkingLotService.getParkingLotByName("Genrev")).thenReturn(parkingLot);
        when(orderService.createOrder(any(),any())).thenThrow(Exception.class);

        ResultActions result = mockMvc.perform(post("/parkinglots/{name}/orders","Genrev")
                .content(objectMapper.writeValueAsString(order))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isServiceUnavailable());
    }
}
