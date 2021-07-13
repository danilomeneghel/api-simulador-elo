package br.com.elo.steps.ambiente;

import br.com.elo.dto.AmbienteRequestCriteriaDTO;
import br.com.elo.dto.AmbienteResponseDTO;
import br.com.elo.fixture.AmbienteFixture;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class AmbienteGetAllSteps {

    AmbienteResponseDTO ambienteEsperado;
    List<AmbienteResponseDTO> ambienteResponse;

    @Autowired
    private AmbienteFixture ambienteFixture;

    @Quando("^eu realizo uma busca por ambienteSequence$")
    public void eu_realizo_uma_busca_por_ambienteSequence() throws Exception {
        ambienteEsperado = this.ambienteFixture.getResultado().getBody();
        ambienteResponse = Arrays.asList(ambienteFixture.buscaAmbiente(AmbienteRequestCriteriaDTO
                .builder()
                .ambienteSequence(ambienteEsperado.getAmbienteSequence())
                .build()).getBody());
    }

    @Então("^o response apresenta os campos do Ambiente corretamente conforme a base de dados$")
    public void o_response_apresenta_os_campos_do_Ambiente_corretamente_conforme_a_base_de_dados() throws Exception {
        assertEquals(ambienteResponse.get(0).getAmbienteSequence(), ambienteEsperado.getAmbienteSequence());
        assertEquals(ambienteResponse.get(0).getDescricao(), ambienteEsperado.getDescricao());
        assertEquals(ambienteResponse.get(0).getStatus().getDescricao(), ambienteEsperado.getStatus().getDescricao());
        assertEquals(ambienteResponse.get(0).getPortasClientes().get(0).getNomePorta(), ambienteEsperado.getPortasClientes().get(0).getNomePorta());
        assertEquals(ambienteResponse.get(0).getPortasClientes().get(0).getNumeroPorta(), ambienteEsperado.getPortasClientes().get(0).getNumeroPorta());
        assertEquals(ambienteResponse.get(0).getPortasServidores().get(0).getNomePorta(), ambienteEsperado.getPortasServidores().get(0).getNomePorta());
        assertEquals(ambienteResponse.get(0).getPortasServidores().get(0).getNumeroPorta(), ambienteEsperado.getPortasServidores().get(0).getNumeroPorta());
        assertEquals(ambienteResponse.get(0).getChaves().getChavePin(), ambienteEsperado.getChaves().getChavePin());
        assertEquals(ambienteResponse.get(0).getChaves().getChaveCavv(), ambienteEsperado.getChaves().getChaveCavv());
    }

    @Quando("^eu realizo uma busca por id$")
    public void eu_realizo_uma_busca_por_id() throws Exception {
        ambienteEsperado = this.ambienteFixture.getResultado().getBody();
        ambienteResponse = Arrays.asList(ambienteFixture.buscaAmbiente(AmbienteRequestCriteriaDTO
                .builder()
                .id(ambienteEsperado.getId())
                .build()).getBody());
    }
}
