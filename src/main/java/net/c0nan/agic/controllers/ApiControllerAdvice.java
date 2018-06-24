package net.c0nan.agic.controllers;

import net.c0nan.agic.exception.NotFoundException;
import net.c0nan.agic.exception.ValidationException;
import net.c0nan.agic.models.error.notfound.NotFoundErrorDetail;
import net.c0nan.agic.models.error.notfound.ToDoItemNotFoundError;
import net.c0nan.agic.models.error.validation.ToDoItemValidationError;
import net.c0nan.agic.models.error.validation.ValidationErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity handleNotFound(final Exception ex) {
        ToDoItemNotFoundError error = new ToDoItemNotFoundError();
        error.setName("NotFoundError");
        error.getDetails().add(new NotFoundErrorDetail(ex.getLocalizedMessage()));
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity handleValidation(final Exception ex) {
        ToDoItemValidationError error = new ToDoItemValidationError();
        error.setName("ValidationError");
        error.getDetails().add(new ValidationErrorDetail("params", "input", ex.getLocalizedMessage(), ""));
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

}
