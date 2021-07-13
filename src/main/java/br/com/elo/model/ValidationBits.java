package br.com.elo.model;

import br.com.elo.domain.FillingMode;
import br.com.elo.domain.ValidationRuleConditions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidationBits {

    private Integer bitNumber;

    @Enumerated(EnumType.STRING)
    private ValidationRuleConditions validationRule;

    private FillingMode fillingMode;

    private String value;
}
