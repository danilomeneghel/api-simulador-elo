package br.com.elo.fixture;


import br.com.elo.dto.ValidationRulesRequestCriteriaDTO;
import br.com.elo.dto.ValidationRulesRequestDTO;
import br.com.elo.dto.ValidationRulesResponseDTO;
import br.com.elo.rest.RestClientFixture;
import gherkin.deps.com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ValidationRulesFixture implements Fixture{

    private static String HOST = "localhost";
    private static int PORT = 8080;

    private static final String URL_API = "/simulator-management/v1/validation-rules";
    private static  String COLLECTION = "validation_rules";

    @Autowired
    private  MongoOperations mongoOperations;

    @Autowired
    private RestClientFixture restClientFixture;


    @Override
    public void cleanup() {
        mongoOperations.dropCollection(COLLECTION);
        mongoOperations.createCollection(COLLECTION);
    }


    /***
     * Inclui um validationRule atraves de uma chamada post para a api validationRules.
     * @return
     */
    public ResponseEntity<ValidationRulesResponseDTO> incluirvalidationRules(ValidationRulesRequestDTO validationRequestDTO) {

        Gson gson = new Gson();
        String validationRulesJson = gson.toJson(validationRequestDTO);
        return this.restClientFixture.request(HttpMethod.POST, validationRulesJson, ValidationRulesResponseDTO.class, URL_API);
    }

    /**
     * Consulta validationRule por Id atraves de chamada get para a api validationRules.
     *
     * @return
     */
    public ResponseEntity<ValidationRulesResponseDTO> consultaValidationRulesPorId(String id) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, ValidationRulesResponseDTO.class, URL_API +"/"+id);
    }

    /**
     * Consulta bandeiras por filtros de busca atraves de chamada getAll para a api bandeiras.
     *
     * @return
     */
    public ResponseEntity<ValidationRulesResponseDTO[]> buscaValidationRule(ValidationRulesRequestCriteriaDTO bandeiraRequestCriteriaDTO) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, ValidationRulesResponseDTO[].class, URL_API,
                bandeiraRequestCriteriaDTO.getValidationRulesSequence() == null ? null : bandeiraRequestCriteriaDTO.getValidationRulesSequence().toString(),
                bandeiraRequestCriteriaDTO.getName(),
                bandeiraRequestCriteriaDTO.getId());
    }

    /**
     * OBTEM o resultado da ultima chamada
     */
    public ResponseEntity<ValidationRulesResponseDTO> getResultado() {
        return this.restClientFixture.getLastResponse();
    }

    /**
     * OBTEM o resultado da ultima chamada de busca
     */
    public ResponseEntity<ArrayList<ValidationRulesResponseDTO>> getResultadoBusca() {
        return this.restClientFixture.getLastResponse();
    }

}
