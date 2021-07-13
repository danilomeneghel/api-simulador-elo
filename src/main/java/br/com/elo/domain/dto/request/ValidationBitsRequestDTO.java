package br.com.elo.domain.dto.request;

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

    @NotNull
    private Integer bitNumber;

    @NotNull
    private ValidationRuleConditions validationRule;

    private FillingMode fillingMode;

    private String value;
}
