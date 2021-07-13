package br.com.elo.steps.cartao;

import br.com.elo.dto.cartao.CartaoRequestCriteriaDTO;
import br.com.elo.dto.cartao.CartaoResponseDTO;
import br.com.elo.fixture.cartao.CartaoFixture;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CartaoGetAllSteps {

    @Autowired
    private CartaoFixture cartaoFixture;

    CartaoResponseDTO cartaoEsperado;
    List<CartaoResponseDTO> cartoesRetornados;

    @Quando("^realizo o getAll da API de cartao$")
    public void realizo_o_getall_da_api_de_cartao() {
        cartaoEsperado = cartaoFixture.getResultado().getBody();
        cartoesRetornados = Arrays.asList(cartaoFixture.buscaCartao(CartaoRequestCriteriaDTO.builder().pan(cartaoEsperado.getPan()).build()).getBody());
    }

    @E("^o response apresenta uma lista com o cartao cadastrado$")
    public void oResponseApresentaUmaListaComOCartaoCadastrado() {
        assertNotNull(cartoesRetornados);
        assertNotNull(cartoesRetornados.get(0).getPan());
        assertEquals(cartoesRetornados.get(0).getPan(), cartaoEsperado.getPan());
        assertEquals(cartoesRetornados.get(0).getCodigoEmissor(), cartaoEsperado.getCodigoEmissor());
        assertEquals(cartoesRetornados.get(0).getCodigoBandeira(), cartaoEsperado.getCodigoBandeira());


    }
}
