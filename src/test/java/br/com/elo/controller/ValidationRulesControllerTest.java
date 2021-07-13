package br.com.elo.controller;

import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.domain.Status;
import br.com.elo.domain.dto.request.ValidationRulesRequestDTO;
import br.com.elo.domain.dto.response.AmbienteResponseDTO;
import br.com.elo.domain.dto.response.ValidationRulesResponseDTO;
import br.com.elo.service.ValidationRuleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ValidationRulesControllerTest {

    static String VALIDATION_RULES_API = "/simulator-management/v1/validation-rules";

    @Autowired
    MockMvc mvc;

    @MockBean
    ValidationRuleService service;

    private ValidationRulesRequestDTO novaValidationRule = DadosMockUtil.criaNovaValidationRulesRequestDTO();

    @Test
    @DisplayName("Deve criar uma validationRules com sucesso")
    public void createValidationRulesTest() throws Exception {
        ValidationRulesRequestDTO dto = novaValidationRule;

        ValidationRulesResponseDTO validationRulesSalva = DadosMockUtil.validationRulesResponseDTO();

        BDDMockito.given(service.save(Mockito.any(ValidationRulesRequestDTO.class))).willReturn(validationRulesSalva);

        String json = new ObjectMapper().writeValueAsString(dto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(VALIDATION_RULES_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isCreated() )
                .andExpect( MockMvcResultMatchers.jsonPath("id").value(validationRulesSalva.getId()) )
                .andExpect( MockMvcResultMatchers.jsonPath("status").value(dto.getStatus().name()) )
                .andExpect( MockMvcResultMatchers.jsonPath("name").value(validationRulesSalva.getName()))
                .andExpect( MockMvcResultMatchers.jsonPath("validationBits[0].bitNumber").value(validationRulesSalva.getValidationBits().get(0).getBitNumber()));
    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não houver dados suficientes para criação da validationRules")
    public void verificaDadosIncompletosValidationRules() throws Exception {
        String json = new ObjectMapper().writeValueAsString(new ValidationRulesRequestDTO());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(VALIDATION_RULES_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isBadRequest() )
                .andExpect( MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(3)));
    }

    @Test
    @DisplayName("Deve retornar uma validation rule valida passando o Id")
    public void deveRetornarValidationRulePorId() throws Exception {
        String id = "5feb955b37b9fb7770ec3158";

        ValidationRulesResponseDTO validationRulesSalva = DadosMockUtil.validationRulesResponseDTO();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, VALIDATION_RULES_API+"/"+id)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findById(id)).willReturn(validationRulesSalva);

        final MvcResult mvcResult = this.mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("validationRulesSequence").value(validationRulesSalva.getValidationRulesSequence()))
                .andExpect(jsonPath("id").value(validationRulesSalva.getId()))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Deve retornar o resultado na busca através do método findAll")
    public void deveRetornarOResultadoDaBuscaPorTodosRegistros() throws Exception {

        ValidationRulesResponseDTO validationRulesSalva = DadosMockUtil.validationRulesResponseDTO();

        List<ValidationRulesResponseDTO> list = new ArrayList<>();
        list.add(validationRulesSalva);

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, VALIDATION_RULES_API)
                .queryParam("validationRulesSequence","1")
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findAll(BDDMockito.any())).willReturn(list);

        final MvcResult mvcResult = this.mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].validationRulesSequence").value(validationRulesSalva.getValidationRulesSequence()))
                .andExpect(jsonPath("$.[0].id").value(validationRulesSalva.getId()))
                .andExpect( MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andDo(print())
                .andReturn();
    }

}
