package br.com.elo.steps.bandeira;


import br.com.elo.dto.BandeiraResponseDTO;
import br.com.elo.fixture.BandeiraFixture;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class BandeiraGetIdCodigoSteps {

    @Autowired
    private BandeiraFixture bandeiraFixture;

    BandeiraResponseDTO bandeiraEsperado;
    BandeiraResponseDTO bandeiraResponse;


    @Quando("eu realizo uma busca de bandeira pelo endpoint de busca por id")
    public void eu_realizo_uma_busca_de_bandeira_pelo_endpoint_de_busca_por_id() {

        bandeiraEsperado = this.bandeiraFixture.getResultado().getBody();
        bandeiraResponse = bandeiraFixture.consultaBandeiraId(bandeiraEsperado.getId()).getBody();
    }

    @Quando("eu realizo uma busca de bandeira pelo endpoint de busca por código")
    public void eu_realizo_uma_busca_de_bandeira_pelo_endpoint_de_busca_por_codigo() {

        bandeiraEsperado = this.bandeiraFixture.getResultado().getBody();
        bandeiraResponse = bandeiraFixture.consultaBandeiraCodigo(bandeiraEsperado.getCodigoBandeira()).getBody();
    }

    @Então("o response apresenta os campos da Bandeira de acordo com a base de dados")
    public void o_response_apresenta_os_campos_da_Bandeira_de_acordo_com_a_base_de_dados() {

        assertNotNull(bandeiraResponse);
        assertNotNull(bandeiraResponse.getCodigoBandeira());
        assertEquals(bandeiraResponse.getCodigoBandeira(), bandeiraEsperado.getCodigoBandeira());
        assertEquals(bandeiraResponse.getDataCriacao(),bandeiraEsperado.getDataCriacao());
        assertEquals(bandeiraResponse.getDescricao(), bandeiraEsperado.getDescricao());

    }


}
