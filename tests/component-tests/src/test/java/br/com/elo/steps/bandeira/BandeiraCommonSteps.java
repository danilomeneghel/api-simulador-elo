package br.com.elo.steps.bandeira;


import br.com.elo.dto.BandeiraRequestDTO;
import br.com.elo.fixture.BandeiraFixture;
import cucumber.api.java.pt.Dado;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


public class BandeiraCommonSteps {

    @Autowired
    private BandeiraFixture bandeiraFixture;


    @Dado("que possua uma bandeira cadastrada:")
    public void que_possua_uma_bandeira_cadastrada(List<BandeiraRequestDTO> bandeiraRequestDTOList) {

        for (BandeiraRequestDTO bandeiraRequestDTO : bandeiraRequestDTOList) {
            bandeiraFixture.incluirBandeira(bandeiraRequestDTO);
        }
    }


}
