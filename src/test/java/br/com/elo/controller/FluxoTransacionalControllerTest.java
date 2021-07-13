package br.com.elo.controller;


import br.com.elo.domain.dto.request.FluxoTransacionalRequestDTO;
import br.com.elo.domain.dto.response.FluxoTransacionalResponseDTO;
import br.com.elo.service.FluxoTransacionalService;
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

import static br.com.elo.controller.util.DadosMockUtil.criaNovoFluxoTransacional;
import static br.com.elo.controller.util.DadosMockUtil.criaNovoFluxoTransacionalRequestDTO;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FluxoTransacionalControllerTest {

    static String FLUXO_TRANSACIONAL_API = "/api/fluxostransacionais";


    @Autowired
    MockMvc mockMvc;

    @MockBean
    FluxoTransacionalService service;

    @Test
    @DisplayName("Deve criar um fluxo transacional com sucesso")
    public void createFluxoTransacionalTest() throws Exception {
        FluxoTransacionalRequestDTO dto = criaNovoFluxoTransacionalRequestDTO();

        FluxoTransacionalResponseDTO fluxoTransacionalSalvo =  FluxoTransacionalResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .fluxoTransacionalSequence(1L)
                .descricao("Cadastro de fluxo transacional")
                .mensagemSolicitacaoPernaUmSequence(1L)
                .mensagemSolicitacaoPernaDoisSequence(1L)
                .mensagemRespostaPernaTresSequence(1L)
                .mensagemRespostaPernaQuatroSequence(1L)
                .bitVinculacaoMensagensPernasUmQuatro(11)
                .bitVinculacaoMensagensPernasDoisTres(11)
                .build();

        BDDMockito.given(service.save(Mockito.any(FluxoTransacionalRequestDTO.class))).willReturn(fluxoTransacionalSalvo);

        String json = new ObjectMapper().writeValueAsString(dto);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(FLUXO_TRANSACIONAL_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isCreated() )
                .andExpect( MockMvcResultMatchers.jsonPath("id").value(fluxoTransacionalSalvo.getId()))
                .andExpect( MockMvcResultMatchers.jsonPath("fluxoTransacionalSequence").value(fluxoTransacionalSalvo.getFluxoTransacionalSequence()) )
                .andExpect( MockMvcResultMatchers.jsonPath("descricao").value(dto.getDescricao()) )
                .andExpect( MockMvcResultMatchers.jsonPath("mensagemSolicitacaoPernaUmSequence").value(dto.getMensagemSolicitacaoPernaUmSequence()) )
                .andExpect( MockMvcResultMatchers.jsonPath("mensagemRespostaPernaQuatroSequence").value(dto.getMensagemRespostaPernaQuatroSequence()) )
                .andExpect( MockMvcResultMatchers.jsonPath("mensagemSolicitacaoPernaDoisSequence").value(dto.getMensagemSolicitacaoPernaDoisSequence()) )
                .andExpect( MockMvcResultMatchers.jsonPath("mensagemRespostaPernaTresSequence").value(dto.getMensagemRespostaPernaTresSequence()) )
                .andExpect( MockMvcResultMatchers.jsonPath("bitVinculacaoMensagensPernasUmQuatro").value(dto.getBitVinculacaoMensagensPernasUmQuatro()) )
                .andExpect( MockMvcResultMatchers.jsonPath("bitVinculacaoMensagensPernasDoisTres").value(dto.getBitVinculacaoMensagensPernasDoisTres()) );

    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não houver dados suficientes para criação do fluxo transacional")
    public void createDadosIncompletosFLuxoTransacional() throws Exception {
        String json = new ObjectMapper().writeValueAsString(new FluxoTransacionalRequestDTO());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(FLUXO_TRANSACIONAL_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isBadRequest() )
                .andExpect( MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(1)) ) ;
    }

    @Test
    @DisplayName("Deve atualizar um fluxo transacional com sucesso")
    public void updateFluxoTransacionalCodigosMensagensValidosTest() throws Exception {

        Long fluxoTransacionalSequence = 1L;
        FluxoTransacionalResponseDTO fluxoTransacionalSalvo =  FluxoTransacionalResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .fluxoTransacionalSequence(1L)
                .descricao("Cadastro de fluxo transacional")
                .mensagemSolicitacaoPernaUmSequence(1L)
                .mensagemSolicitacaoPernaDoisSequence(1L)
                .mensagemRespostaPernaTresSequence(1L)
                .mensagemRespostaPernaQuatroSequence(1L)
                .bitVinculacaoMensagensPernasUmQuatro(11)
                .bitVinculacaoMensagensPernasDoisTres(11)
                .build();

        FluxoTransacionalRequestDTO fluxoTransacionalRequest = FluxoTransacionalRequestDTO
                .builder()
                .descricao("Cadastro de fluxo transacional")
                .mensagemSolicitacaoPernaUmSequence(1L)
                .mensagemSolicitacaoPernaDoisSequence(1L)
                .mensagemRespostaPernaTresSequence(1L)
                .mensagemRespostaPernaQuatroSequence(1L)
                .bitVinculacaoMensagensPernasUmQuatro(11)
                .bitVinculacaoMensagensPernasDoisTres(11)
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.PUT, "/api/fluxostransacionais/" + fluxoTransacionalSequence)
                .headers(new HttpHeaders())
                .content(new ObjectMapper().writeValueAsString(fluxoTransacionalRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.update(BDDMockito.any(),BDDMockito.any())).willReturn(fluxoTransacionalSalvo);

        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("fluxoTransacionalSequence").value(fluxoTransacionalSalvo.getFluxoTransacionalSequence()))
                .andExpect(jsonPath("id").value(fluxoTransacionalSalvo.getId()))
                .andDo(print())
                .andReturn();

    }

    @Test
    @DisplayName("Deve retornar erro ao atualizar um fluxo transacional")
    public void updateFluxoTransacionalDadosIncompletosTest() throws Exception {

        Long fluxoTransacionalSequence = 1L;
        String json = new ObjectMapper().writeValueAsString(new FluxoTransacionalRequestDTO());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(FLUXO_TRANSACIONAL_API + "/" + fluxoTransacionalSequence)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isBadRequest() )
                .andExpect( MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(1)) )
                .andDo(print());

    }

    @Test
    @DisplayName("Deve retornar um fluxo transacional valido")
    public void getFluxoTrasacionalIdValidoTest() throws Exception {

        FluxoTransacionalResponseDTO fluxoTransacionalSalvo =  FluxoTransacionalResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .fluxoTransacionalSequence(1L)
                .descricao("Cadastro de fluxo transacional")
                .mensagemSolicitacaoPernaUmSequence(1L)
                .mensagemSolicitacaoPernaDoisSequence(1L)
                .mensagemRespostaPernaTresSequence(1L)
                .mensagemRespostaPernaQuatroSequence(1L)
                .bitVinculacaoMensagensPernasUmQuatro(11)
                .bitVinculacaoMensagensPernasDoisTres(11)
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/fluxostransacionais/5ffc7cc8324f9c7563d41d71")
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findById(BDDMockito.any())).willReturn(fluxoTransacionalSalvo);

        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("fluxoTransacionalSequence").value(fluxoTransacionalSalvo.getFluxoTransacionalSequence()))
                .andExpect(jsonPath("id").value(fluxoTransacionalSalvo.getId()))
                .andDo(print())
                .andReturn();

    }

    @Test
    @DisplayName("Deve retornar um fluxo transacional valido")
    public void getFluxoTransacionalCodigoValidoTest() throws Exception {

        FluxoTransacionalResponseDTO fluxoTransacionalSalvo =  FluxoTransacionalResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .fluxoTransacionalSequence(1L)
                .descricao("Cadastro de fluxo transacional")
                .mensagemSolicitacaoPernaUmSequence(1L)
                .mensagemSolicitacaoPernaDoisSequence(1L)
                .mensagemRespostaPernaTresSequence(1L)
                .mensagemRespostaPernaQuatroSequence(1L)
                .bitVinculacaoMensagensPernasUmQuatro(11)
                .bitVinculacaoMensagensPernasDoisTres(11)
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/fluxostransacionais/seq/1")
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findByFluxoTransacionalSequence(BDDMockito.any())).willReturn(fluxoTransacionalSalvo);

        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("fluxoTransacionalSequence").value(fluxoTransacionalSalvo.getFluxoTransacionalSequence()))
                .andExpect(jsonPath("id").value(fluxoTransacionalSalvo.getId()))
                .andDo(print())
                .andReturn();

    }

    @Test
    @DisplayName("Deve retornar um fluxo transacional valido")
    public void getAllProtocoloCodigoValidoTest() throws Exception {

        FluxoTransacionalResponseDTO fluxoTransacionalSalvo =  FluxoTransacionalResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .fluxoTransacionalSequence(1L)
                .descricao("Cadastro de fluxo transacional")
                .mensagemSolicitacaoPernaUmSequence(1L)
                .mensagemSolicitacaoPernaDoisSequence(1L)
                .mensagemRespostaPernaTresSequence(1L)
                .mensagemRespostaPernaQuatroSequence(1L)
                .bitVinculacaoMensagensPernasUmQuatro(11)
                .bitVinculacaoMensagensPernasDoisTres(11)
                .build();

        List<FluxoTransacionalResponseDTO> fluxoTransacionalSalvoList = new ArrayList<>();
        fluxoTransacionalSalvoList.add(fluxoTransacionalSalvo);

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/fluxostransacionais/")
                .queryParam("fluxoTransacionalSequence","1")
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findAll(BDDMockito.any())).willReturn(fluxoTransacionalSalvoList);

        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].fluxoTransacionalSequence").value(fluxoTransacionalSalvo.getFluxoTransacionalSequence()))
                .andExpect(jsonPath("$.[0].id").value(fluxoTransacionalSalvo.getId()))
                .andExpect( MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andDo(print())
                .andReturn();

    }

    private FluxoTransacionalRequestDTO criaNovoFluxoTransacional() {
        return FluxoTransacionalRequestDTO
                .builder()
                .descricao("Cadastro de fluxo transacional")
                .mensagemSolicitacaoPernaUmSequence(1L)
                .mensagemSolicitacaoPernaDoisSequence(1L)
                .mensagemRespostaPernaTresSequence(1L)
                .mensagemRespostaPernaQuatroSequence(1L)
                .bitVinculacaoMensagensPernasUmQuatro(11)
                .bitVinculacaoMensagensPernasDoisTres(11)
                .build();
    }
}
