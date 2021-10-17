package lesson5.dto;

import lombok.*;

@Data
public class ResponseFullError extends ResponseError {

    String error;
    String path;

}
