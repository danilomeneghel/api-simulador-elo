package br.com.elo.controller;

import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.domain.CvnCartao;
import br.com.elo.domain.TipoCartao;
import br.com.elo.domain.TokenCartao;
import br.com.elo.domain.dto.request.CartaoRequestDTO;
import br.com.elo.domain.dto.response.CartaoResponseDTO;
import br.com.elo.service.CartaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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
public class CartaoControllerTest {

    static String CARTAO_API = "/api/cartoes";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CartaoService service;

    private final CartaoRequestDTO novoCartao = DadosMockUtil.criaNovoCartaoRequestDTO();

    @Test
    @DisplayName("Deve criar um cartao com sucesso")
    public void createCartaoTest() throws Exception {
        CartaoRequestDTO dto = novoCartao;

        CartaoResponseDTO cartaoSalvo =  CartaoResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d73")
                .codigoBandeira(1)
                .codigoEmissor(1)
                .tipoCartao(TipoCartao.CREDITO)
                .nomeCartao("Nome Cartao")
                .pan("1234567890123456789")
                .pin("123456")
                .cardSequenceNumber(Integer.valueOf("123"))
                .cve2("321")
                .dataExpiracao("03/2021")
                .tokenCartao(TokenCartao.SIM)
                .applicationIdentifier("55555")
                .cardAppInterchangeProfile("666666")
                .issuerDiscrData("777777")
                .cardAppTransactionCounter(555)
                .track1("111")
                .track2("222")
                .track2EquivalentData("333")
                .cvnCartao(CvnCartao.CVN05)
                .cvr("22222")
                .dkiKdi("55555")
                .build();

        BDDMockito.given(service.save(BDDMockito.any())).willReturn(cartaoSalvo);

        String json = new ObjectMapper().writeValueAsString(dto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(CARTAO_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isCreated() )
                .andExpect( MockMvcResultMatchers.jsonPath("codigoBandeira").value(dto.getCodigoBandeira()) )
                .andExpect( MockMvcResultMatchers.jsonPath("codigoEmissor").value(dto.getCodigoEmissor()) )
                .andExpect( MockMvcResultMatchers.jsonPath("tipoCartao").value(dto.getTipoCartao().toString()) )
                .andExpect( MockMvcResultMatchers.jsonPath("nomeCartao").value(dto.getNomeCartao()) )
                .andExpect( MockMvcResultMatchers.jsonPath("pan").value(dto.getPan()) )
                .andExpect( MockMvcResultMatchers.jsonPath("pin").value(dto.getPin()) )
                .andExpect( MockMvcResultMatchers.jsonPath("cardSequenceNumber").value(dto.getCardSequenceNumber()) )
                .andExpect( MockMvcResultMatchers.jsonPath("cve2").value(dto.getCve2()) )
                .andExpect( MockMvcResultMatchers.jsonPath("dataExpiracao").value(dto.getDataExpiracao()) )
                .andExpect( MockMvcResultMatchers.jsonPath("tokenCartao").value(dto.getTokenCartao().toString()) )
                .andExpect( MockMvcResultMatchers.jsonPath("cvnCartao").value(dto.getCvnCartao().toString()) )
                .andExpect( MockMvcResultMatchers.jsonPath("applicationIdentifier").value(dto.getApplicationIdentifier()) )
                .andExpect( MockMvcResultMatchers.jsonPath("cardAppInterchangeProfile").value(dto.getCardAppInterchangeProfile()) )
                .andExpect( MockMvcResultMatchers.jsonPath("issuerDiscrData").value(dto.getIssuerDiscrData()) )
                .andExpect( MockMvcResultMatchers.jsonPath("cardAppTransactionCounter").value(dto.getCardAppTransactionCounter()) )
                .andExpect( MockMvcResultMatchers.jsonPath("track1").value(dto.getTrack1()) )
                .andExpect( MockMvcResultMatchers.jsonPath("track2").value(dto.getTrack2()) )
                .andExpect( MockMvcResultMatchers.jsonPath("track2EquivalentData").value(dto.getTrack2EquivalentData()) )
                .andExpect( MockMvcResultMatchers.jsonPath("cvr").value(dto.getCvr()) )
                .andExpect( MockMvcResultMatchers.jsonPath("dkiKdi").value(dto.getDkiKdi()) );
    }
/*
    @Test
    @DisplayName("Deve lançar erro de validação quando não houver dados suficientes para criação do cartao")
    public void createDadosIncompletosCartao() throws Exception {
        String json = new ObjectMapper().writeValueAsString(new CartaoRequestDTO());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(CARTAO_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isBadRequest() )
                .andExpect( MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(8)) ) ;
    }

    @Test
    @DisplayName("Deve atualizar um cartao com sucesso")
    public void updateCartaoPanValidoTest() throws Exception {

        String pan = "1234567890123456789";
        CartaoResponseDTO cartaoSalvo =  CartaoResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d73")
                .codigoBandeira(1)
                .codigoEmissor(1)
                .tipoCartao(TipoCartao.CREDITO)
                .nomeCartao("Nome Cartao")
                .pan("1234567890123456789")
                .pin("123456")
                .cardSequenceNumber(Integer.valueOf("123"))
                .cve2("321")
                .dataExpiracao("03/2021")
                .tokenCartao(TokenCartao.SIM)
                .applicationIdentifier("55555")
                .cardAppInterchangeProfile("666666")
                .issuerDiscrData("777777")
                .track1("111")
                .track2("222")
                .track2EquivalentData("333")
                .cvnCartao(CvnCartao.CVN05)
                .cvr("22222")
                .dkiKdi("55555")
                .build();

        CartaoRequestDTO cartaoRequest = CartaoRequestDTO
                .builder()
                .codigoBandeira(1)
                .codigoEmissor(1)
                .tipoCartao(TipoCartao.CREDITO)
                .nomeCartao("Nome Cartao")
                .pan("1234567890123456789")
                .pin("123456")
                .cardSequenceNumber(Integer.valueOf("123"))
                .cve2("321")
                .dataExpiracao("03/2021")
                .tokenCartao(TokenCartao.SIM)
                .applicationIdentifier("55555")
                .issuerDiscrData("777777")
                .cvnCartao(CvnCartao.CVN05)
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.PUT, "/api/cartoes/"+pan)
                .headers(new HttpHeaders())
                .content(new ObjectMapper().writeValueAsString(cartaoRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.update(BDDMockito.any(),BDDMockito.any())).willReturn(cartaoSalvo);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("pan").value(cartaoSalvo.getPan()))
                .andExpect(jsonPath("id").value(cartaoSalvo.getId()))
                .andDo(print())
                .andReturn();

    }

    @Test
    @DisplayName("Deve retornar erro ao atualizar um cartao")
    public void updateCartaoDadosIncompletosTest() throws Exception {

        String pan = "1234567890123456789";
        String json = new ObjectMapper().writeValueAsString(new CartaoRequestDTO());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(CARTAO_API + "/" + pan)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isBadRequest() )
                .andExpect( MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(8)) )
                .andDo(print());

    }
*/
    @Test
    @DisplayName("Deve retornar um cartao valido")
    public void getCartaoIdValidoTest() throws Exception {

        CartaoResponseDTO cartaoSalvo =  CartaoResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d73")
                .codigoBandeira(1)
                .codigoEmissor(1)
                .tipoCartao(TipoCartao.CREDITO)
                .nomeCartao("Nome Cartao")
                .pan("1234567890123456789")
                .pin("123456")
                .cardSequenceNumber(Integer.valueOf("123"))
                .cve2("321")
                .dataExpiracao("03/2021")
                .tokenCartao(TokenCartao.SIM)
                .applicationIdentifier("55555")
                .cardAppInterchangeProfile("666666")
                .issuerDiscrData("777777")
                .track1("111")
                .track2("222")
                .track2EquivalentData("333")
                .cvnCartao(CvnCartao.CVN05)
                .cvr("22222")
                .dkiKdi("55555")
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/cartoes/5ffc7cc8324f9c7563d41d71")
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findById(BDDMockito.any())).willReturn(cartaoSalvo);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("pan").value(cartaoSalvo.getPan()))
                .andExpect(jsonPath("id").value(cartaoSalvo.getId()))
                .andDo(print())
                .andReturn();

    }

    @Test
    @DisplayName("Deve retornar um cartao valido")
    public void getCartaoPanValidoTest() throws Exception {

        CartaoResponseDTO cartaoSalvo =  CartaoResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d73")
                .codigoBandeira(1)
                .codigoEmissor(1)
                .tipoCartao(TipoCartao.CREDITO)
                .nomeCartao("Nome Cartao")
                .pan("1234567890123456789")
                .pin("123456")
                .cardSequenceNumber(Integer.valueOf("123"))
                .cve2("321")
                .dataExpiracao("03/2021")
                .tokenCartao(TokenCartao.SIM)
                .applicationIdentifier("55555")
                .cardAppInterchangeProfile("666666")
                .issuerDiscrData("777777")
                .track1("111")
                .track2("222")
                .track2EquivalentData("333")
                .cvnCartao(CvnCartao.CVN05)
                .cvr("22222")
                .dkiKdi("55555")
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/cartoes/cod/1")
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findByPan(BDDMockito.any())).willReturn(cartaoSalvo);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("pan").value(cartaoSalvo.getPan()))
                .andExpect(jsonPath("id").value(cartaoSalvo.getId()))
                .andDo(print())
                .andReturn();

    }

    @Test
    @DisplayName("Deve retornar um cartao valido")
    public void getAllCartaoPanValidoTest() throws Exception {

        CartaoResponseDTO cartaoSalvo =  CartaoResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d73")
                .codigoBandeira(1)
                .codigoEmissor(1)
                .tipoCartao(TipoCartao.CREDITO)
                .nomeCartao("Nome Cartao")
                .pan("1234567890123456789")
                .pin("123456")
                .cardSequenceNumber(Integer.valueOf("123"))
                .cve2("321")
                .dataExpiracao("03/2021")
                .tokenCartao(TokenCartao.SIM)
                .applicationIdentifier("55555")
                .cardAppInterchangeProfile("666666")
                .issuerDiscrData("777777")
                .track1("111")
                .track2("222")
                .track2EquivalentData("333")
                .cvnCartao(CvnCartao.CVN05)
                .cvr("22222")
                .dkiKdi("55555")
                .build();

        List<CartaoResponseDTO> cartaoSalvoList = new ArrayList<>();
        cartaoSalvoList.add(cartaoSalvo);

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/cartoes/")
                .queryParam("pan","1")
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findAll(BDDMockito.any())).willReturn(cartaoSalvoList);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].pan").value(cartaoSalvo.getPan()))
                .andExpect(jsonPath("$.[0].id").value(cartaoSalvo.getId()))
                .andExpect( MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andDo(print())
                .andReturn();

    }

}
