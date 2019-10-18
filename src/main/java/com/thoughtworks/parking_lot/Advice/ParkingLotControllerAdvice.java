package com.thoughtworks.parking_lot.Advice;

import com.thoughtworks.parking_lot.core.ParkingLotException;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ParkingLotControllerAdvice {
    private ParkingLotException parkingLotException;

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ParkingLotException notFoundException(NotFoundException exception){
        parkingLotException = new ParkingLotException();
        parkingLotException.setCode(404);
        parkingLotException.setMessage(exception.getMessage());
        return parkingLotException;
    }
}
