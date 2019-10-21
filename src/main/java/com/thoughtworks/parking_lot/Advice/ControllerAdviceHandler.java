package com.thoughtworks.parking_lot.Advice;

import com.thoughtworks.parking_lot.core.ModelException;
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
    private ModelException modelException;

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ModelException notFoundException(NotFoundException exception){
        modelException = new ModelException();
        modelException.setCode(404);
        modelException.setMessage(exception.getMessage());
        return modelException;
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ModelException constraintViolationException(ConstraintViolationException exception){
        modelException = new ModelException();
        modelException.setCode(400);
        modelException.setMessage(exception.getMessage() + PARKING_LOT_NAME_ALREADY_EXISTS);
        return modelException;
    }

    @ResponseBody
    @ExceptionHandler(RollbackException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ModelException rollbackException(RollbackException exception){
        modelException = new ModelException();
        modelException.setCode(400);
        modelException.setMessage(exception.getMessage() + ". Capacity must be greater than 0.");
        return modelException;
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE)
    public ModelException Exception(Exception exception){
        modelException = new ModelException();
        modelException.setCode(503);
        modelException.setMessage(exception.getMessage());
        return modelException;
    }
}
