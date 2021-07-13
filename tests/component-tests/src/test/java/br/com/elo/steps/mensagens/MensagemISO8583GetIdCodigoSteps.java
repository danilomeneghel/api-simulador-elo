package br.com.elo.steps.mensagens;


import br.com.elo.dto.MensagemISO8583ResponseDTO;
import br.com.elo.fixture.MensagemISO8583Fixture;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MensagemISO8583GetIdCodigoSteps{

	@Autowired
	private MensagemISO8583Fixture mensagemISO8583Fixture;

	MensagemISO8583ResponseDTO mensagemISO8583Esperado;
	MensagemISO8583ResponseDTO mensagemISO8583Response;


	@Quando("eu realizo uma busca de mensagemISO8583 pelo endpoint de busca por id")
	public void eu_realizo_uma_busca_de_mensagemISO8583_pelo_endpoint_de_busca_por_id() {

		mensagemISO8583Esperado = this.mensagemISO8583Fixture.getResultado().getBody();
		mensagemISO8583Response = mensagemISO8583Fixture.consultaMensagemISO8583Id(mensagemISO8583Esperado.getId()).getBody();
	}

	@Quando("eu realizo uma busca de mensagemISO8583 pelo endpoint de busca por código")
	public void eu_realizo_uma_busca_de_mensagemISO8583_pelo_endpoint_de_busca_por_codigo() {

		mensagemISO8583Esperado = this.mensagemISO8583Fixture.getResultado().getBody();
		mensagemISO8583Response = mensagemISO8583Fixture.consultaMensagemISO8583Codigo(mensagemISO8583Esperado.getMensagemSequence()).getBody();
	}

	@Então("o response apresenta os campos do MensagemISO8583 de acordo com a base de dados")
	public void o_response_apresenta_os_campos_do_MensagemISO8583_de_acordo_com_a_base_de_dados() {

		assertNotNull(mensagemISO8583Response);
		assertEquals(mensagemISO8583Response.getMensagemSequence(), mensagemISO8583Esperado.getMensagemSequence());
		assertEquals(mensagemISO8583Response.getDataCriacao(), mensagemISO8583Esperado.getDataCriacao());
		assertEquals(mensagemISO8583Response.getCodigoMensagem(), mensagemISO8583Esperado.getCodigoMensagem());
		assertEquals(mensagemISO8583Response.getDescricao(), mensagemISO8583Esperado.getDescricao());

	}
	
}
