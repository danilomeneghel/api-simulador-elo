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
public class MensagemISO8583Fixture implements Fixture{


    private static final String URL_API = "/api/mensagens";
    private static  String COLLECTION = "mensagem_iso8583";

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
     * Inclui uma mensagemISO8583 atraves de uma chamada post para a api bandeiras.
     * @return
     */
    public ResponseEntity<MensagemISO8583ResponseDTO> incluirMensagemISO8583(MensagemISO8583RequestDTO mensagemISO8583RequestDTO) {

        Gson gson = new Gson();
        String mensagemISO8583RequestJson = gson.toJson(mensagemISO8583RequestDTO);
        return this.restClientFixture.request(HttpMethod.POST, mensagemISO8583RequestJson, MensagemISO8583ResponseDTO.class, URL_API);
    }

    /***
     * Inclui uma mensagemISO8583 atraves de uma chamada post para a api mensagensISO8583.
     * @return
     */
    public ResponseEntity<MensagemISO8583ResponseDTO> atualizaMensagemISO8583(MensagemISO8583RequestDTO mensagemISO8583RequestDTO, Long mensagemSequence) {

        Gson gson = new Gson();
        String mensagemISO8583RequestJson = gson.toJson(mensagemISO8583RequestDTO);
        return this.restClientFixture.request(HttpMethod.PUT, mensagemISO8583RequestJson, MensagemISO8583ResponseDTO.class, URL_API+"/"+mensagemSequence);
    }

    /**
     * Consulta mensagemISO8583 por Id atraves de chamada get para a api mensagensISO8583.
     *
     * @return
     */
    public ResponseEntity<MensagemISO8583ResponseDTO> consultaMensagemISO8583Id(String id) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, MensagemISO8583ResponseDTO.class, URL_API +"/"+id);
    }

    /**
     * Consulta mensagemISO8583 por Codigo atraves de chamada get para a api mensagensISO8583.
     *
     * @return
     */
    public ResponseEntity<MensagemISO8583ResponseDTO> consultaMensagemISO8583Codigo(Long codigo) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, MensagemISO8583ResponseDTO.class, URL_API +"/seq/"+codigo);
    }

    /**
     * Consulta mensagensISO8583 por filtros de busca atraves de chamada getAll para a api mensagensISO8583.
     *
     * @return
     */
    public ResponseEntity<MensagemISO8583ResponseDTO[]> buscaMensagemISO8583(MensagemISO8583RequestCriteriaDTO mensagemISO8583RequestCriteriaDTO) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, MensagemISO8583ResponseDTO[].class, URL_API,
                mensagemISO8583RequestCriteriaDTO.getCodigoMensagem() == null ? null : mensagemISO8583RequestCriteriaDTO.getCodigoMensagem().toString(),
                mensagemISO8583RequestCriteriaDTO.getTipoMensagem() == null ? null : mensagemISO8583RequestCriteriaDTO.getTipoMensagem().toString(),
                mensagemISO8583RequestCriteriaDTO.getDescricao(),
                mensagemISO8583RequestCriteriaDTO.getId(),
                mensagemISO8583RequestCriteriaDTO.getCodigo());
    }

    /**
     * OBTEM o resultado da ultima chamada
     */
    public ResponseEntity<MensagemISO8583ResponseDTO> getResultado() {
        return this.restClientFixture.getLastResponse();
    }

    /**
     * OBTEM o resultado da ultima chamada de busca
     */
    public ResponseEntity<ArrayList<MensagemISO8583ResponseDTO>> getResultadoBusca() {
        return this.restClientFixture.getLastResponse();
    }

}
