package br.com.elo.steps.fluxoTransacional;


import br.com.elo.dto.ErrorResponseDTO;
import br.com.elo.dto.FluxoTransacionalRequestDTO;
import br.com.elo.fixture.FluxoTransacionalFixture;
import cucumber.api.DataTable;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class FluxoTransacionalPostInvalidosSteps {

    @Autowired
    private FluxoTransacionalFixture fluxoTransacionalFixture;

    FluxoTransacionalRequestDTO fluxoTransacionalRequest;
    ErrorResponseDTO errorResponseDTO;

    @Dado("^que quero cadastrar um fluxo transacional com payload com \"([^\"]*)\"$")
    public void que_quero_cadastrar_um_fluxo_transacional_com_payload_com(String cenario, DataTable dataTable){

        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            fluxoTransacionalRequest = FluxoTransacionalRequestDTO.builder()
                    .descricao(columns.get("descricao"))
                    .build();

            if(cenario.equals("PERNA1INVALIDA"))
                fluxoTransacionalRequest.setMensagemSolicitacaoPernaUmSequence(0L);

            if(cenario.equals("PERNA2INVALIDA"))
                fluxoTransacionalRequest.setMensagemSolicitacaoPernaDoisSequence(0L);

            if(cenario.equals("PERNA3INVALIDA"))
                fluxoTransacionalRequest.setMensagemRespostaPernaTresSequence(0L);

            if(cenario.equals("PERNA4INVALIDA"))
                fluxoTransacionalRequest.setMensagemRespostaPernaQuatroSequence(0L);

            if(cenario.equals("BIT14INVALIDO"))
                fluxoTransacionalRequest.setBitVinculacaoMensagensPernasUmQuatro(11);

            if(cenario.equals("BIT23INVALIDO"))
                fluxoTransacionalRequest.setBitVinculacaoMensagensPernasDoisTres(11);


        }
    }

    @Dado("^que quero cadastrar um fluxo transacional com descricao invalida:$")
    public void que_quero_cadastrar_um_fluxo_transacional_com_descricao_invalida(DataTable dataTable){
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            fluxoTransacionalRequest = FluxoTransacionalRequestDTO.builder()
                    .descricao(columns.get("descricao"))
                    .build();
        }
    }


    @Quando("^realizo POST deste fluxo transacional com dados invalidos$")
    public void realizo_POST_deste_fluxo_transacional_com_dados_invalidos(){

        errorResponseDTO = fluxoTransacionalFixture.incluirFluxoTransacionalInvalido(fluxoTransacionalRequest).getBody();
    }

    @Entao("^o response exibe a mensagem \"([^\"]*)\"$")
    public void o_response_exibe_a_mensagem(String mensagemErro) {

        assertEquals(errorResponseDTO.errors.get(0),mensagemErro);
    }
}
