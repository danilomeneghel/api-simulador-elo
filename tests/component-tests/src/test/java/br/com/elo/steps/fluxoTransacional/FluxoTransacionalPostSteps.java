package br.com.elo.steps.fluxoTransacional;



import br.com.elo.dto.FluxoTransacionalRequestDTO;
import br.com.elo.dto.FluxoTransacionalResponseDTO;
import br.com.elo.dto.MensagemISO8583RequestDTO;
import br.com.elo.dto.MensagemISO8583ResponseDTO;
import br.com.elo.fixture.FluxoTransacionalFixture;
import br.com.elo.fixture.MensagemISO8583Fixture;
import br.com.elo.fixture.ProtocoloFixture;
import cucumber.api.DataTable;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class FluxoTransacionalPostSteps {

    @Autowired
    private FluxoTransacionalFixture fluxoTransacionalFixture;

    @Autowired
    private MensagemISO8583Fixture mensagemISO8583Fixture;

    @Autowired
    private ProtocoloFixture protocoloFixture;

    FluxoTransacionalRequestDTO fluxoTransacionalRequest;
    FluxoTransacionalResponseDTO fluxoTransacionalResponse;
    List<MensagemISO8583ResponseDTO> listaMensagensISO8583Cadastradas = new ArrayList<>();

    @Dado("que possua uma lista de mensagensISO8583 cadastradas:")
    public void que_possua_uma_lista_mensagemISO8583_cadastrada(List<MensagemISO8583RequestDTO> mensagemISO8583RequestDTOList) {


        Long protocoloCodigo = protocoloFixture.getResultado().getBody().getProtocoloSequence();

        for (MensagemISO8583RequestDTO mensagemISO8583RequestDTO : mensagemISO8583RequestDTOList) {

            mensagemISO8583RequestDTO.setProtocoloSequence(protocoloCodigo);
            listaMensagensISO8583Cadastradas.add(mensagemISO8583Fixture.incluirMensagemISO8583(mensagemISO8583RequestDTO).getBody());
        }
    }


    @Dado("^que possua um fluxo de transação com o payload:$")
    public void que_possua_um_fluxotransacional_com_payload(DataTable dataTable) {

        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            fluxoTransacionalRequest = FluxoTransacionalRequestDTO.builder()
                    .descricao(columns.get("descricao"))
                    .build();

               if(fluxoTransacionalRequest.getDescricao().equals("FLUXO1E4")) {
                    fluxoTransacionalRequest.setMensagemSolicitacaoPernaUmSequence(listaMensagensISO8583Cadastradas.get(0).getMensagemSequence());
                    fluxoTransacionalRequest.setMensagemRespostaPernaQuatroSequence(listaMensagensISO8583Cadastradas.get(1).getMensagemSequence());
               }

               if(fluxoTransacionalRequest.getDescricao().equals("FLUXO2E3")) {
                    fluxoTransacionalRequest.setMensagemSolicitacaoPernaDoisSequence(listaMensagensISO8583Cadastradas.get(0).getMensagemSequence());
                    fluxoTransacionalRequest.setMensagemRespostaPernaTresSequence(listaMensagensISO8583Cadastradas.get(1).getMensagemSequence());
               }

        }

        fluxoTransacionalFixture.incluirFluxoTransacional(fluxoTransacionalRequest);
    }

    @Quando("^realizo POST deste fluxo de transaçao")
    public void realizo_um_post_desta_bandeira() {

        fluxoTransacionalResponse = this.fluxoTransacionalFixture.getResultado().getBody();
    }

    @Entao("^o response apresenta todos os campos referente ao fluxo de transação preenchidos corretamente$")
    public void o_response_apresenta_todos_os_campos_referente_ao_fluxo_de_transação_preenchidos_corretamente(){

        assertNotNull(fluxoTransacionalResponse);
        assertNotNull(fluxoTransacionalResponse.getId());
        assertNotNull(fluxoTransacionalResponse.getFluxoTransacionalSequence());
        assertNotNull(fluxoTransacionalResponse.getDataCriacao());
        assertEquals(fluxoTransacionalResponse.getDescricao(),fluxoTransacionalRequest.getDescricao());
        assertEquals(fluxoTransacionalResponse.getMensagemSolicitacaoPernaUmSequence(), fluxoTransacionalRequest.getMensagemSolicitacaoPernaUmSequence());
        assertEquals(fluxoTransacionalResponse.getMensagemRespostaPernaQuatroSequence(), fluxoTransacionalRequest.getMensagemRespostaPernaQuatroSequence());
        assertEquals(fluxoTransacionalResponse.getMensagemSolicitacaoPernaDoisSequence(), fluxoTransacionalRequest.getMensagemSolicitacaoPernaDoisSequence());
        assertEquals(fluxoTransacionalResponse.getMensagemRespostaPernaTresSequence(), fluxoTransacionalRequest.getMensagemRespostaPernaTresSequence());

    }

}
