package br.com.elo.steps.validationRule;


import br.com.elo.dto.ValidationBitsRequestDTO;
import br.com.elo.dto.ValidationRulesRequestDTO;
import br.com.elo.fixture.ValidationRulesFixture;
import cucumber.api.java.pt.Dado;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ValidationRulesCommonSteps {

    @Autowired
    private ValidationRulesFixture validationRuleFixture;

    public static List<ValidationBitsRequestDTO> validationBitsRequestDTO;

    @Dado("que possua uma lista validationBitRequestDTO:")
    public static void que_possua_uma_lista_validationBitRequestDTO(List<ValidationBitsRequestDTO> validationBitRequestDTO) {
        validationBitsRequestDTO = validationBitRequestDTO;
    }

    @Dado("que possua uma validationRules cadastrada:")
    public void que_possua_uma_validationRules_cadastrada(List<ValidationRulesRequestDTO> validationRulesDTOList) {

        for (ValidationRulesRequestDTO validationRulesDTO : validationRulesDTOList) {
            validationRulesDTO.setValidationBits(validationBitsRequestDTO);
            validationRuleFixture.incluirvalidationRules(validationRulesDTO);
        }
    }


}
