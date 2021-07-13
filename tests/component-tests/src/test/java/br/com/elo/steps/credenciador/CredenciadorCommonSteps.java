package br.com.elo.steps.credenciador;


import br.com.elo.dto.CredenciadorRequestDTO;
import br.com.elo.fixture.CredenciadorFixture;
import cucumber.api.java.pt.Dado;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class CredenciadorCommonSteps {

    @Autowired
    private CredenciadorFixture credenciadorFixture;


    @Dado("que possua um credenciador cadastrado:")
    public void que_possua_um_credenciador_cadastrado(List<CredenciadorRequestDTO> credenciadorRequestDTOList) {

        for (CredenciadorRequestDTO credenciadorRequestDTO : credenciadorRequestDTOList) {
            credenciadorFixture.incluirCredenciador(credenciadorRequestDTO);
        }
    }
}