package br.com.elo.controller;

import br.com.elo.common.exception.BusinessException;
import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.domain.TipoPlataforma;
import br.com.elo.domain.dto.request.ProdutoRequestCriteriaDTO;
import br.com.elo.domain.dto.request.ProdutoRequestDTO;
import br.com.elo.domain.dto.response.ProdutoResponseDTO;
import br.com.elo.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTest {

    static String PRODUTO_API = "/api/produtos";

    @Autowired
    MockMvc mvc;

    @MockBean
    ProdutoService service;

    private ProdutoRequestDTO novoProduto = DadosMockUtil.criaNovoProdutoRequestDTO();

    @Test
    @DisplayName("Deve criar um produto com sucesso")
    public void createProdutoTest() throws Exception {
        ProdutoRequestDTO dto = novoProduto;

        ProdutoResponseDTO produtoSalvo = ProdutoResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d72")
                .codigoBandeira(1)
                .codigoProduto(1)
                .tipoPlataforma(TipoPlataforma.CREDITO)
                .descricao("Cadastro de Produto")
                .build();

        BDDMockito.given(service.save(Mockito.any(ProdutoRequestDTO.class))).willReturn(produtoSalvo);

        String json = new ObjectMapper().writeValueAsString(dto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(PRODUTO_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isCreated() )
                .andExpect( MockMvcResultMatchers.jsonPath("codigoBandeira").value(dto.getCodigoBandeira()) )
                .andExpect( MockMvcResultMatchers.jsonPath("codigoProduto").value(dto.getCodigoProduto()) )
                .andExpect( MockMvcResultMatchers.jsonPath("tipoPlataforma").value(dto.getTipoPlataforma().toString()) )
                .andExpect( MockMvcResultMatchers.jsonPath("descricao").value(dto.getDescricao()) );
    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não houver dados suficientes para criação do produto")
    public void verificaDadosIncompletosProduto() throws Exception {
        String json = new ObjectMapper().writeValueAsString(new ProdutoRequestDTO());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(PRODUTO_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isBadRequest() )
                .andExpect( MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(4)) ) ;
    }

    @Test
    @DisplayName("Deve atualizar o produto com sucesso")
    public void deveAtualizarProdutoComSucesso()throws Exception{

        Integer codigoBandeira = 1;
        Integer codigoProduto = 1;
        String json = new ObjectMapper().writeValueAsString(novoProduto);
        ProdutoRequestDTO produtoRequestDTO = novoProduto;
        ProdutoResponseDTO produtoRetorno = ProdutoResponseDTO.builder()
                .codigoBandeira(1)
                .codigoProduto(1)
                .tipoPlataforma(TipoPlataforma.CREDITO)
                .descricao("Cadastro de Produto")
                .build();

        BDDMockito.given(service.findByCodigoBandeiraAndCodigoProduto(codigoBandeira,codigoProduto)).willReturn(produtoRetorno);

        BDDMockito.given(service.update(codigoBandeira,codigoProduto,produtoRequestDTO)).willReturn(produtoRetorno);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(PRODUTO_API.concat("/" + 1 + "/" + 1))
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect( status().isOk())
                .andExpect( MockMvcResultMatchers.jsonPath("codigoBandeira").value(DadosMockUtil.criaNovoProdutoRequestDTO().getCodigoBandeira()) )
                .andExpect( MockMvcResultMatchers.jsonPath("codigoProduto").value(DadosMockUtil.criaNovoProdutoRequestDTO().getCodigoProduto()) )
                .andExpect( MockMvcResultMatchers.jsonPath("tipoPlataforma").value(DadosMockUtil.criaNovoProdutoRequestDTO().getTipoPlataforma().toString()) )
                .andExpect( MockMvcResultMatchers.jsonPath("descricao").value(DadosMockUtil.criaNovoProdutoRequestDTO().getDescricao()) );
    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não houver dados suficientes para atualizar o produto")
    public void updateInexistenteTest()throws Exception{

        Integer codigoBandeira = 1;
        Integer codigoProduto = 1;

        String json = new ObjectMapper().writeValueAsString(new ProdutoRequestDTO());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(PRODUTO_API + "/" + codigoBandeira + "/" +codigoProduto)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isBadRequest() )
                .andExpect( MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(4)) )
                .andDo(print());
    }

    @Test
    @DisplayName("Deve retornar um produto valido passando o código")
    public void deveRetornarProdutoPorCodigoBandeiraECodigoProduto() throws Exception {

        Integer codigoBandeira = 1;
        Integer codigoProduto = 1;

        ProdutoResponseDTO produtoSalvo = ProdutoResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d72")
                .codigoBandeira(1)
                .codigoProduto(1)
                .tipoPlataforma(TipoPlataforma.CREDITO)
                .descricao("Cadastro de Produto")
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/produtos/cod/1/1")
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findByCodigoBandeiraAndCodigoProduto(codigoBandeira,codigoProduto)).willReturn(produtoSalvo);

        final MvcResult mvcResult = this.mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(produtoSalvo.getId()))
                .andExpect(jsonPath("codigoBandeira").value(produtoSalvo.getCodigoBandeira()))
                .andExpect(jsonPath("codigoProduto").value(produtoSalvo.getCodigoBandeira()))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Deve retornar um produto valido passando o Id")
    public void deveRetornarProdutoPorId() throws Exception {
        String id = "5ffc7cc8324f9c7563d41d72";
        ProdutoResponseDTO produtoSalvo = ProdutoResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d72")
                .codigoBandeira(1)
                .codigoProduto(1)
                .tipoPlataforma(TipoPlataforma.CREDITO)
                .descricao("Cadastro de Produto")
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/produtos/"+id)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findById(id)).willReturn(produtoSalvo);

        final MvcResult mvcResult = this.mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(produtoSalvo.getId()))
                .andExpect(jsonPath("codigoBandeira").value(produtoSalvo.getCodigoBandeira()))
                .andExpect(jsonPath("codigoProduto").value(produtoSalvo.getCodigoBandeira()))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Deve retornar o resultado na busca através do método findAll")
    public void deveRetornarOResultadoDaBuscaPorTodosRegistros() throws Exception {

        ProdutoResponseDTO produtoSalvo = ProdutoResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d72")
                .codigoBandeira(1)
                .codigoProduto(1)
                .tipoPlataforma(TipoPlataforma.CREDITO)
                .descricao("Cadastro de Produto")
                .build();

        List<ProdutoResponseDTO> list = new ArrayList<>();
        list.add(produtoSalvo);

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/produtos/")
                .queryParam("codigo","1")
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findAll(BDDMockito.any())).willReturn(list);

        final MvcResult mvcResult = this.mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect( MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.[0].id").value(produtoSalvo.getId()))
                .andExpect(jsonPath("$.[0].codigoBandeira").value(produtoSalvo.getCodigoBandeira()))
                .andExpect(jsonPath("$.[0].codigoProduto").value(produtoSalvo.getCodigoBandeira()))
                .andDo(print())
                .andReturn();
    }

}
