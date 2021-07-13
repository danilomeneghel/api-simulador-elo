package br.com.elo.fixture.cartao;

import br.com.elo.dto.BandeiraRequestCriteriaDTO;
import br.com.elo.dto.BandeiraResponseDTO;
import br.com.elo.dto.cartao.CartaoRequestCriteriaDTO;
import br.com.elo.dto.cartao.CartaoRequestDTO;
import br.com.elo.dto.cartao.CartaoResponseDTO;
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
public class CartaoFixture implements Fixture {

    private static String HOST = "localhost";
    private static int PORT = 8080;

    private static final String URL_API = "/api/cartoes";
    private static  String COLLECTION = "cartao";

    @Autowired
    private  MongoOperations mongoOperations;

    @Autowired
    private RestClientFixture restClientFixture;


    @Override
    public void cleanup() {
        mongoOperations.dropCollection(COLLECTION);
        mongoOperations.createCollection(COLLECTION);
    }

    public ResponseEntity<CartaoResponseDTO> incluirCartao(CartaoRequestDTO cartaoRequestDTO) {
        Gson gson = new Gson();
        String cartaoRequestJson = gson.toJson(cartaoRequestDTO);
        return this.restClientFixture.request(HttpMethod.POST, cartaoRequestJson, CartaoResponseDTO.class, URL_API);

    }

    public ResponseEntity<CartaoResponseDTO> atualizarCartao(CartaoRequestDTO cartaoRequestDTO, String pan) {
        Gson gson = new Gson();
        String cartaoRequestJson = gson.toJson(cartaoRequestDTO);
        return this.restClientFixture.request(HttpMethod.PUT, cartaoRequestJson, CartaoResponseDTO.class, URL_API+"/"+pan);
    }

    public ResponseEntity<CartaoResponseDTO> consultarCartaoId(String id) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, CartaoResponseDTO.class, URL_API +"/"+id);
    }

    public ResponseEntity<CartaoResponseDTO> consultarCartaoPan(String pan) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, CartaoResponseDTO.class, URL_API +"/cod/"+pan);
    }


    public ResponseEntity<CartaoResponseDTO> consultarEmissorCodigo(Long codigo) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, CartaoResponseDTO.class, URL_API +"/seq/"+codigo);
    }

    public ResponseEntity<CartaoResponseDTO> getResultado() {
        return this.restClientFixture.getLastResponse();
    }

    public ResponseEntity<ArrayList<CartaoResponseDTO>> getResultadoBusca() {
        return this.restClientFixture.getLastResponse();
    }

    /**
     * Consulta cartoes por filtros de busca atraves de chamada getAll para a api cartoes.
     *
     * @return
     */
    public ResponseEntity<CartaoResponseDTO[]> buscaCartao(CartaoRequestCriteriaDTO cartaoRequestCriteriaDTO) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, CartaoResponseDTO[].class, URL_API,
                cartaoRequestCriteriaDTO.getId(),
                cartaoRequestCriteriaDTO.getCodigoBandeira() == null ? null : cartaoRequestCriteriaDTO.getCodigoBandeira().toString(),
                cartaoRequestCriteriaDTO.getCodigoEmissor() == null ? null : cartaoRequestCriteriaDTO.getCodigoEmissor().toString(),
                cartaoRequestCriteriaDTO.getTipoCartao(),
                cartaoRequestCriteriaDTO.getNomeCartao(),
                cartaoRequestCriteriaDTO.getPan(),
                cartaoRequestCriteriaDTO.getPin(),
                cartaoRequestCriteriaDTO.getCardSequenceNumber(),
                cartaoRequestCriteriaDTO.getCve2(),
                cartaoRequestCriteriaDTO.getDataExpiracao(),
                cartaoRequestCriteriaDTO.getTokenCartao(),
                cartaoRequestCriteriaDTO.getApplicationIdentifier(),
                cartaoRequestCriteriaDTO.getCardAppInterchangeProfile(),
                cartaoRequestCriteriaDTO.getIssuerDiscrData(),
                cartaoRequestCriteriaDTO.getCardAppTransactionCounter(),
                cartaoRequestCriteriaDTO.getTrack1(),
                cartaoRequestCriteriaDTO.getTrack2(),
                cartaoRequestCriteriaDTO.getTrack2EquivalentData(),
                cartaoRequestCriteriaDTO.getCvnCartao(),
                cartaoRequestCriteriaDTO.getCvr(),
                cartaoRequestCriteriaDTO.getDkiKdi()
                );
    }

}
