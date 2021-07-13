package br.com.elo.steps.emissor;

import br.com.elo.dto.emissor.EmissorRequestCriteriaDTO;
import br.com.elo.dto.emissor.EmissorResponseDTO;
import br.com.elo.fixture.emissor.EmissorFixture;
import cucumber.api.PendingException;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EmissorGetAllSteps {

    EmissorResponseDTO emissorEsperado;
    List<EmissorResponseDTO> emissorResponse;

    @Autowired
    private EmissorFixture emissorFixture;


   /* @Quando("^eu realizo uma busca de Emissor por parametro \"([^\"]*)\"$")
    public void eu_realizo_uma_busca_de_Emissor_por_parametro(String arg1) throws Exception {

        emissorEsperado = this.emissorFixture.getResultado().getBody();
        emissorResponse = Arrays.asList(emissorFixture.buscaEmissor(EmissorRequestCriteriaDTO
                .builder()
                .codigo(emissorEsperado.getCodigo())
                .id(emissorEsperado.getId())
                .codigoEmissor(emissorEsperado.getCodigoEmissor())
                .codigoProcessadora(emissorEsperado.getCodigoProcessadora())
                .build()).getBody());

    }*/

    @Quando("^eu realizo uma busca de Emissor por parametro \"([^\"]*)\" com o valor \"([^\"]*)\"$")
    public void eu_realizo_uma_busca_de_Emissor_por_parametro_com_o_valor(String parametro, String valor){
        emissorEsperado = this.emissorFixture.getResultado().getBody();
        emissorResponse = Arrays.asList(emissorFixture.buscaEmissor(EmissorRequestCriteriaDTO
                .builder()
                .id(emissorEsperado.getId())
                .codigoEmissor(Integer.parseInt(valor))
                .codigoProcessadora(emissorEsperado.getCodigoProcessadora())
                .codigoBandeira(emissorEsperado.getCodigoBandeira())
                .build()).getBody());
    }

    @Então("^o response apresenta todos os simuladores com valores corretos sendo campo \"([^\"]*)\" igual a \"([^\"]*)\"$")
    public void o_response_apresenta_todos_os_simuladores_com_valores_corretos_sendo_campo_igual_a(String parametro, String valor){
        emissorEsperado = this.emissorFixture.getResultado().getBody();
        emissorResponse = Arrays.asList(emissorFixture.buscaEmissor(EmissorRequestCriteriaDTO
                .builder()
                .id(emissorEsperado.getId())
                .codigoEmissor(Integer.parseInt(valor))
                .codigoProcessadora(emissorEsperado.getCodigoProcessadora())
                .codigoBandeira(emissorEsperado.getCodigoBandeira())
                .build()).getBody());
    }

    @Então("^o response apresenta o Emissor pesquisado com os campos corretos$")
    public void o_response_apresenta_o_Emissor_pesquisado_com_os_campos_corretos() throws Exception {
        assertEquals(emissorEsperado.getCodigoEmissor(), emissorResponse.get(0).getCodigoEmissor());
        assertEquals(emissorEsperado.getCodigoProcessadora(), emissorResponse.get(0).getCodigoProcessadora());

    }


}
