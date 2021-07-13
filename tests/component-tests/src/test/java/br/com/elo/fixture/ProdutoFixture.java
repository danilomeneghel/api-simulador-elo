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
public class ProdutoFixture implements Fixture{


    private static final String URL_API = "/api/produtos";
    private static  String COLLECTION = "produto";

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
     * Inclui uma produto atraves de uma chamada post para a api produtos.
     * @return
     */
    public ResponseEntity<ProdutoResponseDTO> incluirProduto(ProdutoRequestDTO produtoRequestDTO) {

        Gson gson = new Gson();
        String produtoRequestJson = gson.toJson(produtoRequestDTO);
        return this.restClientFixture.request(HttpMethod.POST, produtoRequestJson, ProdutoResponseDTO.class, URL_API);
    }

    /***
     * Inclui um produto atraves de uma chamada post para a api produtos.
     * @return
     */
    public ResponseEntity<ProdutoResponseDTO> atualizaProduto(ProdutoRequestDTO produtoRequestDTO, Integer codigoBandeira, Integer codigoProduto) {

        Gson gson = new Gson();
        String produtoRequestJson = gson.toJson(produtoRequestDTO);
        return this.restClientFixture.request(HttpMethod.PUT, produtoRequestJson, ProdutoResponseDTO.class, URL_API+"/"+codigoBandeira+"/"+codigoProduto);
    }

    /**
     * Consulta produto por Id atraves de chamada get para a api produtos.
     *
     * @return
     */
    public ResponseEntity<ProdutoResponseDTO> consultaProdutoId(String id) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, ProdutoResponseDTO.class, URL_API +"/"+id);
    }

    /**
     * Consulta produto por Codigo atraves de chamada get para a api produtos.
     *
     * @return
     */
    public ResponseEntity<ProdutoResponseDTO> consultaProdutoCodigo(Integer codigoBandeira, Integer codigoProduto) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, ProdutoResponseDTO.class, URL_API +"/cod/"+codigoBandeira+"/"+codigoProduto);
    }

    /**
     * Consulta produto por filtros de busca atraves de chamada getAll para a api produtos.
     *
     * @return
     */
    public ResponseEntity<ProdutoResponseDTO[]> buscaProduto(ProdutoRequestCriteriaDTO produtoRequestCriteriaDTO) {
        return this.restClientFixture.request(
                HttpMethod.GET, null, ProdutoResponseDTO[].class, URL_API,
                produtoRequestCriteriaDTO.getCodigoProduto() == null ? null : produtoRequestCriteriaDTO.getCodigoProduto().toString(),
                produtoRequestCriteriaDTO.getCodigoBandeira() == null ? null : produtoRequestCriteriaDTO.getCodigoBandeira().toString(),
                produtoRequestCriteriaDTO.getDescricao());
    }

    /**
     * OBTEM o resultado da ultima chamada
     */
    public ResponseEntity<ProdutoResponseDTO> getResultado() {
        return this.restClientFixture.getLastResponse();
    }

    /**
     * OBTEM o resultado da ultima chamada de busca
     */
    public ResponseEntity<ArrayList<ProdutoResponseDTO>> getResultadoBusca() {
        return this.restClientFixture.getLastResponse();
    }

}
