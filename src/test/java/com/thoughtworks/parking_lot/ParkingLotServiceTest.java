package com.thoughtworks.parking_lot;

import com.thoughtworks.parking_lot.Repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.Service.ParkingLotService;
import com.thoughtworks.parking_lot.core.ParkingLot;
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

}
