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
public class FluxoTransacionalFixture implements Fixture{

    private static String HOST = "localhost";
    private static int PORT = 8080;

    private static final String URL_API = "/api/fluxostransacionais";
    private static  String COLLECTION = "fluxo_transacional";

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
     * Inclui um fluxo transacional atraves de uma chamada post para a api fluxos transacionais.
     * @return
     */
    public ResponseEntity<FluxoTransacionalResponseDTO> incluirFluxoTransacional(FluxoTransacionalRequestDTO fluxoTransacionalRequestDTO) {

        Gson gson = new Gson();
        String fluxoTransacionalRequestJson = gson.toJson(fluxoTransacionalRequestDTO);
        return this.restClientFixture.request(HttpMethod.POST, fluxoTransacionalRequestJson, FluxoTransacionalResponseDTO.class, URL_API);
    }

    /***
     * Faz uma chamada de fluxo transacional invalido.
     * @return
     */
    public ResponseEntity<ErrorResponseDTO> incluirFluxoTransacionalInvalido(FluxoTransacionalRequestDTO fluxoTransacionalRequestDTO) {

        Gson gson = new Gson();
        String fluxoTransacionalRequestJson = gson.toJson(fluxoTransacionalRequestDTO);
        return this.restClientFixture.request(HttpMethod.POST, fluxoTransacionalRequestJson, ErrorResponseDTO.class, URL_API);
    }

    /***
     * Inclui um fluxo transacional atraves de uma chamada post para a api fluxos transacionais.
     * @return
     */
    public ResponseEntity<FluxoTransacionalResponseDTO> atualizaFluxoTransacional(FluxoTransacionalRequestDTO fluxoTransacionalRequestDTO, Long fluxoTransacionalSequence) {

        Gson gson = new Gson();
        String fluxoTransacionalRequestJson = gson.toJson(fluxoTransacionalRequestDTO);
        return this.restClientFixture.request(HttpMethod.PUT, fluxoTransacionalRequestJson, FluxoTransacionalResponseDTO.class, URL_API+"/"+ fluxoTransacionalSequence);
    }

    /**
     * Consulta um fluxo transacional por Id atraves de chamada get para a api fluxos transacionais.
     *
     * @return
     */
    public ResponseEntity<FluxoTransacionalResponseDTO> consultaFluxoTransacionalId(String id) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, FluxoTransacionalResponseDTO.class, URL_API +"/"+id);
    }

    /**
     * Consulta bandeira por FluxoTransacionalSequence atraves de chamada get para a api fluxos transacionais.
     *
     * @return
     */
    public ResponseEntity<FluxoTransacionalResponseDTO> consultaFluxoTransacionalPorFluxoTransacionalSequence(Long fluxoTransacionalSequence) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, FluxoTransacionalResponseDTO.class, URL_API +"/seq/"+ fluxoTransacionalSequence);
    }

    /**
     * Consulta fluxo transacional por filtros de busca atraves de chamada getAll para a api fluxos transacionais.
     *
     * @return
     */
    public ResponseEntity<FluxoTransacionalResponseDTO[]> buscaFluxoTransacional(FluxoTransacionalRequestCriteriaDTO fluxoTransacionalRequestCriteriaDTO) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, FluxoTransacionalResponseDTO[].class, URL_API,
                fluxoTransacionalRequestCriteriaDTO.getMensagemSolicitacaoPernaUmSequence() == null ? null : fluxoTransacionalRequestCriteriaDTO.getMensagemSolicitacaoPernaUmSequence().toString(),
                fluxoTransacionalRequestCriteriaDTO.getMensagemSolicitacaoPernaDoisSequence() == null ? null : fluxoTransacionalRequestCriteriaDTO.getMensagemSolicitacaoPernaDoisSequence().toString(),
                fluxoTransacionalRequestCriteriaDTO.getMensagemRespostaPernaTresSequence() == null ? null : fluxoTransacionalRequestCriteriaDTO.getMensagemRespostaPernaTresSequence().toString(),
                fluxoTransacionalRequestCriteriaDTO.getMensagemRespostaPernaQuatroSequence() == null ? null : fluxoTransacionalRequestCriteriaDTO.getMensagemRespostaPernaQuatroSequence().toString(),
                fluxoTransacionalRequestCriteriaDTO.getFluxoTransacionalSequence() == null ? null : fluxoTransacionalRequestCriteriaDTO.getFluxoTransacionalSequence().toString(),
                fluxoTransacionalRequestCriteriaDTO.getDescricao(),
                fluxoTransacionalRequestCriteriaDTO.getId());
    }

    /**
     * OBTEM o resultado da ultima chamada
     */
    public ResponseEntity<FluxoTransacionalResponseDTO> getResultado() {
        return this.restClientFixture.getLastResponse();
    }

    /**
     * OBTEM o resultado da ultima chamada de busca
     */
    public ResponseEntity<ArrayList<FluxoTransacionalResponseDTO>> getResultadoBusca() {
        return this.restClientFixture.getLastResponse();
    }

}
