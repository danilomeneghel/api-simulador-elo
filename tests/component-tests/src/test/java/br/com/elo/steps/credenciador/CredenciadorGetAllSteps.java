package br.com.elo.steps.credenciador;


import br.com.elo.dto.CredenciadorRequestCriteriaDTO;
import br.com.elo.dto.CredenciadorResponseDTO;
import br.com.elo.fixture.CredenciadorFixture;
import cucumber.api.java.pt.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CredenciadorGetAllSteps {

    @Autowired
    private CredenciadorFixture credenciadorFixture;

    CredenciadorResponseDTO credenciadorEsperado;
    List<CredenciadorResponseDTO> credenciadorResponse;


    @Quando("realizo o getAll da API de credenciador")
    public void realizo_o_getAll_da_API_de_credenciador() {

        credenciadorEsperado = this.credenciadorFixture.getResultado().getBody();
        credenciadorResponse = Arrays.asList(credenciadorFixture.buscaCredenciador(CredenciadorRequestCriteriaDTO.builder().codigo(credenciadorEsperado.getCredenciadorCodigo()).build()).getBody());
    }

    @Então("o response apresenta uma lista com o credenciador cadastrado")
    public void o_response_apresenta_uma_lista_com_o_credenciador_cadastrado() {

        assertNotNull(credenciadorResponse);
        assertEquals(credenciadorResponse.get(0).getId(), credenciadorEsperado.getId());
        //assertEquals(credenciadorResponse.get(0).getCodigo(), credenciadorEsperado.getCodigo());
        assertEquals(credenciadorResponse.get(0).getDataCriacao(), credenciadorEsperado.getDataCriacao());
        assertEquals(credenciadorResponse.get(0).getCredenciadorCodigo(), credenciadorEsperado.getCredenciadorCodigo());
        assertEquals(credenciadorResponse.get(0).getNome(), credenciadorEsperado.getNome());

    }
}