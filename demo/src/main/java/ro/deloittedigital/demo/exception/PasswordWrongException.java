package ro.deloittedigital.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Wrong password!")
public class PasswordWrongException extends RuntimeException{
}
