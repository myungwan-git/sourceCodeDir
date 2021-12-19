package hello.exceptionreview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "잘못된 요청 오류 BadRequestException.class")
public class BadRequestException extends RuntimeException {
}
