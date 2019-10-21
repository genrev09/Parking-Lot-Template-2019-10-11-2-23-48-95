package com.thoughtworks.parking_lot.Advice;

import com.thoughtworks.parking_lot.core.ParkingLotException;
import javassist.NotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.RollbackException;

@ControllerAdvice
public class ControllerAdviceHandler {
    public static final String PARKING_LOT_NAME_ALREADY_EXISTS = ". Parking Lot name already exists.";
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

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ParkingLotException notFoundException(ConstraintViolationException exception){
        parkingLotException = new ParkingLotException();
        parkingLotException.setCode(400);
        parkingLotException.setMessage(exception.getMessage() + PARKING_LOT_NAME_ALREADY_EXISTS);
        return parkingLotException;
    }

    @ResponseBody
    @ExceptionHandler(RollbackException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ParkingLotException notFoundException(RollbackException exception){
        parkingLotException = new ParkingLotException();
        parkingLotException.setCode(400);
        parkingLotException.setMessage(exception.getMessage() + ". Capacity must be greater than 0.");
        return parkingLotException;
    }
}
