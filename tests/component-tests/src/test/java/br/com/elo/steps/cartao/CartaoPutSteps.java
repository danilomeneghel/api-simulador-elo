package br.com.elo.steps.cartao;

import br.com.elo.dto.cartao.CartaoRequestDTO;
import br.com.elo.dto.cartao.CartaoResponseDTO;
import br.com.elo.fixture.cartao.CartaoFixture;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class CartaoPutSteps {

    @Autowired
    private CartaoFixture cartaoFixture;

    CartaoResponseDTO cartaoCadastrado;
    CartaoRequestDTO cartaoAlterado;
    CartaoResponseDTO cartaoResponseDTO;

    @Quando("^realizo uma alteração do nome do Cartao$")
    public void realizoUmaAlteraçãoDoNomeDoCartao() {
        cartaoCadastrado = cartaoFixture.getResultado().getBody();
        cartaoAlterado = CartaoRequestDTO.builder()
                .issuerDiscrData(cartaoCadastrado.getIssuerDiscrData())
                .cvnCartao(cartaoCadastrado.getCvnCartao())
                .tokenCartao(cartaoCadastrado.getTokenCartao())
                .nomeCartao("Nome Cartao Alterado")
                .applicationIdentifier(cartaoCadastrado.getApplicationIdentifier())
                .dataExpiracao(cartaoCadastrado.getDataExpiracao())
                .cve2(cartaoCadastrado.getCve2())
                .tipoCartao(cartaoCadastrado.getTipoCartao())
                .cardSequenceNumber(cartaoCadastrado.getCardSequenceNumber())
                .pin(cartaoCadastrado.getPin())
                .pan(cartaoCadastrado.getPan())
                .codigoBandeira(cartaoCadastrado.getCodigoBandeira())
                .codigoEmissor(cartaoCadastrado.getCodigoEmissor()).build();
        cartaoResponseDTO = cartaoFixture.atualizarCartao(cartaoAlterado, cartaoCadastrado.getPan()).getBody();
    }

    @Então("^o response da alteração de Cartao exibe a data de atualização corretamente$")
    public void o_response_da_alteração_de_Cartao_exibe_a_data_de_atualização_corretamente() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dataAlteracao = cartaoResponseDTO.getDataAlteracao();
        String dataAlteracaoString = dataAlteracao.format(formatter);

        LocalDateTime dataEsperada = LocalDateTime.now();
        String dataEsperadaString = dataEsperada.format(formatter);

        assertEquals(dataAlteracaoString, dataEsperadaString);

    }
}
