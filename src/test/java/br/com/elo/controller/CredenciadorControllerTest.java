package br.com.elo.controller;

import br.com.elo.common.exception.BusinessException;
import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.domain.Status;
import br.com.elo.domain.dto.request.CredenciadorRequestCriteriaDTO;
import br.com.elo.domain.dto.request.CredenciadorRequestDTO;


import br.com.elo.domain.dto.response.CredenciadorResponseDTO;

import br.com.elo.service.CredenciadorService;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

public class CredenciadorControllerTest {

    static String CREDENCIADOR_API = "/api/credenciadores";

    @Autowired
    MockMvc mvc;

    @MockBean
    CredenciadorService service;

    private CredenciadorRequestDTO novoCredenciador = DadosMockUtil.criaNovoCredenciadorRequestDTO();

    @Test
    @DisplayName("Criar um novo credenciador com sucesso")
    public void createCredenciadorTest() throws Exception{
        CredenciadorRequestDTO dto = criaNovoCredenciador();

        CredenciadorResponseDTO credenciadorSalvo = CredenciadorResponseDTO
                .builder()
                .id("601239085059924aea7e7db4")
                //.codigo(1L)
                .credenciadorCodigo(1)
                .nome("Nome credenciador")
                .status(Status.ATIVO)
                .build();

        BDDMockito.given(service.save(Mockito.any(CredenciadorRequestDTO.class))).willReturn(credenciadorSalvo);

        String json = new ObjectMapper().writeValueAsString(dto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(CREDENCIADOR_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isCreated() )
                .andExpect( MockMvcResultMatchers.jsonPath("credenciadorCodigo").value(dto.getCredenciadorCodigo()) )
                .andExpect( MockMvcResultMatchers.jsonPath("nome").value(dto.getNome()) )
                .andExpect( MockMvcResultMatchers.jsonPath("status").value(dto.getStatus().toString()) );
    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não houver dados suficientes para criação do credenciador")
    public void verificaDadosIncompletosCredenciador() throws Exception {
        String json = new ObjectMapper().writeValueAsString(new CredenciadorRequestDTO());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(CREDENCIADOR_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isBadRequest() )
                .andExpect( MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(3)) ) ;
    }

    public void verificaCredenciadorJaCadastrado() throws Exception{

        CredenciadorRequestDTO dto = novoCredenciador;
        String json = new ObjectMapper().writeValueAsString(dto);
        String mensagemErro = "Código já cadastrado";
        BDDMockito.given(service.save(Mockito.any(CredenciadorRequestDTO.class)))
                .willThrow(new BusinessException(mensagemErro));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(CREDENCIADOR_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isBadRequest() )
                .andExpect( MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(1)) )
                .andExpect( MockMvcResultMatchers.jsonPath("errors[0]").value(mensagemErro) );
    }

    @Test
    @DisplayName("Deve atualizar o credenciador com sucesso")
    public void deveAtualizarCredenciadorComSuceso()throws Exception{
        CredenciadorRequestDTO dto = criaNovoCredenciador();

        Integer credenciadorCodigo = 1;
        String json = new ObjectMapper().writeValueAsString(novoCredenciador);
        CredenciadorRequestDTO credenciadorRequestDTO = novoCredenciador;
        CredenciadorResponseDTO credenciadorRetorno = CredenciadorResponseDTO.builder()
                .id("601239085059924aea7e7db4")
                .credenciadorCodigo(1)
                .nome("Nome credenciador")
                .status(Status.ATIVO)
                .build();

        BDDMockito.given(service.findByCodigo(credenciadorCodigo)).willReturn(credenciadorRetorno);

        CredenciadorRequestCriteriaDTO updateCredenciador = CredenciadorRequestCriteriaDTO.builder()
                .id("601239085059924aea7e7db4")
                .codigo(1L)
                .credenciadorCodigo(1L)
                .nome("Nome credenciador")
                .status(Status.ATIVO)
                .build();

        BDDMockito.given(service.update(credenciadorCodigo, credenciadorRequestDTO)).willReturn(credenciadorRetorno);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(CREDENCIADOR_API.concat("/"+1))
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect( MockMvcResultMatchers.jsonPath("credenciadorCodigo").value(dto.getCredenciadorCodigo()) )
                .andExpect( MockMvcResultMatchers.jsonPath("nome").value(dto.getNome()) )
                .andExpect( MockMvcResultMatchers.jsonPath("status").value(dto.getStatus().toString()) );
    }

    @Test
    @DisplayName("Deve retornar 404 ao tentar atualizar um credenciador inexistente")
    public void updateInexistenteTest()throws Exception{

        Long codigo = 1L;
        String json = new ObjectMapper().writeValueAsString(new CredenciadorRequestDTO());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(CREDENCIADOR_API + "/"+ codigo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isBadRequest() )
                .andExpect( MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(3)) )
                .andDo(print());
    }

    public static CredenciadorRequestDTO criaNovoCredenciador() {
        return CredenciadorRequestDTO
                .builder()
                .credenciadorCodigo(1)
                .nome("Nome credenciador")
                .status(Status.ATIVO)
                .build();
    }
}