package br.com.elo.steps.fluxoTransacional;


import br.com.elo.dto.BandeiraRequestDTO;
import br.com.elo.dto.FluxoTransacionalRequestDTO;
import br.com.elo.fixture.BandeiraFixture;
import br.com.elo.fixture.FluxoTransacionalFixture;
import br.com.elo.fixture.MensagemISO8583Fixture;
import cucumber.api.java.pt.Dado;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class FluxoTransacionalCommonSteps {

    @Autowired
    private FluxoTransacionalFixture fluxoTransacionalFixture;

    @Autowired
    private MensagemISO8583Fixture mensagemISO8583Fixture;


    @Dado("que possua um fluxoTransacional cadastrado:")
    public void que_possua_um_fluxoTransacional_cadastrado(List<FluxoTransacionalRequestDTO> fluxoTransacionalRequestDTOList) {

        for (FluxoTransacionalRequestDTO fluxoTransacionalRequestDTO : fluxoTransacionalRequestDTOList) {
            fluxoTransacionalRequestDTO.setMensagemSolicitacaoPernaUmSequence(mensagemISO8583Fixture.getResultado().getBody().getMensagemSequence());
            fluxoTransacionalRequestDTO.setMensagemRespostaPernaQuatroSequence(mensagemISO8583Fixture.getResultado().getBody().getMensagemSequence());
            fluxoTransacionalFixture.incluirFluxoTransacional(fluxoTransacionalRequestDTO);
        }
    }


}
