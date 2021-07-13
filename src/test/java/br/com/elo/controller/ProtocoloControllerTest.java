package br.com.elo.controller;


import br.com.elo.domain.*;
import br.com.elo.domain.dto.request.ProtocoloRequestDTO;
import br.com.elo.domain.dto.response.ProtocoloResponseDTO;
import br.com.elo.service.ProtocoloService;
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
public class ProtocoloControllerTest {

    static String PROTOCOLO_API = "/api/protocolos";


    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProtocoloService service;

    @Test
    @DisplayName("Deve criar um protocolo com sucesso")
    public void createProtocoloTest() throws Exception {
        ProtocoloRequestDTO dto = criaNovoProtocolo();

        ProtocoloResponseDTO protocoloSalvo =  ProtocoloResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .protocoloSequence(1L)
                .tipo(TipoProtocolo.BANDEIRA)
                .status(Status.ATIVO)
                .descricao("Cadastro de protocolo")
                .encodeBCDLLVARLLLVAR(EncodeBCDLLVARLLLVAR.NRODIGITOS)
                .encodeCodigoMensagem(EncodeCodigoMensagem.ASCII)
                .encodeMapaDeBits(EncodeMapaDeBits.ASCII)
                .versao("1.0")
                .build();

        BDDMockito.given(service.save(Mockito.any(ProtocoloRequestDTO.class))).willReturn(protocoloSalvo);

        String json = new ObjectMapper().writeValueAsString(dto);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(PROTOCOLO_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc
                .perform(request)
                .andExpect( MockMvcResultMatchers.status().isCreated() )
                .andExpect( MockMvcResultMatchers.jsonPath("id").value(protocoloSalvo.getId()))
                .andExpect( MockMvcResultMatchers.jsonPath("protocoloSequence").value(protocoloSalvo.getProtocoloSequence()) )
                .andExpect( MockMvcResultMatchers.jsonPath("tipo").value(dto.getTipo().toString()) )
                .andExpect( MockMvcResultMatchers.jsonPath("descricao").value(dto.getDescricao()) )
                .andExpect( MockMvcResultMatchers.jsonPath("status").value(dto.getStatus().toString()) )
                .andExpect( MockMvcResultMatchers.jsonPath("encodeBCDLLVARLLLVAR").value(dto.getEncodeBCDLLVARLLLVAR().toString()) )
                .andExpect( MockMvcResultMatchers.jsonPath("encodeCodigoMensagem").value(dto.getEncodeCodigoMensagem().toString()) )
                .andExpect( MockMvcResultMatchers.jsonPath("encodeMapaDeBits").value(dto.getEncodeMapaDeBits().toString()) )
                .andExpect( MockMvcResultMatchers.jsonPath("versao").value(dto.getVersao()) );

    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não houver dados suficientes para criação do protocolo")
    public void createDadosIncompletosProtocolo() throws Exception {
        String json = new ObjectMapper().writeValueAsString(new ProtocoloRequestDTO());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(PROTOCOLO_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isBadRequest() )
                .andExpect( MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(7)) ) ;
    }

    @Test
    @DisplayName("Deve atualizar um protocolo com sucesso")
    public void updateProtocoloCodigoValidoTest() throws Exception {

        Long codigo = 1L;
        ProtocoloResponseDTO protocoloSalvo =  ProtocoloResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .protocoloSequence(1L)
                .tipo(TipoProtocolo.BANDEIRA)
                .status(Status.ATIVO)
                .descricao("Cadastro de protocolo")
                .encodeBCDLLVARLLLVAR(EncodeBCDLLVARLLLVAR.NRODIGITOS)
                .encodeCodigoMensagem(EncodeCodigoMensagem.ASCII)
                .encodeMapaDeBits(EncodeMapaDeBits.ASCII)
                .versao("1.0")
                .build();

        ProtocoloRequestDTO protocoloRequest = ProtocoloRequestDTO
                .builder()
                .tipo(TipoProtocolo.BANDEIRA)
                .status(Status.ATIVO)
                .descricao("Cadastro de protocolo")
                .encodeBCDLLVARLLLVAR(EncodeBCDLLVARLLLVAR.NRODIGITOS)
                .encodeCodigoMensagem(EncodeCodigoMensagem.ASCII)
                .encodeMapaDeBits(EncodeMapaDeBits.ASCII)
                .versao("1.0")
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.PUT, "/api/protocolos/"+codigo)
                .headers(new HttpHeaders())
                .content(new ObjectMapper().writeValueAsString(protocoloRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.update(BDDMockito.any(),BDDMockito.any())).willReturn(protocoloSalvo);

        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("protocoloSequence").value(protocoloSalvo.getProtocoloSequence()))
                .andExpect(jsonPath("id").value(protocoloSalvo.getId()))
                .andDo(print())
                .andReturn();

    }

