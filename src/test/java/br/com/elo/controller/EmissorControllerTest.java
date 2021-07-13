package br.com.elo.controller;

import br.com.elo.common.exception.BusinessException;
import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.domain.Plataforma;
import br.com.elo.domain.dto.request.EmissorRequestDTO;
import br.com.elo.domain.dto.response.EmissorResponseDTO;
import br.com.elo.service.EmissorService;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmissorControllerTest {

    static String EMISSOR_API = "/api/emissores";
    @Autowired
    MockMvc mvc;
    @MockBean
    EmissorService service;

    private final EmissorRequestDTO novoEmissor = DadosMockUtil.criaNovoEmissorRequestDTO();

    @Test
    @DisplayName("Deve criar um Emissor com sucesso")
    public void createEmissorTest() throws Exception {
        EmissorRequestDTO dto = novoEmissor;

        EmissorResponseDTO EmissorSalvo = EmissorResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .codigoEmissor(100)
                .nomeEmissor("Cadastro de emissor")
                .codigoBandeira(1)
                .codigoProcessadora(1)
                .plataforma(Plataforma.CREDITO)
                .dataCriacao(LocalDateTime.now())
                .build();

        given(service.save(Mockito.any(EmissorRequestDTO.class))).willReturn(EmissorSalvo);

        String json = new ObjectMapper().writeValueAsString(dto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(EMISSOR_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
        mvc
                .perform(request)
                .andExpect( status().isCreated() )
                //.andExpect( MockMvcResultMatchers.jsonPath("codigo").value(1L) )
                .andExpect( jsonPath("nomeEmissor").value(dto.getNomeEmissor()) );

    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não houver dados suficientes para criação do Emissor")
    public void verificaDadosIncompletosEmissor() throws Exception {
        String json = new ObjectMapper().writeValueAsString(new EmissorRequestDTO());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(EMISSOR_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect( status().isBadRequest() )
                .andExpect( jsonPath("errors", Matchers.hasSize(5)) ) ;
    }

    @Test
    @DisplayName("Deve lançar erro ao tentar cadastrar um Emissor com codigo já utilizado por outro")
    public void verificaEmissorJaCadastrado() throws Exception {

        EmissorRequestDTO dto = novoEmissor;
        String json = new ObjectMapper().writeValueAsString(dto);
        String mensagemErro = "Código já cadastrado";
        given(service.save(Mockito.any(EmissorRequestDTO.class)))
                .willThrow(new BusinessException(mensagemErro));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(EMISSOR_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect( status().isBadRequest() )
                .andExpect( jsonPath("errors", Matchers.hasSize(1)) )
                .andExpect( jsonPath("errors[0]").value(mensagemErro) );
    }

    @Test
    @DisplayName("Deve atualizar o Emissor com sucesso")
    public void deveAtualizarEmissorComSuceso()throws Exception{

        Integer codigoEmissor = 1;
        Integer codigoBandeira = 1;
        String json = new ObjectMapper().writeValueAsString(novoEmissor);
        EmissorRequestDTO emissorRequestDTO = novoEmissor;

        EmissorResponseDTO emissorRetorno = EmissorResponseDTO
                .builder()
                .id("5feb955b37b9fb7770ec3155")
                .codigoEmissor(codigoEmissor)
                .codigoBandeira(codigoBandeira)
                .nomeEmissor("Cadastro de emissor")
                .codigoProcessadora(1)
                .plataforma(Plataforma.CREDITO)
                .build();

        given(service.findByCodigoBandeiraAndCodigoEmissor(codigoEmissor, codigoBandeira)).willReturn(emissorRetorno);
        given(service.update(codigoEmissor, codigoBandeira, emissorRequestDTO)).willReturn(emissorRetorno);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(EMISSOR_API + "/" + codigoBandeira + "/" + codigoEmissor )
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform( request)
                .andExpect( status().isOk())
                .andExpect( jsonPath("codigoEmissor").value(codigoEmissor) )
                .andExpect( jsonPath("nomeEmissor").value(DadosMockUtil.criaNovoEmissorRequestDTO().getNomeEmissor()) );
    }

    @Test
    @DisplayName("Deve retornar 400 ao tentar atualizar um Emissor inexistente")
    public void updateInexistenteTest()throws Exception{

        Integer codigoEmissor = 1;
        Integer codigoBandeira = 1;
        String json = new ObjectMapper().writeValueAsString(new EmissorRequestDTO());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(EMISSOR_API + "/" + codigoBandeira + "/" + codigoEmissor )
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect( status().isBadRequest() )
                .andExpect( jsonPath("errors", Matchers.hasSize(5)) )
                .andDo(print());
    }


    @Test
    @DisplayName("Deve retornar um Emissor valido passando o código")
    public void deveRetornarEmissorPorCodigo() throws Exception {
        Integer codigoEmissor = 1;
        Integer codigoBandeira = 1;
        EmissorResponseDTO emissorSalvo = EmissorResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .codigoEmissor(1)
                .codigoBandeira(1)
                .nomeEmissor("Cadastro de emissor")
                .codigoProcessadora(1)
                .plataforma(Plataforma.CREDITO)
                .dataCriacao(LocalDateTime.now())
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/emissores/cod/1/1")
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findByCodigoBandeiraAndCodigoEmissor(codigoEmissor, codigoBandeira)).willReturn(emissorSalvo);

        final MvcResult mvcResult = this.mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("codigoEmissor").value(emissorSalvo.getCodigoEmissor()))
                .andExpect(jsonPath("id").value(emissorSalvo.getId()))
                .andDo(print())
                .andReturn();

    }


    @Test
    @DisplayName("Deve retornar um Emissor valido passando o Id")
    public void deveRetornarEmissorPorId() throws Exception {
        String id = "5ffc7cc8324f9c7563d41d71";
        EmissorResponseDTO EmissorSalvo = EmissorResponseDTO
                .builder()
                .id(id)
                .codigoEmissor(1)
                .nomeEmissor("Cadastro de emmissor")
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/emissores/"+id)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findById(id)).willReturn(EmissorSalvo);

        final MvcResult mvcResult = this.mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("codigoEmissor").value(EmissorSalvo.getCodigoEmissor()))
                .andExpect(jsonPath("id").value(EmissorSalvo.getId()))
                .andDo(print())
                .andReturn();

    }

    @Test
    @DisplayName("Deve retornar o resultado na busca através do método findAll")
    public void deveRetornarOResultadoDaBuscaPorTodosRegistros() throws Exception {

        EmissorResponseDTO EmissorSalvo = EmissorResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .codigoEmissor(1)
                .nomeEmissor("Cadastro de emissor")
                .build();

        List<EmissorResponseDTO> list = new ArrayList<>();
        list.add(EmissorSalvo);

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/emissores/")
                .queryParam("codigo","1")
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findAll(BDDMockito.any())).willReturn(list);

        final MvcResult mvcResult = this.mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].codigoEmissor").value(EmissorSalvo.getCodigoEmissor()))
                .andExpect(jsonPath("$.[0].id").value(EmissorSalvo.getId()))
                .andExpect( jsonPath("$", Matchers.hasSize(1)))
                .andDo(print())
                .andReturn();

    }

}
