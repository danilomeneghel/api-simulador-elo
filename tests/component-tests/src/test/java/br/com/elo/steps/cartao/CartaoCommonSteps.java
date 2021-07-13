package br.com.elo.steps.cartao;

import br.com.elo.domain.CvnCartao;
import br.com.elo.domain.Plataforma;
import br.com.elo.domain.TipoCartao;
import br.com.elo.domain.TokenCartao;
import br.com.elo.dto.BandeiraRequestDTO;
import br.com.elo.dto.BandeiraResponseDTO;
import br.com.elo.dto.cartao.CartaoRequestDTO;
import br.com.elo.dto.cartao.CartaoResponseDTO;
import br.com.elo.dto.emissor.EmissorRequestDTO;
import br.com.elo.dto.emissor.EmissorResponseDTO;
import br.com.elo.fixture.BandeiraFixture;
import br.com.elo.fixture.cartao.CartaoFixture;
import br.com.elo.fixture.emissor.EmissorFixture;
import cucumber.api.java.pt.Dado;
import org.springframework.beans.factory.annotation.Autowired;

public class CartaoCommonSteps {

    @Autowired
    private EmissorFixture emissorFixture;

    @Autowired
    private BandeiraFixture bandeiraFixture;

    @Autowired
    private CartaoFixture cartaoFixture;

    BandeiraRequestDTO bandeiraRequest;
    BandeiraResponseDTO bandeiraResponse;

    EmissorRequestDTO emissorRequest;
    EmissorResponseDTO emissorResponse;

    CartaoRequestDTO cartaoRequest;
    CartaoResponseDTO cartaoResponse;

    @Dado("que possua uma bandeira cadastrada para o emissor")
    public void que_possua_uma_bandeira_cadastrada_para_o_emissor(){
        bandeiraRequest = BandeiraRequestDTO.builder().codigoBandeira(1).descricao("ELO").build();
        bandeiraResponse = bandeiraFixture.incluirBandeira(bandeiraRequest).getBody();
    }

    @Dado("^que possua um Emissor cadastrado no sistema$")
    public void que_eu_possua_um_Emissor_cadastrado() {
        EmissorRequestDTO emissorRequestDTO = EmissorRequestDTO
                .builder()
                .codigoEmissor(1)
                .nomeEmissor("Emissor CT 1")
                .codigoProcessadora(2222)
                .plataforma(Plataforma.CREDITO)
                .codigoBandeira(1)
                .build();
        emissorFixture.incluirEmissor(emissorRequestDTO);

    }

    @Dado("^que possua um cartao cadastrado$")
    public void quePossuaUmCartaoCadastrado() {
        // Incluindo bandeira
        bandeiraRequest = BandeiraRequestDTO.builder()
                .codigoBandeira(1)
                .descricao("ELO").build();
        bandeiraResponse = bandeiraFixture.incluirBandeira(bandeiraRequest).getBody();

        //Incluindo Emissor
        emissorRequest = EmissorRequestDTO.builder()
                .codigoBandeira(1)
                .codigoEmissor(1)
                .codigoProcessadora(1)
                .nomeEmissor("Emissor Teste")
                .plataforma(Plataforma.CREDITO).build();
        emissorResponse = emissorFixture.incluirEmissor(emissorRequest).getBody();

        // Incluindo cartão para emissor e bandeira previamente cadastrados
        cartaoRequest = CartaoRequestDTO.builder()
                .codigoEmissor(1)
                .codigoBandeira(1)
                .pan("12345678")
                .tipoCartao(TipoCartao.CREDITO)
                .nomeCartao("Cartão Teste")
                .pin("1234")
                .cardSequenceNumber(123)
                .cve2("1234")
                .dataExpiracao("30/11/2025")
                .tokenCartao(TokenCartao.SIM)
                .cvnCartao(CvnCartao.CVN05)
                .applicationIdentifier("1234")
                .issuerDiscrData("1234")
                .cardAppInterchangeProfile("1")
                .cardAppTransactionCounter(1)
                .cvr("1")
                .dkiKdi("1")
                .cvr("1")
                .track1("1")
                .track2("1")
                .track2EquivalentData("2").build();
        cartaoResponse = cartaoFixture.incluirCartao(cartaoRequest).getBody();
    }
}
