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
public class ProtocoloFixture implements Fixture{

    private static final String URL_API = "/api/protocolos";
    private static  String COLLECTION = "protocolo";

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
     * Inclui uma protocolo atraves de uma chamada post para a api protocolos.
     * @return
     */
    public ResponseEntity<ProtocoloResponseDTO> incluirProtocolo(ProtocoloRequestDTO protocoloRequestDTO) {

        Gson gson = new Gson();
        String protocoloRequestJson = gson.toJson(protocoloRequestDTO);
        return this.restClientFixture.request(HttpMethod.POST, protocoloRequestJson, ProtocoloResponseDTO.class, URL_API);
    }

    /***
     * Inclui uma protocolo atraves de uma chamada post para a api protocolos.
     * @return
     */
    public ResponseEntity<ProtocoloResponseDTO> atualizaProtocolo(ProtocoloRequestDTO protocoloRequestDTO, Long codigo) {

        Gson gson = new Gson();
        String protocoloRequestJson = gson.toJson(protocoloRequestDTO);
        return this.restClientFixture.request(HttpMethod.PUT, protocoloRequestJson, ProtocoloResponseDTO.class, URL_API+"/"+codigo);
    }

    /**
     * Consulta protocolo por Id atraves de chamada get para a api protocolos.
     *
     * @return
     */
    public ResponseEntity<ProtocoloResponseDTO> consultaProtocoloId(String id) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, ProtocoloResponseDTO.class, URL_API +"/"+id);
    }

    /**
     * Consulta protocolo por Codigo atraves de chamada get para a api protocolos.
     *
     * @return
     */
    public ResponseEntity<ProtocoloResponseDTO> consultaProtocoloCodigo(Long codigo) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, ProtocoloResponseDTO.class, URL_API +"/seq/"+codigo);
    }

    /**
     * Consulta protocolo por filtros de busca atraves de chamada getAll para a api protocolos.
     *
     * @return
     */
    public ResponseEntity<ProtocoloResponseDTO[]> buscaProtocolo(ProtocoloRequestCriteriaDTO protocoloRequestCriteriaDTO) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, ProtocoloResponseDTO[].class, URL_API,
                protocoloRequestCriteriaDTO.getCodigo() == null ? null : protocoloRequestCriteriaDTO.getCodigo().toString(),
                protocoloRequestCriteriaDTO.getStatus() == null ? null : protocoloRequestCriteriaDTO.getStatus().toString(),
                protocoloRequestCriteriaDTO.getDescricao(),
                protocoloRequestCriteriaDTO.getId());
    }

    /**
     * OBTEM o resultado da ultima chamada
     */
    public ResponseEntity<ProtocoloResponseDTO> getResultado() {
        return this.restClientFixture.getLastResponse();
    }

    /**
     * OBTEM o resultado da ultima chamada de busca
     */
    public ResponseEntity<ArrayList<ProtocoloResponseDTO>> getResultadoBusca() {
        return this.restClientFixture.getLastResponse();
    }

}
