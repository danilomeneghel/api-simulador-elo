package br.com.elo.steps.bandeira;


import br.com.elo.dto.BandeiraRequestCriteriaDTO;
import br.com.elo.dto.BandeiraRequestDTO;
import br.com.elo.dto.BandeiraResponseDTO;
import br.com.elo.fixture.BandeiraFixture;
import cucumber.api.DataTable;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class BandeiraGetAllSteps {

    @Autowired
    private BandeiraFixture bandeiraFixture;

    BandeiraResponseDTO bandeiraEsperado;
    List<BandeiraResponseDTO> bandeiraResponse;


    @Quando("realizo o getAll da API de bandeira")
    public void realizo_o_getAll_da_API_de_bandeira() {

        bandeiraEsperado = this.bandeiraFixture.getResultado().getBody();
        bandeiraResponse = Arrays.asList(bandeiraFixture.buscaBandeira(BandeiraRequestCriteriaDTO.builder().codigoBandeira(bandeiraEsperado.getCodigoBandeira()).build()).getBody());
    }

    @Então("o response apresenta uma lista com a bandeira cadastrada")
    public void o_response_apresenta_uma_lista_com_a_bandeira_cadastrada() {

        assertNotNull(bandeiraResponse);
        assertNotNull(bandeiraResponse.get(0).getCodigoBandeira());
        assertEquals(bandeiraResponse.get(0).getCodigoBandeira(), bandeiraEsperado.getCodigoBandeira());
        assertEquals(bandeiraResponse.get(0).getDataCriacao(),bandeiraEsperado.getDataCriacao());
        assertEquals(bandeiraResponse.get(0).getDescricao(), bandeiraEsperado.getDescricao());

    }


}
