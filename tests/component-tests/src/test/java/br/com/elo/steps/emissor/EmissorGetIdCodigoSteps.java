package br.com.elo.steps.emissor;

import br.com.elo.dto.emissor.EmissorResponseDTO;
import br.com.elo.fixture.emissor.EmissorFixture;
import cucumber.api.PendingException;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class EmissorGetIdCodigoSteps {

    @Autowired
    private EmissorFixture emissorFixture;
    EmissorResponseDTO emissorEsperado;
    EmissorResponseDTO emissorResponse;

    @Quando("eu realizo uma busca pelo endpoint de busca de Emissor por id")
    public void eu_realizo_uma_busca_pelo_endpoint_de_busca_de_Emissor_por_id(){
        emissorEsperado = this.emissorFixture.getResultado().getBody();
        emissorResponse = emissorFixture.consultarEmissorId(emissorEsperado.getId()).getBody();

    }

    @Então("o response apresenta os campos dos Emissor corretamente")
    public void o_response_apresenta_os_campos_dos_Emissor_corretamente(){
        assertEquals(emissorResponse.getId(), emissorEsperado.getId());
        assertEquals(emissorResponse.getCodigoEmissor(), emissorEsperado.getCodigoEmissor());
        assertEquals(emissorResponse.getCodigoEmissor(), emissorResponse.getCodigoEmissor());
        assertEquals(emissorResponse.getDataCriacao(), emissorResponse.getDataCriacao());
        assertEquals(emissorResponse.getCodigoProcessadora(), emissorResponse.getCodigoProcessadora());
        assertEquals(emissorResponse.getPlataforma(), emissorResponse.getPlataforma());
        //assertEquals(emissorResponse.getSchemaVersion(), emissorResponse.getSchemaVersion());
    }

    @Quando("^eu realizo uma busca pelo endpoint de busca de Emissor por codigoBandeira e codigoEmissor$")
    public void eu_realizo_uma_busca_pelo_endpoint_de_busca_de_Emissor_por_codigo(){
        emissorEsperado = this.emissorFixture.getResultado().getBody();
        emissorResponse = emissorFixture.consultarEmissorCodigo(emissorEsperado.getCodigoBandeira(),
                emissorEsperado.getCodigoEmissor() ).getBody();
    }



}
