package br.com.elo.steps.cartao;

import br.com.elo.domain.CvnCartao;
import br.com.elo.domain.TipoCartao;
import br.com.elo.domain.TokenCartao;
import br.com.elo.dto.cartao.CartaoRequestDTO;
import br.com.elo.dto.cartao.CartaoResponseDTO;
import br.com.elo.fixture.cartao.CartaoFixture;
import cucumber.api.DataTable;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CartaoPostSteps {

    @Autowired
    private CartaoFixture cartaoFixture;

    CartaoRequestDTO cartaoRequestDTO;
    CartaoResponseDTO cartaoResponseDTO;

    @Quando("^realizo POST de novo cartao para a Bandeira e Emissor com o payload:$")
    public void realizo_POST_de_novo_cartao_para_a_Bandeira_e_Emissor_com_o_payload(DataTable dataTable){
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            cartaoRequestDTO = CartaoRequestDTO
                    .builder()
                    .codigoBandeira(Integer.parseInt(columns.get("CODBANDEIRA")))
                    .codigoEmissor(Integer.parseInt(columns.get("CODEMISSOR")))
                    .tipoCartao(TipoCartao.valueOf(columns.get("TIPOCARTAO")))
                    .nomeCartao(columns.get("CARDNAME"))
                    .pan(columns.get("PAN"))
                    .pin(columns.get("PIN"))
                    .cardSequenceNumber(Integer.parseInt(columns.get("CARDSEQUENCENUMBER")))
                    .cve2(columns.get("CVE2"))
                    .dataExpiracao(columns.get("EXPIRYDATE"))
                    .tokenCartao(TokenCartao.valueOf(columns.get("TOKEN")))
                    .applicationIdentifier(columns.get("APPLICATIONIDENTIFIER"))
                    .cardAppInterchangeProfile(columns.get("CARDAPPINTERCHANGEPROFILE"))
                    .issuerDiscrData(columns.get("ISSUERDISCRDATA"))
                    .cardAppTransactionCounter(Integer.parseInt(columns.get("CARDAPPTRANSACTIONCOUNTER")))
                    .track1(columns.get("TRACK1"))
                    .track2(columns.get("TRACK2"))
                    .track2EquivalentData(columns.get("TRACK2EQUIVALENTDATA"))
                    .cvnCartao(CvnCartao.valueOf(columns.get("CVN")))
                    .cvr(columns.get("CVR"))
                    .dkiKdi(columns.get("DKIKDI"))
                    .build();
            cartaoResponseDTO = cartaoFixture.incluirCartao(cartaoRequestDTO).getBody();
        }

    }

    @Então("^o cartao salvo no banco de dados$")
    public void o_cartao_salvo_no_banco_de_dados(){
        assertNotNull(cartaoResponseDTO.getId());
        assertNotNull(cartaoResponseDTO.getCodigoBandeira());
        assertNotNull(cartaoResponseDTO.getCodigoEmissor());
        assertNotNull(cartaoResponseDTO.getPan());
        assertEquals(cartaoResponseDTO.getTipoCartao(), cartaoRequestDTO.getTipoCartao());
        assertEquals(cartaoResponseDTO.getNomeCartao(), cartaoRequestDTO.getNomeCartao());
        assertEquals(cartaoResponseDTO.getPin(), cartaoRequestDTO.getPin());
        assertEquals(cartaoResponseDTO.getCardSequenceNumber(), cartaoRequestDTO.getCardSequenceNumber());
        assertEquals(cartaoResponseDTO.getCve2(), cartaoRequestDTO.getCve2());
        assertEquals(cartaoResponseDTO.getDataExpiracao(), cartaoRequestDTO.getDataExpiracao());
        assertEquals(cartaoResponseDTO.getTokenCartao(), cartaoRequestDTO.getTokenCartao());
    }

}
