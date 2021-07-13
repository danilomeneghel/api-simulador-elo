package br.com.elo.steps.ambiente;

import br.com.elo.dto.AmbienteRequestCriteriaDTO;
import br.com.elo.dto.AmbienteResponseDTO;
import br.com.elo.fixture.AmbienteFixture;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AmbienteGetIdAmbienteSequenceSteps {

    AmbienteResponseDTO ambienteEsperado;
    List<AmbienteResponseDTO> ambienteResponse;
    ResponseEntity<AmbienteResponseDTO> responseEntity;

    @Autowired
    private AmbienteFixture ambienteFixture;

    @Quando("^eu realizo uma busca de Ambiente pelo endpoint de busca por id$")
    public void eu_realizo_uma_busca_de_Ambiente_pelo_endpoint_de_busca_por_id() throws Exception {
        ambienteEsperado = this.ambienteFixture.getResultado().getBody();
        ambienteResponse = Arrays.asList(ambienteFixture.buscaAmbiente(AmbienteRequestCriteriaDTO
                .builder()
                .id(ambienteEsperado.getId())
                .build()).getBody());
    }

    @Quando("^eu realizo uma busca de Ambiente pelo endpoint de busca por ambienteSequence$")
    public void eu_realizo_uma_busca_de_Ambiente_pelo_endpoint_de_busca_por_ambienteSequence$() throws Exception {
        ambienteEsperado = this.ambienteFixture.getResultado().getBody();
        ambienteResponse = Arrays.asList(ambienteFixture.buscaAmbiente(AmbienteRequestCriteriaDTO
                .builder()
                .ambienteSequence(ambienteEsperado.getAmbienteSequence())
                .build()).getBody());
    }

    @Quando("^realizo uma busca de Ambiente pelo endpoint de busca por id com valor \"([^\"]*)\"$")
    public void realizo_uma_busca_de_Ambiente_pelo_endpoint_de_busca_por_id_com_valor(String arg1) throws Exception {
        responseEntity = this.ambienteFixture.consultaAmbienteId(arg1);
    }

    @Quando("^realizo uma busca por ambienteSequence com valor \"([^\"]*)\"$")
    public void realizo_uma_busca_por_ambienteSequence_com_valor(String arg1) throws Exception {
        responseEntity = this.ambienteFixture.consultaAmbientePorAmbienteSequence(Long.valueOf(arg1));
    }

    @E("^o response apresenta os dados de Ambiente corretamente conforme a base de dados$")
    public void oResponseApresentaOsDadosDeAmbienteCorretamenteConformeABaseDeDados() {
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

}
