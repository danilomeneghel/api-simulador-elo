package br.com.elo.controller;

import br.com.elo.common.exception.BusinessException;
import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.domain.Status;
import br.com.elo.domain.dto.request.AmbienteRequestDTO;
import br.com.elo.domain.dto.response.AmbienteResponseDTO;
import br.com.elo.service.AmbienteService;
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
public class AmbienteControllerTest {

    static String AMBIENTE_API = "/api/ambientes";

    @Autowired
    MockMvc mvc;

    @MockBean
    AmbienteService service;

    private AmbienteRequestDTO novoAmbiente = DadosMockUtil.criaNovoSimuladorRequestDTO();

    @Test
    @DisplayName("Deve criar um ambiente com sucesso")
    public void createAmbienteTest() throws Exception {
        AmbienteRequestDTO dto = novoAmbiente;

        AmbienteResponseDTO ambienteSalvo = DadosMockUtil.ambienteResponseDTO();

        BDDMockito.given(service.save(Mockito.any(AmbienteRequestDTO.class))).willReturn(ambienteSalvo);

        String json = new ObjectMapper().writeValueAsString(dto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(AMBIENTE_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isCreated() )
                .andExpect( MockMvcResultMatchers.jsonPath("ambienteSequence").value(1L) )
                .andExpect( MockMvcResultMatchers.jsonPath("status").value(dto.getStatus().name()) )
                .andExpect( MockMvcResultMatchers.jsonPath("descricao").value(dto.getDescricao()) )
                .andExpect( MockMvcResultMatchers.jsonPath("portasClientes[0].nomeHost").value(dto.getPortasClientes().get(0).getNomeHost()))
                .andExpect( MockMvcResultMatchers.jsonPath("portasClientes[0].nomePorta").value(dto.getPortasClientes().get(0).getNomePorta()))
                .andExpect( MockMvcResultMatchers.jsonPath("portasClientes[0].numeroPorta").value(dto.getPortasClientes().get(0).getNumeroPorta()))
                .andExpect( MockMvcResultMatchers.jsonPath("portasClientes[0].cabecalho").value(dto.getPortasClientes().get(0).getCabecalho().toString()))
                .andExpect( MockMvcResultMatchers.jsonPath("portasServidores[0].nomeHost").value(dto.getPortasServidores().get(0).getNomeHost()))
                .andExpect( MockMvcResultMatchers.jsonPath("portasServidores[0].nomePorta").value(dto.getPortasServidores().get(0).getNomePorta()))
                .andExpect( MockMvcResultMatchers.jsonPath("portasServidores[0].numeroPorta").value(dto.getPortasServidores().get(0).getNumeroPorta()))
                .andExpect( MockMvcResultMatchers.jsonPath("portasServidores[0].cabecalho").value(dto.getPortasServidores().get(0).getCabecalho().toString()))
                .andExpect( MockMvcResultMatchers.jsonPath("chaves.chavePin").value(dto.getChaves().getChavePin()))
                .andExpect( MockMvcResultMatchers.jsonPath("chaves.chaveCavv").value(dto.getChaves().getChaveCavv()));
    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não houver dados suficientes para criação do ambiente")
    public void verificaDadosIncompletosAmbiente() throws Exception {
        String json = new ObjectMapper().writeValueAsString(new AmbienteRequestDTO());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(AMBIENTE_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isBadRequest() )
                .andExpect( MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(2)) ) ;
    }

    @Test
    @DisplayName("Deve lançar erro ao tentar cadastrar um ambiente com ambienteSequence já utilizado por outro")
    public void verificaAmbienteJaCadastrado() throws Exception {
        AmbienteRequestDTO dto = novoAmbiente;

        String json = new ObjectMapper().writeValueAsString(dto);

        String mensagemErro = "AmbienteSequence já cadastrado";

        BDDMockito.given(service.save(Mockito.any(AmbienteRequestDTO.class)))
                .willThrow(new BusinessException(mensagemErro));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(AMBIENTE_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isBadRequest() )
                .andExpect( MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(1)) )
                .andExpect( MockMvcResultMatchers.jsonPath("errors[0]").value(mensagemErro) );
    }

