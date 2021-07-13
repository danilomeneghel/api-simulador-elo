package br.com.elo.fixture.emissor;

import br.com.elo.dto.emissor.EmissorRequestCriteriaDTO;
import br.com.elo.dto.emissor.EmissorRequestDTO;
import br.com.elo.dto.emissor.EmissorResponseDTO;
import br.com.elo.fixture.Fixture;
import br.com.elo.rest.RestClientFixture;
import gherkin.deps.com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class EmissorFixture implements Fixture {

    private static String HOST = "localhost";
    private static int PORT = 8080;

    private static final String URL_API = "/api/emissores";
    private static  String COLLECTION = "emissor";

    @Autowired
    private  MongoOperations mongoOperations;

    @Autowired
    private RestClientFixture restClientFixture;


    @Override
    public void cleanup() {
        mongoOperations.dropCollection(COLLECTION);
        mongoOperations.createCollection(COLLECTION);
    }

    public ResponseEntity<EmissorResponseDTO> incluirEmissor(EmissorRequestDTO emissorRequestDTO) {
        Gson gson = new Gson();
        String emissorRequestJson = gson.toJson(emissorRequestDTO);
        return this.restClientFixture.request(HttpMethod.POST, emissorRequestJson, EmissorResponseDTO.class, URL_API);

    }

    public ResponseEntity<EmissorResponseDTO> atualizarEmissor(EmissorRequestDTO emissorRequestDTO, Integer codigoEmissor, Integer codigoBandeira) {
        Gson gson = new Gson();
        String emissorRequestJson = gson.toJson(emissorRequestDTO);
        return this.restClientFixture.request(HttpMethod.PUT, emissorRequestJson, EmissorResponseDTO.class, URL_API+"/"+codigoBandeira+"/"+codigoEmissor);
    }

    public ResponseEntity<EmissorResponseDTO> consultarEmissorId(String id) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, EmissorResponseDTO.class, URL_API +"/"+id);
    }

    public ResponseEntity<EmissorResponseDTO> consultarEmissorCodigo(Integer codigoBandeira, Integer codigoEmissor) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, EmissorResponseDTO.class, URL_API+"/cod/"+codigoBandeira+"/"+codigoEmissor);
    }

    public ResponseEntity<EmissorResponseDTO[]> buscaEmissor(EmissorRequestCriteriaDTO emissorRequestCriteriaDTO) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, EmissorResponseDTO[].class, URL_API,
                emissorRequestCriteriaDTO.getCodigoEmissor() == null ? null : emissorRequestCriteriaDTO.getCodigoEmissor().toString(),
                emissorRequestCriteriaDTO.getCodigoBandeira() == null ? null : emissorRequestCriteriaDTO.getCodigoBandeira().toString(),
                emissorRequestCriteriaDTO.getNomeEmissor(),
                emissorRequestCriteriaDTO.getCodigoProcessadora(),
                emissorRequestCriteriaDTO.getCodigoBandeira(),
                emissorRequestCriteriaDTO.getPlataforma());
    }

    public ResponseEntity<EmissorResponseDTO> getResultado() {
        return this.restClientFixture.getLastResponse();
    }

    public ResponseEntity<ArrayList<EmissorResponseDTO>> getResultadoBusca() {
        return this.restClientFixture.getLastResponse();
    }


}
