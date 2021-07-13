package br.com.elo.steps.cartao;

import br.com.elo.dto.cartao.CartaoResponseDTO;
import br.com.elo.fixture.cartao.CartaoFixture;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CartaoGetIdPanSteps {

    @Autowired
    private CartaoFixture cartaoFixture;

    CartaoResponseDTO cartaoEsperado;
    CartaoResponseDTO cartaoResponse;

    @Quando("^eu realizo uma busca pelo endpoint de busca de Cartao por id$")
    public void euRealizoUmaBuscaPeloEndpointDeBuscaDeCartaoPorId() {
        cartaoEsperado = cartaoFixture.getResultado().getBody();
        cartaoResponse = cartaoFixture.consultarCartaoId(cartaoEsperado.getId()).getBody();
    }

    @E("^o response apresenta os campos do Cartao corretamente$")
    public void oResponseApresentaOsCamposDoCartaoCorretamente() {
        assertNotNull(cartaoResponse);
        assertNotNull(cartaoResponse.getPan());
        assertEquals(cartaoResponse.getPan(), cartaoEsperado.getPan());
        assertEquals(cartaoResponse.getCodigoEmissor(), cartaoEsperado.getCodigoEmissor());
        assertEquals(cartaoResponse.getCodigoBandeira(), cartaoEsperado.getCodigoBandeira());

    }

    @Quando("^eu realizo uma busca pelo endpoint de busca de Cartao por pan$")
    public void euRealizoUmaBuscaPeloEndpointDeBuscaDeCartaoPorPan() {
        cartaoEsperado = cartaoFixture.getResultado().getBody();
        cartaoResponse = cartaoFixture.consultarCartaoPan(cartaoEsperado.getPan()).getBody();

    }
}
