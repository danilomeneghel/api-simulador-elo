package br.com.elo.controller;


import br.com.elo.domain.Status;
import br.com.elo.domain.TipoMensagem;
import br.com.elo.domain.dto.request.MensagemISO8583RequestDTO;
import br.com.elo.domain.dto.request.ProtocoloRequestDTO;
import br.com.elo.domain.dto.response.MensagemISO8583ResponseDTO;
import br.com.elo.service.MensagemISO8583Service;
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
public class MensagemISO8583ControllerTest {

    static final String MENSAGEMISO8583_API = "/api/mensagens";


    @Autowired
    MockMvc mockMvc;

    @MockBean
    MensagemISO8583Service service;

    @Test
    @DisplayName("Deve criar uma Mensagem ISO8583 com sucesso")
    public void createMensagemISO8583Test() throws Exception {
        MensagemISO8583RequestDTO dto = criaNovaMensagemISO8583();

        MensagemISO8583ResponseDTO mensagemSalva =  MensagemISO8583ResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .mensagemSequence(1L)
                .tipoMensagem(TipoMensagem.SOLICITACAO)
                .status(Status.ATIVO)
                .codigoMensagem(200)
                .protocoloSequence(1L)
                .descricao("Mensagem Credito a Vista")
                .build();

        BDDMockito.given(service.save(Mockito.any(MensagemISO8583RequestDTO.class))).willReturn(mensagemSalva);

        String json = new ObjectMapper().writeValueAsString(dto);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(MENSAGEMISO8583_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc
                .perform(request)
                .andExpect( MockMvcResultMatchers.status().isCreated() )
                .andExpect( MockMvcResultMatchers.jsonPath("id").value(mensagemSalva.getId()))
                .andExpect( MockMvcResultMatchers.jsonPath("mensagemSequence").value(mensagemSalva.getMensagemSequence()) )
                .andExpect( MockMvcResultMatchers.jsonPath("tipoMensagem").value(dto.getTipoMensagem().toString()) )
                .andExpect( MockMvcResultMatchers.jsonPath("codigoMensagem").value(dto.getCodigoMensagem()) )
                .andExpect( MockMvcResultMatchers.jsonPath("descricao").value(dto.getDescricao()) )
                .andExpect( MockMvcResultMatchers.jsonPath("status").value(dto.getStatus().toString()) )
                .andExpect( MockMvcResultMatchers.jsonPath("protocoloSequence").value(dto.getProtocoloSequence()) );

    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não houver dados suficientes para criação da Mensagem")
    public void createDadosIncompletosMensagem() throws Exception {
        String json = new ObjectMapper().writeValueAsString(new MensagemISO8583RequestDTO());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(MENSAGEMISO8583_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isBadRequest() )
                .andExpect( MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(5)) ) ;
    }

    @Test
    @DisplayName("Deve atualizar uma mensagem com sucesso")
    public void updateProtocoloCodigoValidoTest() throws Exception {

        Long codigo = 1L;
        MensagemISO8583ResponseDTO mensagemSalva =  MensagemISO8583ResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .mensagemSequence(1L)
                .tipoMensagem(TipoMensagem.SOLICITACAO)
                .status(Status.ATIVO)
                .codigoMensagem(200)
                .protocoloSequence(1L)
                .descricao("Mensagem Credito a Vista")
                .build();

        MensagemISO8583RequestDTO mensagemRequest = MensagemISO8583RequestDTO
                .builder()
                .descricao("Mensagem Credito a Vista")
                .tipoMensagem(TipoMensagem.SOLICITACAO)
                .status(Status.ATIVO)
                .codigoMensagem(200)
                .protocoloSequence(1L)
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.PUT, "/api/mensagens/"+codigo)
                .headers(new HttpHeaders())
                .content(new ObjectMapper().writeValueAsString(mensagemRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.update(BDDMockito.any(),BDDMockito.any())).willReturn(mensagemSalva);

        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("mensagemSequence").value(mensagemSalva.getMensagemSequence()))
                .andExpect(jsonPath("id").value(mensagemSalva.getId()))
                .andDo(print())
                .andReturn();

    }

    @Test
    @DisplayName("Deve retornar erro ao atualizar uma mensagem")
    public void updateMensagemDadosIncompletosTest() throws Exception {

        Long codigo = 1L;
        String json = new ObjectMapper().writeValueAsString(new ProtocoloRequestDTO());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(MENSAGEMISO8583_API + "/" + codigo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isBadRequest() )
                .andExpect( MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(5)) )
                .andDo(print());

    }

    @Test
    @DisplayName("Deve retornar uma mensagem válida")
    public void getMensgemISO8583IdValidoTest() throws Exception {

        MensagemISO8583ResponseDTO mensagemSalva =  MensagemISO8583ResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .mensagemSequence(1L)
                .tipoMensagem(TipoMensagem.SOLICITACAO)
                .status(Status.ATIVO)
                .codigoMensagem(200)
                .protocoloSequence(1L)
                .descricao("Mensagem Credito a Vista")
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/mensagens/5ffc7cc8324f9c7563d41d71")
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findById(BDDMockito.any())).willReturn(mensagemSalva);

        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("mensagemSequence").value(mensagemSalva.getMensagemSequence()))
                .andExpect(jsonPath("id").value(mensagemSalva.getId()))
                .andDo(print())
                .andReturn();

    }

    @Test
    @DisplayName("Deve retornar uma mensagem válida")
    public void getMensagemISO8583CodigoValidoTest() throws Exception {

        MensagemISO8583ResponseDTO mensagemSalva =  MensagemISO8583ResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .mensagemSequence(1L)
                .tipoMensagem(TipoMensagem.SOLICITACAO)
                .status(Status.ATIVO)
                .codigoMensagem(200)
                .protocoloSequence(1L)
                .descricao("Mensagem Credito a Vista")
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/mensagens/seq/1")
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findByMensagemSequence(BDDMockito.any())).willReturn(mensagemSalva);

        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("mensagemSequence").value(mensagemSalva.getMensagemSequence()))
                .andExpect(jsonPath("id").value(mensagemSalva.getId()))
                .andDo(print())
                .andReturn();

    }

    @Test
    @DisplayName("Deve retornar uma mensagem válida")
    public void getAllMensagemValidaTest() throws Exception {

        MensagemISO8583ResponseDTO mensagemSalva =  MensagemISO8583ResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .mensagemSequence(1L)
                .tipoMensagem(TipoMensagem.SOLICITACAO)
                .status(Status.ATIVO)
                .codigoMensagem(200)
                .protocoloSequence(1L)
                .descricao("Mensagem Credito a Vista")
                .build();

        List<MensagemISO8583ResponseDTO> mensagemSalvoList = new ArrayList<>();
        mensagemSalvoList.add(mensagemSalva);

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/mensagens/")
                .queryParam("codigo","1")
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findAll(BDDMockito.any())).willReturn(mensagemSalvoList);

        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].mensagemSequence").value(mensagemSalva.getMensagemSequence()))
                .andExpect(jsonPath("$.[0].id").value(mensagemSalva.getId()))
                .andExpect( MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andDo(print())
                .andReturn();

    }

    private MensagemISO8583RequestDTO criaNovaMensagemISO8583() {
        return  MensagemISO8583RequestDTO
                .builder()
                .descricao("Mensagem Credito a Vista")
                .tipoMensagem(TipoMensagem.SOLICITACAO)
                .status(Status.ATIVO)
                .codigoMensagem(200)
                .protocoloSequence(1L)
                .build();
    }
}
