package net.saravana.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class ErrorDto {
    public ErrorDto(String unauthorizedPath) {
        String message;
    }
}
