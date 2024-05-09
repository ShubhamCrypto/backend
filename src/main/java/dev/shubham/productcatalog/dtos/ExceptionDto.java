package dev.shubham.productcatalog.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class ExceptionDto {
    private HttpStatus status;
    private String message;
    public ExceptionDto(HttpStatus status,String message){
        this.message=message;
        this.status=status;
    }
}
