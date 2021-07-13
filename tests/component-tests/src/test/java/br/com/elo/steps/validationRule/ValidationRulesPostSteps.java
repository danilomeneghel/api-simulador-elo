package br.com.elo.steps.validationRule;


import br.com.elo.domain.Status;
import br.com.elo.dto.ValidationRulesRequestDTO;
import br.com.elo.dto.ValidationRulesResponseDTO;
import br.com.elo.fixture.ValidationRulesFixture;
import cucumber.api.DataTable;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ValidationRulesPostSteps {

    @Autowired
    private ValidationRulesFixture validationRuleFixture;

    ValidationRulesRequestDTO validationRulesRequest;
    ValidationRulesResponseDTO validationRulesResponse;


    @Dado("que possua uma validationRule com payload:")
    public void que_possua_uma_validationRule_com_payload(DataTable dataTable) {

        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            validationRulesRequest = ValidationRulesRequestDTO.builder().name(columns.get("name")).status(Status.valueOf(columns.get("status"))).build();
        }

        validationRulesRequest.setValidationBits(ValidationRulesCommonSteps.validationBitsRequestDTO);
    }

    @Quando("realizo um POST desta validationRule")
    public void realizo_um_post_desta_validationRule() {

        validationRulesResponse = validationRuleFixture.incluirvalidationRules(validationRulesRequest).getBody();
    }

    @Então("o response apresenta todos os campos referente a validationRule preenchidos corretamente")
    public void o_response_apresenta_todos_os_campos_referente_a_validationRule_preenchidos_corretamente() {

        assertNotNull(validationRulesResponse.getId());
        assertNotNull(validationRulesResponse.getValidationRulesSequence());
        assertNotNull(validationRulesResponse.getCreateDate());
        assertEquals(validationRulesResponse.getName(), validationRulesRequest.getName());
        assertEquals(validationRulesResponse.getStatus(), validationRulesRequest.getStatus());

    }


}
