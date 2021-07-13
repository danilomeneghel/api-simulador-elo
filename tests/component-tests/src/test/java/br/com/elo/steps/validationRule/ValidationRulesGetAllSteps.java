package br.com.elo.steps.validationRule;

import br.com.elo.dto.AmbienteRequestCriteriaDTO;
import br.com.elo.dto.ValidationRulesRequestCriteriaDTO;
import br.com.elo.dto.ValidationRulesResponseDTO;
import br.com.elo.fixture.ValidationRulesFixture;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ValidationRulesGetAllSteps {

    ValidationRulesResponseDTO validationRulesEsperado;
    List<ValidationRulesResponseDTO> validationRulesResponse;

    @Autowired
    private ValidationRulesFixture validationRuleFixture;

    @Quando("^eu realizo uma busca por validationRulesSequence$")
    public void eu_realizo_uma_busca_por_ambienteSequence() throws Exception {
        validationRulesEsperado = this.validationRuleFixture.getResultado().getBody();
        validationRulesResponse = Arrays.asList(validationRuleFixture.buscaValidationRule(ValidationRulesRequestCriteriaDTO
                .builder()
                .validationRulesSequence(validationRulesEsperado.getValidationRulesSequence())
                .build()).getBody());
    }

    @Então("^o response apresenta os campos da ValidationRule corretamente conforme a base de dados$")
    public void o_response_apresenta_os_campos_da_ValidationRule_corretamente_conforme_a_base_de_dados() throws Exception {
        assertEquals(validationRulesResponse.get(0).getValidationRulesSequence(), validationRulesEsperado.getValidationRulesSequence());
        assertEquals(validationRulesResponse.get(0).getId(), validationRulesEsperado.getId());
        assertEquals(validationRulesResponse.get(0).getName(), validationRulesEsperado.getName());
        assertEquals(validationRulesResponse.get(0).getStatus().getDescricao(), validationRulesEsperado.getStatus().getDescricao());
        assertEquals(validationRulesResponse.get(0).getValidationBits().get(0).getBitNumber(), validationRulesEsperado.getValidationBits().get(0).getBitNumber());
        assertEquals(validationRulesResponse.get(0).getValidationBits().get(0).getValidationRule(), validationRulesEsperado.getValidationBits().get(0).getValidationRule());
        assertEquals(validationRulesResponse.get(0).getValidationBits().get(0).getFillingMode(), validationRulesEsperado.getValidationBits().get(0).getFillingMode());
        assertEquals(validationRulesResponse.get(0).getValidationBits().get(0).getValue(), validationRulesEsperado.getValidationBits().get(0).getValue());
    }
}
