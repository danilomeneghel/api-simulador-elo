package br.com.elo.steps.mensagens;


import br.com.elo.dto.MensagemISO8583RequestDTO;
import br.com.elo.dto.MensagemISO8583ResponseDTO;
import br.com.elo.fixture.MensagemISO8583Fixture;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MensagemISO8583PutSteps {

	@Autowired
	private MensagemISO8583Fixture mensagemISO8583Fixture;


	MensagemISO8583ResponseDTO mensagemISO8583Cadastrado;
	MensagemISO8583RequestDTO mensagemISO8583Alterada;
	MensagemISO8583ResponseDTO mensagemISO8583Response;


	@Quando("realizo um put com o codigoMensagem alterado")
	public void realizo_um_put_com_o_codigoMensagem_alterado() {

		mensagemISO8583Cadastrado = this.mensagemISO8583Fixture.getResultado().getBody();
		mensagemISO8583Alterada = MensagemISO8583RequestDTO.builder()
				                  .codigoMensagem(204)
				                  .tipoMensagem(mensagemISO8583Cadastrado.getTipoMensagem())
				                  .status(mensagemISO8583Cadastrado.getStatus())
				                  .descricao(mensagemISO8583Cadastrado.getDescricao())
				                  .protocoloSequence(mensagemISO8583Cadastrado.getProtocoloSequence())
				                  .build();

		mensagemISO8583Response = mensagemISO8583Fixture.atualizaMensagemISO8583(mensagemISO8583Alterada, mensagemISO8583Cadastrado.getMensagemSequence()).getBody();
	}

	@Quando("realizo um put com a descricao da mensagemISO8583 alterada")
	public void realizo_um_put_com_a_descricao_da_mensagemISO8583_alterada() {

		mensagemISO8583Cadastrado = this.mensagemISO8583Fixture.getResultado().getBody();
		mensagemISO8583Alterada = MensagemISO8583RequestDTO.builder()
				.codigoMensagem(mensagemISO8583Cadastrado.getCodigoMensagem())
				.tipoMensagem(mensagemISO8583Cadastrado.getTipoMensagem())
				.status(mensagemISO8583Cadastrado.getStatus())
				.descricao("Descricao mensagem alterada")
				.protocoloSequence(mensagemISO8583Cadastrado.getProtocoloSequence())
				.build();
		mensagemISO8583Response = mensagemISO8583Fixture.atualizaMensagemISO8583(mensagemISO8583Alterada, mensagemISO8583Cadastrado.getMensagemSequence()).getBody();
	}

	@Quando("realizo um put com o codigoMensagem e descricao alterados")
	public void realizo_um_put_com_o_codigoMensagem_e_descricao_alterados() {

		mensagemISO8583Cadastrado = this.mensagemISO8583Fixture.getResultado().getBody();
		mensagemISO8583Alterada = MensagemISO8583RequestDTO.builder()
				.codigoMensagem(204)
				.tipoMensagem(mensagemISO8583Cadastrado.getTipoMensagem())
				.status(mensagemISO8583Cadastrado.getStatus())
				.descricao("Descricao mensagem alterada")
				.protocoloSequence(mensagemISO8583Cadastrado.getProtocoloSequence())
				.build();
		mensagemISO8583Response = mensagemISO8583Fixture.atualizaMensagemISO8583(mensagemISO8583Alterada, mensagemISO8583Cadastrado.getMensagemSequence()).getBody();
	}

	@Então("o response da alteração de mensagemISO8583 exibe os dados alterados corretamente")
	public void o_response_da_alteração_de_mensagemISO8583_exibe_os_dados_alterados_corretamente() {

		assertNotNull(mensagemISO8583Response.getId());
		assertNotNull(mensagemISO8583Response.getMensagemSequence());
		assertNotNull(mensagemISO8583Response.getDataCriacao());
		assertEquals(mensagemISO8583Response.getCodigoMensagem(), mensagemISO8583Alterada.getCodigoMensagem());
		assertEquals(mensagemISO8583Response.getDescricao(), mensagemISO8583Alterada.getDescricao());
		assertEquals(mensagemISO8583Response.getTipoMensagem(), mensagemISO8583Alterada.getTipoMensagem());
		assertEquals(mensagemISO8583Response.getStatus(), mensagemISO8583Alterada.getStatus());
	}



	
}
