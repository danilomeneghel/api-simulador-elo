package br.com.elo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO implements Serializable {
    public String timestamp;
    public int code;
    public String status;
    public String details;
    public List<String> errors;
}
