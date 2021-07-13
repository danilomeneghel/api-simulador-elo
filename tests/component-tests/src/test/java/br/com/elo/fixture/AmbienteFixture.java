package br.com.elo.fixture;

import br.com.elo.dto.*;
import br.com.elo.rest.RestClientFixture;
import gherkin.deps.com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AmbienteFixture implements Fixture {

    private static String HOST = "localhost";
    private static int PORT = 8080;

    private static final String URL_API = "/api/ambientes";
    private static  String COLLECTION = "ambiente";

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private RestClientFixture restClientFixture;


    @Override
    public void cleanup() {
        mongoOperations.dropCollection(COLLECTION);
        mongoOperations.createCollection(COLLECTION);
    }


    /***
     * Inclui um ambiente atraves de uma chamada POST para a api ambientes.
     * @return
     */
    public ResponseEntity<AmbienteResponseDTO> incluirAmbiente(AmbienteRequestDTO ambienteRequestDTO) {

        Gson gson = new Gson();
        String ambienteRequestJson = gson.toJson(ambienteRequestDTO);
        return this.restClientFixture.request(HttpMethod.POST, ambienteRequestJson, AmbienteResponseDTO.class, URL_API);
    }

    /***
     * Atualiza um ambiente a partir de uma chamada PUT para a api ambientes.
     * @return
     */
    public ResponseEntity<AmbienteResponseDTO> atualizaAmbiente(AmbienteRequestDTO ambienteRequestDTO, Long ambienteSequence) {

        Gson gson = new Gson();
        String ambienteRequestJson = gson.toJson(ambienteRequestDTO);
        return this.restClientFixture.request(HttpMethod.PUT, ambienteRequestJson, AmbienteResponseDTO.class, URL_API+"/" + ambienteSequence);
    }

    /**
     * Consulta ambiente por Id atraves de chamada GET para a api ambientes.
     *
     * @return
     */
    public ResponseEntity<AmbienteResponseDTO> consultaAmbienteId(String id) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, AmbienteResponseDTO.class, URL_API +"/"+id);
    }

    /**
     * Consulta ambiente por ambienteSequence atraves de chamada get para a api ambientes.
     *
     * @return
     */
    public ResponseEntity<AmbienteResponseDTO> consultaAmbientePorAmbienteSequence(Long ambienteSequence) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, AmbienteResponseDTO.class, URL_API +"/seq/" + ambienteSequence);
    }

    /**
     * Consulta ambientes por filtros de busca atraves de chamada getAll para a api ambientes.
     *
     * @return
     */
    public ResponseEntity<AmbienteResponseDTO[]> buscaAmbiente(AmbienteRequestCriteriaDTO ambienteRequestCriteriaDTO) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, AmbienteResponseDTO[].class, URL_API,
                ambienteRequestCriteriaDTO.getAmbienteSequence() == null ? null : ambienteRequestCriteriaDTO.getAmbienteSequence().toString(),
                ambienteRequestCriteriaDTO.getDescricao(),
                ambienteRequestCriteriaDTO.getId());
    }

    /**
     * OBTEM o resultado da ultima chamada
     */
    public ResponseEntity<AmbienteResponseDTO> getResultado() {
        return this.restClientFixture.getLastResponse();
    }

    /**
     * OBTEM o resultado da ultima chamada de busca
     */
    public ResponseEntity<ArrayList<AmbienteResponseDTO>> getResultadoBusca() {
        return this.restClientFixture.getLastResponse();
    }
}

