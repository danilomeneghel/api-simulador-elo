package br.com.elo.steps.credenciador;


import br.com.elo.dto.CredenciadorResponseDTO;
import br.com.elo.fixture.CredenciadorFixture;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CredenciadorGetIdCodigoSteps{

    @Autowired
    private CredenciadorFixture credenciadorFixture;

    CredenciadorResponseDTO credenciadorEsperado;
    CredenciadorResponseDTO credenciadorResponse;


    @Quando("eu realizo uma busca de credenciador pelo endpoint de busca por id")
    public void eu_realizo_uma_busca_de_credenciador_pelo_endpoint_de_busca_por_id() {

        credenciadorEsperado = this.credenciadorFixture.getResultado().getBody();
        credenciadorResponse = credenciadorFixture.consultaCredenciadorId(credenciadorEsperado.getId()).getBody();
    }

    @Quando("eu realizo uma busca de credenciador pelo endpoint de busca por código")
    public void eu_realizo_uma_busca_de_credenciador_pelo_endpoint_de_busca_por_codigo() {

        credenciadorEsperado = this.credenciadorFixture.getResultado().getBody();
        credenciadorResponse = credenciadorFixture.consultaCredenciadorCodigo(credenciadorEsperado.getCredenciadorCodigo()).getBody();
    }

    @Então("o response apresenta os campos do Credenciador de acordo com a base de dados")
    public void o_response_apresenta_os_campos_do_Credenciador_de_acordo_com_a_base_de_dados() {

        assertNotNull(credenciadorResponse);
       // assertEquals(credenciadorResponse.getCodigo(), credenciadorEsperado.getCodigo());
        assertEquals(credenciadorResponse.getDataCriacao(), credenciadorEsperado.getDataCriacao());
        assertEquals(credenciadorResponse.getCredenciadorCodigo(), credenciadorEsperado.getCredenciadorCodigo());
        assertEquals(credenciadorResponse.getNome(), credenciadorEsperado.getNome());

    }
}