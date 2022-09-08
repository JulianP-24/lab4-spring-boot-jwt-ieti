package edu.escuelaing.ieti.lab2.exception;

import edu.escuelaing.ieti.lab2.enums.ErrorCodeEnum;
import edu.escuelaing.ieti.lab2.dto.ServerErrorResponseDto;
import org.springframework.http.HttpStatus;
import javax.ws.rs.InternalServerErrorException;

public class InvalidCredentialsException extends InternalServerErrorException
{
    public InvalidCredentialsException() {
        super();
        new ServerErrorResponseDto("User not found", ErrorCodeEnum.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
