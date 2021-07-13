package br.com.elo.dto;

import br.com.elo.domain.FillingMode;
import br.com.elo.domain.ValidationRuleConditions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidationBitsRequestDTO {

    private Integer bitNumber;
    private ValidationRuleConditions validationRule;
    private FillingMode fillingMode;
    private String value;
}
