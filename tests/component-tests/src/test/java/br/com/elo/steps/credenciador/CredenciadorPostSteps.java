package br.com.elo.steps.credenciador;


import br.com.elo.domain.Status;
import br.com.elo.dto.CredenciadorRequestDTO;
import br.com.elo.dto.CredenciadorResponseDTO;
import br.com.elo.fixture.CredenciadorFixture;
import cucumber.api.DataTable;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CredenciadorPostSteps{

    @Autowired
    private CredenciadorFixture credenciadorFixture;

    CredenciadorRequestDTO credenciadorRequest;
    CredenciadorResponseDTO credenciadorResponse;

    @Dado("que possua um credenciador com payload:")
    public void que_possua_um_credenciador_com_payload(DataTable dataTable) {

        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            credenciadorRequest = CredenciadorRequestDTO.builder().credenciadorCodigo(Long.parseLong(columns.get("CREDENCIADORCODIGO"))).nome(columns.get("NOME")).status(Status.valueOf(columns.get("STATUS"))).build();
        }
    }

    @Quando("realizo um POST deste credenciador")
    public void realizo_um_post_desta_bandeira() {

        credenciadorResponse = credenciadorFixture.incluirCredenciador(credenciadorRequest).getBody();
    }

    @Então("o response apresenta todos os campos referente ao credenciador preenchidos corretamente")
    public void o_response_apresenta_todos_os_campos_referente_ao_credenciador_preenchidos_corretamente() {

        assertNotNull(credenciadorResponse.getId());
        //assertNotNull(credenciadorResponse.getCodigo());
        assertNotNull(credenciadorResponse.getDataCriacao());
        assertEquals(credenciadorResponse.getCredenciadorCodigo(), credenciadorRequest.getCredenciadorCodigo());
        assertEquals(credenciadorResponse.getNome(), credenciadorRequest.getNome());

    }
}