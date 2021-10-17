package lesson5.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@ToString
public class ResponseError {
    Integer status;
    String message;
    Date timestamp;

}
