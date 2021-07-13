package br.com.elo.steps.bandeira;


import br.com.elo.dto.BandeiraRequestDTO;
import br.com.elo.dto.BandeiraResponseDTO;
import br.com.elo.fixture.BandeiraFixture;
import cucumber.api.DataTable;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class BandeiraPostSteps {

    @Autowired
    private BandeiraFixture bandeiraFixture;

    BandeiraRequestDTO bandeiraRequest;
    BandeiraResponseDTO bandeiraResponse;

    @Dado("que possua uma bandeira com payload:")
    public void que_possua_uma_bandeira_com_payload(DataTable dataTable) {

        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            bandeiraRequest = BandeiraRequestDTO.builder().codigoBandeira(Integer.parseInt(columns.get("codigoBandeira"))).descricao(columns.get("descricao")).build();
        }
    }

    @Quando("realizo um POST desta bandeira")
    public void realizo_um_post_desta_bandeira() {

        bandeiraResponse = bandeiraFixture.incluirBandeira(bandeiraRequest).getBody();
    }

    @Então("o response apresenta todos os campos referente a bandeira preenchidos corretamente")
    public void o_response_apresenta_todos_os_campos_referente_a_bandeira_preenchidos_corretamente() {

        assertNotNull(bandeiraResponse.getId());
        assertNotNull(bandeiraResponse.getCodigoBandeira());
        assertNotNull(bandeiraResponse.getDataCriacao());
        assertEquals(bandeiraResponse.getCodigoBandeira(), bandeiraRequest.getCodigoBandeira());
        assertEquals(bandeiraResponse.getDescricao(), bandeiraRequest.getDescricao());

    }


}
