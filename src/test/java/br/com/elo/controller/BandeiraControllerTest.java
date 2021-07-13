package br.com.elo.controller;

import br.com.elo.domain.dto.request.BandeiraRequestDTO;
import br.com.elo.domain.dto.response.BandeiraResponseDTO;
import br.com.elo.service.BandeiraService;
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
public class BandeiraControllerTest {

    static String BANDEIRA_API = "/api/bandeiras";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BandeiraService service;

    @Test
    @DisplayName("Deve criar uma bandeira com sucesso")
    public void createBandeiraTest() throws Exception {

        BandeiraRequestDTO bandeiraRequest = BandeiraRequestDTO
                .builder()
                .codigoBandeira(7)
                .descricao("Bandeira ELO")
                .build();

        BandeiraResponseDTO bandeiraSalva = BandeiraResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .codigoBandeira(7)
                .descricao("Bandeira ELO")
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.POST, "/api/bandeiras")
                .headers(new HttpHeaders())
                .content(new ObjectMapper().writeValueAsString(bandeiraRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.save(BDDMockito.any())).willReturn(bandeiraSalva);

        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("codigoBandeira").value(bandeiraSalva.getCodigoBandeira()))
                .andExpect(jsonPath("id").value(bandeiraSalva.getId()))
                .andDo(print())
                .andReturn();

    }


    @Test
    @DisplayName("Não deve criar a bandeira pois tamanho do codigoBandeira maior que o permitido ")
    public void createBandeiraCodigoBandeiraInvalido() throws Exception {
        BandeiraRequestDTO bandeiraRequest = BandeiraRequestDTO
                .builder()
                .codigoBandeira(1000)
                .descricao("Bandeira ELO 1001")
                .build();


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders

                .post(BANDEIRA_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(bandeiraRequest));

        mockMvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isBadRequest() )
                .andExpect( MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(1)) )
                .andDo(print());
    }

    @Test
    @DisplayName("Não deve criar a bandeira pois tamanho da descrição maior que o permitido ")
    public void createBandeiraDescricaoTamanhoMaior() throws Exception {
        BandeiraRequestDTO bandeiraRequest = BandeiraRequestDTO
                .builder()
                .codigoBandeira(7)
                .descricao("lorem ipsum dolor sit amet consectetur adipiscing elit class volutpat accumsan orci ad libero eleifend ex nulla pharetra facilisi eros fusce blandit metus aliquet laoreet sagittis curae tortor sociosqu rutrum quis curabitur porttitor lectus gravida bibendum dapibus consequat facilisis ultricies auctor massa cubilia habitant parturient feugiat pulvinar primis maximus et cursus praesent montes nunc viverra netus velit aptent varius tellus erat iaculis leo finibus arcu urna suscipit vulputate at mauris lacus nisl pretium ornare dictum fames aenean torquent sollicitudin senectus eget placerat luctus hendrerit vehicula condimentum augue imperdiet diam tempor mollis")
                .build();


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders

                .post(BANDEIRA_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(bandeiraRequest));

        mockMvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isBadRequest() )
                .andExpect( MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(1)) )
                .andDo(print());
    }


    @Test
    public void createBandeiraDadosIncompletos() throws Exception {
                String json = new ObjectMapper().writeValueAsString(new BandeiraRequestDTO());
                MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                                .post(BANDEIRA_API)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(json);

        mockMvc.perform(request)
                                .andExpect( MockMvcResultMatchers.status().isBadRequest() )
                                .andExpect( MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(2)) )
                                .andDo(print());
    }

    @Test
    @DisplayName("Deve criar uma bandeira com sucesso")
    public void updateBandeiraCodigoValidoTest() throws Exception {

        Integer codigoBandeira = 1;
        BandeiraResponseDTO bandeiraSalva = BandeiraResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .codigoBandeira(7)
                .descricao("Bandeira ELO")
                .build();

        BandeiraRequestDTO bandeiraRequest = BandeiraRequestDTO
                .builder()
                .codigoBandeira(7)
                .descricao("Bandeira ELO")
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.PUT, "/api/bandeiras/"+codigoBandeira)
                .headers(new HttpHeaders())
                .content(new ObjectMapper().writeValueAsString(bandeiraRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.update(BDDMockito.any(),BDDMockito.any())).willReturn(bandeiraSalva);

        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("codigoBandeira").value(bandeiraSalva.getCodigoBandeira()))
                .andExpect(jsonPath("id").value(bandeiraSalva.getId()))
                .andDo(print())
                .andReturn();

    }

    @Test
    @DisplayName("Deve criar uma bandeira com sucesso")
    public void updateBandeiraDadosIncompletosTest() throws Exception {

        Long codigo = 1L;
        String json = new ObjectMapper().writeValueAsString(new BandeiraRequestDTO());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(BANDEIRA_API + "/" + codigo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect( MockMvcResultMatchers.status().isBadRequest() )
                .andExpect( MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(2)) )
                .andDo(print());

    }

    @Test
    @DisplayName("Deve retornar uma bandeira valida")
    public void getBandeiraIdValidoTest() throws Exception {

        BandeiraResponseDTO bandeiraSalva = BandeiraResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .codigoBandeira(7)
                .descricao("Bandeira ELO")
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/bandeiras/5ffc7cc8324f9c7563d41d71")
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findById(BDDMockito.any())).willReturn(bandeiraSalva);

        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("codigoBandeira").value(bandeiraSalva.getCodigoBandeira()))
                .andExpect(jsonPath("id").value(bandeiraSalva.getId()))
                .andDo(print())
                .andReturn();

    }

    @Test
    @DisplayName("Deve retornar uma bandeira valida")
    public void getBandeiraCodigoValidoTest() throws Exception {

        BandeiraResponseDTO bandeiraSalva = BandeiraResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .codigoBandeira(7)
                .descricao("Bandeira ELO")
                .build();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/bandeiras/cod/1")
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findByCodigoBandeira(BDDMockito.any())).willReturn(bandeiraSalva);

        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("codigoBandeira").value(bandeiraSalva.getCodigoBandeira()))
                .andExpect(jsonPath("id").value(bandeiraSalva.getId()))
                .andDo(print())
                .andReturn();

    }

    @Test
    @DisplayName("Deve retornar uma bandeira valida")
    public void getAllBandeiraCodigoBandeiraValidoTest() throws Exception {

        BandeiraResponseDTO bandeiraSalva  = BandeiraResponseDTO
                .builder()
                .id("5ffc7cc8324f9c7563d41d71")
                .codigoBandeira(7)
                .descricao("Bandeira ELO")
                .build();

        List<BandeiraResponseDTO> bandeiraSalvaList = new ArrayList<>();
        bandeiraSalvaList.add(bandeiraSalva);

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/bandeiras/")
                .queryParam("codigoBandeira","7")
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        given(service.findAll(BDDMockito.any())).willReturn(bandeiraSalvaList);

        final MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(bandeiraSalva.getId()))
                .andExpect(jsonPath("$.[0].codigoBandeira").value(bandeiraSalva.getCodigoBandeira()))
                .andExpect( MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andDo(print())
                .andReturn();

    }

}
