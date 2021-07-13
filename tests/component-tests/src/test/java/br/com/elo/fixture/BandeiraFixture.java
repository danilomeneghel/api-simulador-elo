package br.com.elo.fixture;


import br.com.elo.dto.BandeiraRequestCriteriaDTO;
import br.com.elo.dto.BandeiraRequestDTO;
import br.com.elo.dto.BandeiraResponseDTO;
import br.com.elo.rest.RestClientFixture;
import gherkin.deps.com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BandeiraFixture implements Fixture{

    private static String HOST = "localhost";
    private static int PORT = 8080;

    private static final String URL_API = "/api/bandeiras";
    private static  String COLLECTION = "bandeira";

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
     * Inclui uma bandeira atraves de uma chamada post para a api bandeiras.
     * @return
     */
    public ResponseEntity<BandeiraResponseDTO> incluirBandeira(BandeiraRequestDTO bandeiraRequestDTO) {

        Gson gson = new Gson();
        String bandeiraRequestJson = gson.toJson(bandeiraRequestDTO);
        return this.restClientFixture.request(HttpMethod.POST, bandeiraRequestJson, BandeiraResponseDTO.class, URL_API);
    }

    /***
     * Inclui uma bandeira atraves de uma chamada post para a api bandeiras.
     * @return
     */
    public ResponseEntity<BandeiraResponseDTO> atualizaBandeira(BandeiraRequestDTO bandeiraRequestDTO, Integer codigoBandeira) {

        Gson gson = new Gson();
        String bandeiraRequestJson = gson.toJson(bandeiraRequestDTO);
        return this.restClientFixture.request(HttpMethod.PUT, bandeiraRequestJson, BandeiraResponseDTO.class, URL_API+"/"+codigoBandeira);
    }

    /**
     * Consulta bandeira por Id atraves de chamada get para a api bandeiras.
     *
     * @return
     */
    public ResponseEntity<BandeiraResponseDTO> consultaBandeiraId(String id) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, BandeiraResponseDTO.class, URL_API +"/"+id);
    }

    /**
     * Consulta bandeira por Codigo atraves de chamada get para a api bandeiras.
     *
     * @return
     */
    public ResponseEntity<BandeiraResponseDTO> consultaBandeiraCodigo(Integer codigoBandeira) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, BandeiraResponseDTO.class, URL_API +"/cod/"+codigoBandeira);
    }

    /**
     * Consulta bandeiras por filtros de busca atraves de chamada getAll para a api bandeiras.
     *
     * @return
     */
    public ResponseEntity<BandeiraResponseDTO[]> buscaBandeira(BandeiraRequestCriteriaDTO bandeiraRequestCriteriaDTO) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, BandeiraResponseDTO[].class, URL_API,
                bandeiraRequestCriteriaDTO.getCodigoBandeira() == null ? null : bandeiraRequestCriteriaDTO.getCodigoBandeira().toString(),
                bandeiraRequestCriteriaDTO.getDescricao(),
                bandeiraRequestCriteriaDTO.getId());
    }

    /**
     * OBTEM o resultado da ultima chamada
     */
    public ResponseEntity<BandeiraResponseDTO> getResultado() {
        return this.restClientFixture.getLastResponse();
    }

    /**
     * OBTEM o resultado da ultima chamada de busca
     */
    public ResponseEntity<ArrayList<BandeiraResponseDTO>> getResultadoBusca() {
        return this.restClientFixture.getLastResponse();
    }

}
