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
public class CredenciadorFixture implements Fixture{

    private static String HOST = "localhost";
    private static int PORT = 8080;

    private static final String URL_API = "/api/credenciadores";
    private static  String COLLECTION = "credenciador";

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
     * Inclui uma credenciador atraves de uma chamada post para a api credenciadores.
     * @return
     */
    public ResponseEntity<CredenciadorResponseDTO> incluirCredenciador(CredenciadorRequestDTO credenciadorRequestDTO) {

        Gson gson = new Gson();
        String credenciadorRequestJson = gson.toJson(credenciadorRequestDTO);
        return this.restClientFixture.request(HttpMethod.POST, credenciadorRequestJson, CredenciadorResponseDTO.class, URL_API);
    }

    /***
     * Inclui uma credenciador atraves de uma chamada post para a api credenciadores.
     * @return
     */
    public ResponseEntity<CredenciadorResponseDTO> atualizaCredenciador(CredenciadorRequestDTO credenciadorRequestDTO, Long codigo) {

        Gson gson = new Gson();
        String credenciadorRequestJson = gson.toJson(credenciadorRequestDTO);
        return this.restClientFixture.request(HttpMethod.PUT, credenciadorRequestJson, CredenciadorResponseDTO.class, URL_API+"/"+codigo);
    }

    /**
     * Consulta credenciador por Id atraves de chamada get para a api credenciadores.
     *
     * @return
     */
    public ResponseEntity<CredenciadorResponseDTO> consultaCredenciadorId(String id) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, CredenciadorResponseDTO.class, URL_API +"/"+id);
    }

    /**
     * Consulta credenciador por Codigo atraves de chamada get para a api credenciadores.
     *
     * @return
     */
    public ResponseEntity<CredenciadorResponseDTO> consultaCredenciadorCodigo(Long codigo) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, CredenciadorResponseDTO.class, URL_API +"/seq/"+codigo);
    }

    /**
     * Consulta credenciadores por filtros de busca atraves de chamada getAll para a api credenciadores.
     *
     * @return
     */
    public ResponseEntity<CredenciadorResponseDTO[]> buscaCredenciador(CredenciadorRequestCriteriaDTO credenciadorRequestCriteriaDTO) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, CredenciadorResponseDTO[].class, URL_API,
                credenciadorRequestCriteriaDTO.getCredenciadorCodigo() == null ? null : credenciadorRequestCriteriaDTO.getCredenciadorCodigo().toString(),
                credenciadorRequestCriteriaDTO.getCodigo() == null ? null : credenciadorRequestCriteriaDTO.getCodigo().toString(),
                credenciadorRequestCriteriaDTO.getNome(),
                credenciadorRequestCriteriaDTO.getId(),
                credenciadorRequestCriteriaDTO.getStatus());
    }

    /**
     * OBTEM o resultado da ultima chamada
     */
    public ResponseEntity<CredenciadorResponseDTO> getResultado() {
        return this.restClientFixture.getLastResponse();
    }

    /**
     * OBTEM o resultado da ultima chamada de busca
     */
    public ResponseEntity<ArrayList<CredenciadorResponseDTO>> getResultadoBusca() {
        return this.restClientFixture.getLastResponse();
    }

}
