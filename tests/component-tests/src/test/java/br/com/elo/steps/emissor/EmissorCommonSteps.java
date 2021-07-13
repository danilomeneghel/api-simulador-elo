package br.com.elo.steps.emissor;

import br.com.elo.dto.BandeiraRequestDTO;
import br.com.elo.dto.BandeiraResponseDTO;
import br.com.elo.dto.emissor.EmissorRequestDTO;
import br.com.elo.fixture.BandeiraFixture;
import br.com.elo.fixture.emissor.EmissorFixture;
import cucumber.api.java.pt.Dado;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmissorCommonSteps {

    @Autowired
    private EmissorFixture emissorFixture;

    @Autowired
    private BandeiraFixture bandeiraFixture;

    BandeiraRequestDTO bandeiraRequest;
    BandeiraResponseDTO bandeiraResponse;

   @Dado("^que possua uma Bandeira cadastrada$")
    public void que_possua_uma_bandeira_cadastrada_para_o_emissor(){
        bandeiraRequest = BandeiraRequestDTO.builder().codigoBandeira(1).descricao("ELO").build();
        bandeiraResponse = bandeiraFixture.incluirBandeira(bandeiraRequest).getBody();
    }

    @Dado("^que eu possua um Emissor cadastrado$")
    public void que_eu_possua_um_Emissor_cadastrado(List<EmissorRequestDTO> emissorRequestDTOList) {
        for (EmissorRequestDTO emissorRequestDTO : emissorRequestDTOList) {
            emissorFixture.incluirEmissor(emissorRequestDTO);
        }
    }
}
