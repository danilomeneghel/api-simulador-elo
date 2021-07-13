package br.com.elo.steps.httpstatus;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import br.com.elo.dto.MensagemDTO;


import br.com.elo.dto.RespostaDTO;
import br.com.elo.rest.RestClientFixture;
import cucumber.api.java.pt.Entao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpStatusSteps {

    @Autowired
    private RestClientFixture restClientFixture;



    @Entao("^a requisição é um sucesso$")
    public void thenRequisicaoSucesso() {
        this.thenRequisicaoStatus(HttpStatus.OK.value());
    }

    @Entao("^a requisição é um sucesso com status (\\d+)$")
    public void thenRequisicaoSucesso(final int status) {
        this.thenRequisicaoStatus(status);
    }

    @Entao("^a requisição falha com erro (\\d+)$")
    public void thenRequisicaoFalha(final int status) {
        this.thenRequisicaoStatus(status);
    }

    @Entao("^a mensagem de erro \"([^\"]*)\" é retornada$")
    public void thenRequisicaoFalhaComErro(final String mensagem) {
        this.thenRequisicaoFalhaComErroNoCampo(mensagem, null);
    }

    @Entao("^a mensagem de erro \"([^\"]*)\" é retornada para o campo \"([^\"]*)\"$")
    public void thenRequisicaoFalhaComErroNoCampo(final String mensagem, final String campo) {
        ResponseEntity<RespostaDTO<?>> response = this.restClientFixture.getLastResponse();
        assertNotNull("Nenhuma requisição foi feita.", response);
        assertEquals("Foram retornados mais erros do que era esperado:" + response.getBody().getMensagens(), 1,
                response.getBody().getMensagens().size());
        MensagemDTO mensagemDTO = response.getBody().getMensagens().get(0);
        assertEquals(campo, mensagemDTO.getCampo());
        assertEquals(mensagem, mensagemDTO.getMensagem());
    }

    @Entao("^o código de retorno é \"([^\"]*)\"$")
    public void thenCodigoRetorno(final String codigo) {
        ResponseEntity<RespostaDTO<?>> response = this.restClientFixture.getLastResponse();
        assertEquals(codigo, response.getBody().getCodigo());
    }

    private void thenRequisicaoStatus(final int status) {
        ResponseEntity<RespostaDTO<?>> response = this.restClientFixture.getLastResponse();
        assertNotNull("Nenhuma requisição foi feita.", response);
        assertEquals(status, response.getStatusCodeValue());
    }

    private List<MensagemDTO>  getMensagens(final ResponseEntity<RespostaDTO<?>> response) {
        return response.getBody() == null ? null : response.getBody().getMensagens();
    }

    @Entao("^a falha está no campo \"([^\"]*)\"$")
    public void thenCampoFalha(final String campo) {
        ResponseEntity<RespostaDTO<?>> response = this.restClientFixture.getLastResponse();
        List<MensagemDTO> mensagens = response.getBody().getMensagens();
        assertEquals(1, mensagens.size());
        assertEquals(campo, mensagens.get(0).getCampo());
    }

    @Entao("^a mensagem retornada é \"([^\"]*)\"$")
    public void thenMensagem(final String mensagem) {
        ResponseEntity<RespostaDTO<?>> response = this.restClientFixture.getLastResponse();
        List<MensagemDTO> mensagens = response.getBody().getMensagens();
        assertEquals(1, mensagens.size());
        assertEquals(mensagem, mensagens.get(0).getMensagem());
    }

}
