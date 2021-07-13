package br.com.elo.steps.fluxoTransacional;



import br.com.elo.dto.FluxoTransacionalRequestDTO;
import br.com.elo.dto.FluxoTransacionalResponseDTO;
import br.com.elo.dto.MensagemISO8583RequestDTO;
import br.com.elo.dto.MensagemISO8583ResponseDTO;
import br.com.elo.fixture.FluxoTransacionalFixture;
import br.com.elo.fixture.MensagemISO8583Fixture;
import br.com.elo.fixture.ProtocoloFixture;
import cucumber.api.DataTable;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class FluxoTransacionalPutSteps {

    @Autowired
    private FluxoTransacionalFixture fluxoTransacionalFixture;

    FluxoTransacionalResponseDTO fluxoTransacionalCadastrado;
    FluxoTransacionalRequestDTO fluxoTransacionalAlterado;
    FluxoTransacionalResponseDTO fluxoTransacionalResponse;


    @Quando("^realizo um put com a descricao do fluxoTransacional alterada$")
    public void realizo_um_put_com_a_descricao_do_fluxoTransacional_alterada() {

        fluxoTransacionalCadastrado = fluxoTransacionalFixture.getResultado().getBody();
        fluxoTransacionalAlterado = FluxoTransacionalRequestDTO.builder()
                                    .mensagemSolicitacaoPernaUmSequence(fluxoTransacionalCadastrado.getMensagemSolicitacaoPernaUmSequence())
                                    .mensagemRespostaPernaQuatroSequence(fluxoTransacionalCadastrado.getMensagemRespostaPernaQuatroSequence())
                                    .descricao("Descricao fluxoTransacional alterada")
                                    .build();
        fluxoTransacionalResponse = fluxoTransacionalFixture.atualizaFluxoTransacional(fluxoTransacionalAlterado,fluxoTransacionalCadastrado.getFluxoTransacionalSequence()).getBody();
    }

    @Então("^o response da alteração de FluxoTransacional exibe os dados alterados corretamente$")
    public void o_response_da_alteração_de_FluxoTransacional_exibe_os_dados_alterados_corretamente(){

        assertNotNull(fluxoTransacionalResponse.getId());
        assertNotNull(fluxoTransacionalResponse.getFluxoTransacionalSequence());
        assertNotNull(fluxoTransacionalResponse.getDataCriacao());
        assertNotNull(fluxoTransacionalResponse.getDataAlteracao());
        assertEquals(fluxoTransacionalResponse.getDescricao(), fluxoTransacionalAlterado.getDescricao());

    }

}
