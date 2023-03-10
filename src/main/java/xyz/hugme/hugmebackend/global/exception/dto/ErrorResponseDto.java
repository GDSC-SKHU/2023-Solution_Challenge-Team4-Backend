package xyz.hugme.hugmebackend.global.exception.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponseDto {
    private int statusCode;
    private String httpStatus;
    private String errorMessage;

    public ErrorResponseDto(int statusCode, HttpStatus httpStatus, String errorMessage) {
        this.statusCode = statusCode;
        this.httpStatus = httpStatus.getReasonPhrase();
        this.errorMessage = errorMessage;
    }
}
