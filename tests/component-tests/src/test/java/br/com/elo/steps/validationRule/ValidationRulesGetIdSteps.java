package br.com.elo.steps.validationRule;


import br.com.elo.dto.ValidationRulesResponseDTO;
import br.com.elo.fixture.ValidationRulesFixture;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ValidationRulesGetIdSteps {

    @Autowired
    private ValidationRulesFixture validationRuleFixture;

    ValidationRulesResponseDTO validationRulesEsperado;
    ValidationRulesResponseDTO validationRuleResponse;


    @Quando("eu realizo uma busca de validationRule pelo endpoint de busca por id")
    public void eu_realizo_uma_busca_de_validationRule_pelo_endpoint_de_busca_por_id() {

        validationRulesEsperado = this.validationRuleFixture.getResultado().getBody();
        validationRuleResponse = validationRuleFixture.consultaValidationRulesPorId(validationRulesEsperado.getId()).getBody();
    }

    @Então("o response apresenta os campos da ValidationRule de acordo com a base de dados")
    public void o_response_apresenta_os_campos_da_ValidationRule_de_acordo_com_a_base_de_dados() {

        assertNotNull(validationRuleResponse);
        assertNotNull(validationRuleResponse.getId());
        assertNotNull(validationRuleResponse.getValidationRulesSequence());
        assertEquals(validationRuleResponse.getCreateDate(), validationRulesEsperado.getCreateDate());
        assertEquals(validationRuleResponse.getName(), validationRulesEsperado.getName());
        assertEquals(validationRuleResponse.getStatus(), validationRulesEsperado.getStatus());

    }

}