    @Test
    @DisplayName("Deve retornar erro ao atualizar um protocolo")
    public void updateProtocoloDadosIncompletosTest() throws Exception {

        Long codigo = 1L;
        String json = new ObjectMapper().writeValueAsString(new ProtocoloRequestDTO());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(PROTOCOLO_API + "/" + codigo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isBadRequest() )
                .andExpect( MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(7)) )
                .andDo(print());

    }

    @Test
    @DisplayName("Deve retornar um protocolo valido")
    public void getProtocoloIdValidoTest() throws Exception {

        ProtocoloResponseDTO protocoloSalvo =  ProtocoloResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .protocoloSequence(1L)
                .tipo(TipoProtocolo.BANDEIRA)
                .status(Status.ATIVO)
                .descricao("Cadastro de protocolo")
                .encodeBCDLLVARLLLVAR(EncodeBCDLLVARLLLVAR.NRODIGITOS)
                .encodeCodigoMensagem(EncodeCodigoMensagem.ASCII)
                .encodeMapaDeBits(EncodeMapaDeBits.ASCII)
                .versao("1.0")
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/protocolos/5ffc7cc8324f9c7563d41d71")
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findById(BDDMockito.any())).willReturn(protocoloSalvo);

        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("protocoloSequence").value(protocoloSalvo.getProtocoloSequence()))
                .andExpect(jsonPath("id").value(protocoloSalvo.getId()))
                .andDo(print())
                .andReturn();

    }

    @Test
    @DisplayName("Deve retornar um protocolo valido")
    public void getProtocoloCodigoValidoTest() throws Exception {

        ProtocoloResponseDTO protocoloSalvo =  ProtocoloResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .protocoloSequence(1L)
                .tipo(TipoProtocolo.BANDEIRA)
                .status(Status.ATIVO)
                .descricao("Cadastro de protocolo")
                .encodeBCDLLVARLLLVAR(EncodeBCDLLVARLLLVAR.NRODIGITOS)
                .encodeCodigoMensagem(EncodeCodigoMensagem.ASCII)
                .encodeMapaDeBits(EncodeMapaDeBits.ASCII)
                .versao("1.0")
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/protocolos/seq/1")
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findByProtocoloSequence(BDDMockito.any())).willReturn(protocoloSalvo);

        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("protocoloSequence").value(protocoloSalvo.getProtocoloSequence()))
                .andExpect(jsonPath("id").value(protocoloSalvo.getId()))
                .andDo(print())
                .andReturn();

    }

    @Test
    @DisplayName("Deve retornar um protocolo valido")
    public void getAllProtocoloCodigoValidoTest() throws Exception {

        ProtocoloResponseDTO protocoloSalvo =  ProtocoloResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .protocoloSequence(1L)
                .tipo(TipoProtocolo.BANDEIRA)
                .status(Status.ATIVO)
                .descricao("Cadastro de protocolo")
                .encodeBCDLLVARLLLVAR(EncodeBCDLLVARLLLVAR.NRODIGITOS)
                .encodeCodigoMensagem(EncodeCodigoMensagem.ASCII)
                .encodeMapaDeBits(EncodeMapaDeBits.ASCII)
                .versao("1.0")
                .build();

        List<ProtocoloResponseDTO> protocoloSalvoList = new ArrayList<>();
        protocoloSalvoList.add(protocoloSalvo);

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/protocolos/")
                .queryParam("protocoloSequence","1")
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findAll(BDDMockito.any())).willReturn(protocoloSalvoList);

        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].protocoloSequence").value(protocoloSalvo.getProtocoloSequence()))
                .andExpect(jsonPath("$.[0].id").value(protocoloSalvo.getId()))
                .andExpect( MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andDo(print())
                .andReturn();

    }

    private ProtocoloRequestDTO criaNovoProtocolo() {
        return ProtocoloRequestDTO
                .builder()
                .tipo(TipoProtocolo.BANDEIRA)
                .status(Status.ATIVO)
                .descricao("Cadastro de protocolo")
                .encodeBCDLLVARLLLVAR(EncodeBCDLLVARLLLVAR.NRODIGITOS)
                .encodeCodigoMensagem(EncodeCodigoMensagem.ASCII)
                .encodeMapaDeBits(EncodeMapaDeBits.ASCII)
                .versao("1.0")
                .build();
    }
}
