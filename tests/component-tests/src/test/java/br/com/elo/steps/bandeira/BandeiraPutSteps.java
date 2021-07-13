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


public class BandeiraPutSteps {

    @Autowired
    private BandeiraFixture bandeiraFixture;

    BandeiraResponseDTO bandeiraCadastrado;
    BandeiraRequestDTO bandeiraAlterado;
    BandeiraResponseDTO bandeiraResponse;


    @Quando("realizo um put com o codigoBandeira alterado")
    public void realizo_um_put_com_o_codigoBandeira_alterado() {

        bandeiraCadastrado = this.bandeiraFixture.getResultado().getBody();
        bandeiraAlterado = BandeiraRequestDTO.builder().codigoBandeira(8).descricao(bandeiraCadastrado.getDescricao()).build();
        bandeiraResponse = bandeiraFixture.atualizaBandeira(bandeiraAlterado, bandeiraCadastrado.getCodigoBandeira()).getBody();
    }

    @Quando("realizo um put com a descricao da bandeira alterada")
    public void realizo_um_put_com_a_descricao_da_bandeira_alterada() {

        bandeiraCadastrado = this.bandeiraFixture.getResultado().getBody();
        bandeiraAlterado = BandeiraRequestDTO.builder().codigoBandeira(bandeiraCadastrado.getCodigoBandeira()).descricao("descricao alterada teste").build();
        bandeiraResponse = bandeiraFixture.atualizaBandeira(bandeiraAlterado, bandeiraCadastrado.getCodigoBandeira()).getBody();
    }

    @Quando("realizo um put com o codigoBandeira e descricao alterados")
    public void realizo_um_put_com_o_codigoBandeira_e_descricao_alterados() {

        bandeiraCadastrado = this.bandeiraFixture.getResultado().getBody();
        bandeiraAlterado = BandeiraRequestDTO.builder().codigoBandeira(8).descricao("descricao alterada teste").build();
        bandeiraResponse = bandeiraFixture.atualizaBandeira(bandeiraAlterado, bandeiraCadastrado.getCodigoBandeira()).getBody();
    }

    @Então("o response da alteração de Bandeira exibe os dados alterados corretamente")
    public void o_response_da_alteração_de_Bandeira_exibe_os_dados_alterados_corretamente() {

        assertNotNull(bandeiraResponse.getId());
        assertNotNull(bandeiraResponse.getCodigoBandeira());
        assertNotNull(bandeiraResponse.getDataCriacao());
        assertNotNull(bandeiraResponse.getDataAlteracao());
        assertEquals(bandeiraResponse.getCodigoBandeira(), bandeiraAlterado.getCodigoBandeira());
        assertEquals(bandeiraResponse.getDescricao(), bandeiraAlterado.getDescricao());
    }


}
