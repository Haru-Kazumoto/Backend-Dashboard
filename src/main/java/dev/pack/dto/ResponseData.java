package dev.pack.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResponseData<T>{
    List<String> message = new LinkedList<>();
    HttpStatus status;
    T payload;
}
