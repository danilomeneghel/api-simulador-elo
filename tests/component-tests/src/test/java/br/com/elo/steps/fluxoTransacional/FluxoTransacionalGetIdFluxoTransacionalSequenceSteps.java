package br.com.elo.steps.fluxoTransacional;



import br.com.elo.dto.FluxoTransacionalRequestDTO;
import br.com.elo.dto.FluxoTransacionalResponseDTO;
import br.com.elo.fixture.FluxoTransacionalFixture;
import br.com.elo.fixture.MensagemISO8583Fixture;
import cucumber.api.DataTable;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class FluxoTransacionalGetIdFluxoTransacionalSequenceSteps {

    @Autowired
    private FluxoTransacionalFixture fluxoTransacionalFixture;

    @Autowired
    private MensagemISO8583Fixture mensagemISO8583Fixture;

    FluxoTransacionalRequestDTO fluxoTransacionalRequest;
    FluxoTransacionalResponseDTO fluxoTransacionalEsperado;
    FluxoTransacionalResponseDTO fluxoTransacionalResponse;



    @Quando("^eu realizo uma busca de fluxo transacional pelo endpoint de busca por id$")
    public void eu_realizo_uma_busca_de_fluxo_transacional_pelo_endpoint_de_busca_por_id(){

        fluxoTransacionalEsperado = this.fluxoTransacionalFixture.getResultado().getBody();
        fluxoTransacionalResponse = this.fluxoTransacionalFixture.consultaFluxoTransacionalId(fluxoTransacionalEsperado.getId()).getBody();
    }

    @Quando("^eu realizo uma busca de fluxo transacional pelo endpoint de busca por fluxoTransacionalSequence$")
    public void eu_realizo_uma_busca_de_fluxo_transacional_pelo_endpoint_de_busca_por_FluxoTransacionalSequence(){

        fluxoTransacionalEsperado = this.fluxoTransacionalFixture.getResultado().getBody();
        fluxoTransacionalResponse = this.fluxoTransacionalFixture.consultaFluxoTransacionalPorFluxoTransacionalSequence(fluxoTransacionalEsperado.getFluxoTransacionalSequence()).getBody();

    }

    @Entao("^o response apresenta os campos do fluxo transacional de acordo com a base de dados$")
    public void o_response_apresenta_os_campos_do_fluxo_transacional_de_acordo_com_a_base_de_dados(){

        assertNotNull(fluxoTransacionalResponse);
        assertEquals(fluxoTransacionalResponse.getFluxoTransacionalSequence(),fluxoTransacionalResponse.getFluxoTransacionalSequence());
        assertEquals(fluxoTransacionalResponse.getDataCriacao(),fluxoTransacionalResponse.getDataCriacao());
        assertEquals(fluxoTransacionalResponse.getMensagemSolicitacaoPernaUmSequence(), fluxoTransacionalResponse.getMensagemSolicitacaoPernaUmSequence());
        assertEquals(fluxoTransacionalResponse.getMensagemSolicitacaoPernaDoisSequence(), fluxoTransacionalResponse.getMensagemSolicitacaoPernaDoisSequence());

    }



}