    @Test
    @DisplayName("Deve atualizar o ambiente com sucesso")
    public void deveAtualizarAmbienteComSuceso()throws Exception{
        Long ambienteSequence = 1L;

        AmbienteResponseDTO ambienteSalvo = DadosMockUtil.ambienteResponseDTO();

        AmbienteRequestDTO ambienteRequest = DadosMockUtil.criaNovoSimuladorRequestDTO();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.PUT, "/api/ambientes/" + ambienteSequence)
                .headers(new HttpHeaders())
                .content(new ObjectMapper().writeValueAsString(ambienteRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.update(BDDMockito.any(),BDDMockito.any())).willReturn(ambienteSalvo);

        final MvcResult mvcResult = this.mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("ambienteSequence").value(ambienteSalvo.getAmbienteSequence()))
                .andExpect(jsonPath("id").value(ambienteSalvo.getId()))
                .andExpect(jsonPath("portasClientes[0].nomeHost").value(ambienteSalvo.getPortasClientes().get(0).getNomeHost()))
                .andExpect(jsonPath("portasClientes[0].nomePorta").value(ambienteSalvo.getPortasClientes().get(0).getNomePorta()))
                .andExpect(jsonPath("chaves.chavePin").value(ambienteSalvo.getChaves().getChavePin()))
                .andExpect(jsonPath("chaves.chaveCavv").value(ambienteSalvo.getChaves().getChaveCavv()))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Deve retornar 404 ao tentar atualizar um ambiente inexistente")
    public void updateInexistenteTest()throws Exception{
        Long ambienteSequence = 1L;

        String json = new ObjectMapper().writeValueAsString(new AmbienteRequestDTO());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(AMBIENTE_API + "/" + ambienteSequence)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isBadRequest() )
                .andExpect( MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(2)) )
                .andDo(print());
    }

    @Test
    @DisplayName("Deve retornar um ambiente valido passando o ambienteSequence")
    public void deveRetornarAmbientePorAmbienteSequence() throws Exception {
        AmbienteResponseDTO ambienteSalvo = AmbienteResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .ambienteSequence(1L)
                .status(Status.ATIVO)
                .descricao("Cadastro de injetor")
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/ambientes/seq/1")
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findByAmbienteSequence(BDDMockito.any())).willReturn(ambienteSalvo);

        final MvcResult mvcResult = this.mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("ambienteSequence").value(ambienteSalvo.getAmbienteSequence()))
                .andExpect(jsonPath("id").value(ambienteSalvo.getId()))
                .andDo(print())
                .andReturn();
    }


    @Test
    @DisplayName("Deve retornar um ambiente valido passando o Id")
    public void deveRetornarAmbientePorId() throws Exception {
        String id = "5ffc7cc8324f9c7563d41d71";

        AmbienteResponseDTO ambienteSalvo = AmbienteResponseDTO
                .builder()
                .id(id)
                .ambienteSequence(1L)
                .status(Status.ATIVO)
                .descricao("Cadastro de injetor")
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/ambientes/"+id)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findById(id)).willReturn(ambienteSalvo);

        final MvcResult mvcResult = this.mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("ambienteSequence").value(ambienteSalvo.getAmbienteSequence()))
                .andExpect(jsonPath("id").value(ambienteSalvo.getId()))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Deve retornar o resultado na busca através do método findAll")
    public void deveRetornarOResultadoDaBuscaPorTodosRegistros() throws Exception {
        AmbienteResponseDTO ambienteSalvo = AmbienteResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .ambienteSequence(1L)
                .status(Status.ATIVO)
                .descricao("Cadastro de injetor")
                .build();

        List<AmbienteResponseDTO> list = new ArrayList<>();
        list.add(ambienteSalvo);

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/ambientes/")
                .queryParam("ambienteSequence","1")
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findAll(BDDMockito.any())).willReturn(list);

        final MvcResult mvcResult = this.mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].ambienteSequence").value(ambienteSalvo.getAmbienteSequence()))
                .andExpect(jsonPath("$.[0].id").value(ambienteSalvo.getId()))
                .andExpect( MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andDo(print())
                .andReturn();
    }

}
