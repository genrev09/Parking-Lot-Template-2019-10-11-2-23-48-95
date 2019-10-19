package com.thoughtworks.parking_lot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.Controller.ParkingLotController;
import com.thoughtworks.parking_lot.Service.ParkingLotService;
import com.thoughtworks.parking_lot.core.ParkingLot;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ParkingLotController.class)
@ActiveProfiles(profiles = "test")
public class ParkingLotControllerTest {

    private static final String DELETED_PARKINGLOT = "Parking lot successfully deleted.";
    @MockBean
    ParkingLotService parkingLotService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void should_add_parking_lot() throws Exception {
        when(parkingLotService.addParkingLot(any())).thenReturn(true);
        ResultActions result = mockMvc.perform(post("/parkinglots")
                .content(objectMapper.writeValueAsString(new ParkingLot()))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
    }

    @Test
    public void should_not_add_parking_lot_when_invalid_parking_lot() throws Exception {
        when(parkingLotService.addParkingLot(any())).thenReturn(false);
        ResultActions result = mockMvc.perform(post("/parkinglots")
                .content(objectMapper.writeValueAsString(null))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
    }

    @Test
    public void should_delete_parking_lot() throws Exception {
        when(parkingLotService.deleteParkingLot("Genrev")).thenReturn(DELETED_PARKINGLOT);
        ResultActions result = mockMvc.perform(delete("/parkinglots/{name}", "Genrev")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    public void should_not_delete_parking_lot_when_invalid_name() throws Exception {
        when(parkingLotService.deleteParkingLot("Genrev")).thenThrow(NotFoundException.class);
        ResultActions result = mockMvc.perform(delete("/parkinglots/{name}", "Genrev")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    public void should_get_all_parking_lot() throws Exception {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("Genrev");
        parkingLot.setCapacity(1);
        parkingLot.setLocation("Santa Rosa");

        parkingLotList.add(parkingLot);
        Page<ParkingLot> parkingLotPage = new PageImpl<>(parkingLotList);

        when(parkingLotService.getAllParkingLot(0,15)).thenReturn(parkingLotPage);
        ResultActions result = mockMvc.perform(get("/parkinglots")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
        .andExpect(jsonPath("$.content[0].location",is(parkingLot.getLocation())))
        .andExpect(jsonPath("$.content[0].name",is(parkingLot.getName())));
    }

    @Test
    public void should_get_parking_lot_by_name() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("Genrev");
        parkingLot.setCapacity(1);
        parkingLot.setLocation("Santa Rosa");

        when(parkingLotService.getParkingLotByName("Genrev")).thenReturn(parkingLot);
        ResultActions result = mockMvc.perform(get("/parkinglots/{name}","Genrev")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.location",is(parkingLot.getLocation())))
                .andExpect(jsonPath("$.name",is(parkingLot.getName())));
    }

    @Test
    public void should_not_get_parking_lot_when_invalid_name() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("Nicole");
        parkingLot.setCapacity(1);
        parkingLot.setLocation("Santa Rosa");

        when(parkingLotService.getParkingLotByName("Genrev")).thenThrow(NotFoundException.class);
        ResultActions result = mockMvc.perform(get("/parkinglots/{name}","Genrev")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    public void should_update_parking_lot_capacity() throws Exception {
        ParkingLot updatedParkingLotCapacity = new ParkingLot();
        updatedParkingLotCapacity.setCapacity(5);

        ParkingLot updatedParkingLot = new ParkingLot();
        updatedParkingLot.setName("Genrev");
        updatedParkingLot.setCapacity(5);
        updatedParkingLot.setLocation("Santa Rosa");

        when(parkingLotService.updateParkingLotCapacity(anyString(),any())).thenReturn(updatedParkingLot);
        ResultActions result = mockMvc.perform(patch("/parkinglots/{name}","Genrev")
                .content(objectMapper.writeValueAsString(updatedParkingLotCapacity))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
        .andExpect(jsonPath("$.capacity", is(updatedParkingLot.getCapacity())))
        .andExpect(jsonPath("$.name", is(updatedParkingLot.getName())));
    }

    @Test
    public void should_not_update_parking_lot_capacity_when_invalid_name() throws Exception {
        ParkingLot updatedParkingLotCapacity = new ParkingLot();
        updatedParkingLotCapacity.setCapacity(5);

        when(parkingLotService.updateParkingLotCapacity(anyString(),any())).thenThrow(NotFoundException.class);
        ResultActions result = mockMvc.perform(patch("/parkinglots/{name}","Genrev")
                .content(objectMapper.writeValueAsString(updatedParkingLotCapacity))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }
}
