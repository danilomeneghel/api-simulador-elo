package br.com.elo.steps.emissor;

import br.com.elo.domain.Plataforma;
import br.com.elo.dto.emissor.EmissorRequestDTO;
import br.com.elo.dto.emissor.EmissorResponseDTO;
import br.com.elo.fixture.emissor.EmissorFixture;
import cucumber.api.DataTable;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class EmissorPostSteps {

    @Autowired
    private EmissorFixture emissorFixture;

    EmissorRequestDTO emissorRequestDTO;
    EmissorResponseDTO emissorResponseDTO;

    @Dado("^um Emissor com payload:$")
    public void um_Emissor_com_payload(DataTable dataTable){
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            emissorRequestDTO = EmissorRequestDTO
                    .builder()
                    .codigoEmissor(Integer.parseInt(columns.get("CODIGOEMI")))
                    .nomeEmissor(columns.get("NOMEEMI"))
                    .codigoProcessadora(Integer.parseInt(columns.get("CODPROCESSADORA")))
                    .plataforma(Plataforma.valueOf(columns.get("PLATAFORMA")))
                    .codigoBandeira(Integer.parseInt(columns.get("BANDEIRACOD")))
                    .build();
        }
    }

    @Quando("^realizo um POST deste Emissor$")
    public void realizo_um_POST_deste_Emissor(){
        emissorResponseDTO = emissorFixture.incluirEmissor(emissorRequestDTO).getBody();
    }

    @Então("o response apresenta todos os campos referente ao Emissor preenchidos corretamente")
    public void o_response_apresenta_todos_os_campos_referente_ao_Emissor_preenchidos_corretamente(){
        assertThat(emissorResponseDTO.getId()).isNotNull();
        assertThat(emissorResponseDTO.getCodigoEmissor()).isNotNull();
        assertThat(emissorResponseDTO.getCodigoBandeira()).isNotNull();
        assertThat(emissorResponseDTO.getDataCriacao()).isNotNull();
        assertThat(emissorResponseDTO.getCodigoProcessadora()).isNotNull();
        assertThat(emissorResponseDTO.getPlataforma()).isNotNull();


    }

}
