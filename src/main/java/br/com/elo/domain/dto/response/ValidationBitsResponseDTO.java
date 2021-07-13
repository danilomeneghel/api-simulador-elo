package br.com.elo.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidationBitsResponseDTO {

    private Integer bitNumber;
    private String validationRule;
    private String fillingMode;
    private String value;
}
