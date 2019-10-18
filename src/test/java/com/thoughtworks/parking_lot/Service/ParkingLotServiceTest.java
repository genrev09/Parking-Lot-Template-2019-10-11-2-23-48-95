package com.thoughtworks.parking_lot.Service;

import com.thoughtworks.parking_lot.Repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.core.ParkingLot;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingLotServiceTest {

    @MockBean
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private ParkingLotService parkingLotService;

    @Test
    public void should_add_parking_lot() {

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("Genrev");
        parkingLot.setCapacity(1);
        parkingLot.setLocation("Santa Rosa");

        when(parkingLotRepository.save(parkingLot)).thenReturn(parkingLot);

        Assertions.assertThat(parkingLotService.addParkingLot(parkingLot)).isEqualTo(true);
    }

    @Test
    public void should_not_add_existing_parking_lot_name() {

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("Genrev");
        parkingLot.setCapacity(1);
        parkingLot.setLocation("Santa Rosa");

        when(parkingLotRepository.save(parkingLot)).thenReturn(null);

        Assertions.assertThat(parkingLotService.addParkingLot(parkingLot)).isEqualTo(false);
    }

    @Test
    public void should_delete_parking_lot() {
        List<ParkingLot> parkingLotList = new ArrayList<>();

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("Genrev");
        parkingLot.setCapacity(1);
        parkingLot.setLocation("Santa Rosa");

        parkingLotList.add(parkingLot);

        when(parkingLotRepository.findByName("Genrev")).thenReturn(parkingLot);
        when(parkingLotRepository.findAll()).thenReturn(parkingLotList);

        Assertions.assertThat(parkingLotService.deleteParkingLot("Genrev")).isEqualTo(true);
    }

}
