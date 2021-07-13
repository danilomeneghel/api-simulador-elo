package br.com.elo.steps.mensagens;


import br.com.elo.dto.MensagemISO8583RequestCriteriaDTO;
import br.com.elo.dto.MensagemISO8583ResponseDTO;
import br.com.elo.fixture.MensagemISO8583Fixture;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MensagemISO8583GetAllSteps{

	@Autowired
	private MensagemISO8583Fixture mensagemISO8583Fixture;


	MensagemISO8583ResponseDTO mensagemISO8583Esperada;
	List<MensagemISO8583ResponseDTO> mensagemISO8583Response;


	@Quando("realizo o getAll da API de mensagemISO8583")
	public void realizo_o_getAll_da_API_de_mensagemISO8583() {

		mensagemISO8583Esperada = this.mensagemISO8583Fixture.getResultado().getBody();
		mensagemISO8583Response = Arrays.asList(mensagemISO8583Fixture.buscaMensagemISO8583(MensagemISO8583RequestCriteriaDTO.builder().codigo(mensagemISO8583Esperada.getMensagemSequence()).build()).getBody());
	}

	@Então("o response apresenta uma lista com uma mensagemISO8583 cadastrada")
	public void o_response_apresenta_uma_lista_com_uma_mensagemISO8583_cadastrada() {

		assertNotNull(mensagemISO8583Response);
		assertEquals(mensagemISO8583Response.get(0).getId(), mensagemISO8583Esperada.getId());
		assertEquals(mensagemISO8583Response.get(0).getMensagemSequence(), mensagemISO8583Esperada.getMensagemSequence());
		assertEquals(mensagemISO8583Response.get(0).getDataCriacao(), mensagemISO8583Esperada.getDataCriacao());
		assertEquals(mensagemISO8583Response.get(0).getCodigoMensagem(), mensagemISO8583Esperada.getCodigoMensagem());
		assertEquals(mensagemISO8583Response.get(0).getDescricao(), mensagemISO8583Esperada.getDescricao());
		assertEquals(mensagemISO8583Response.get(0).getProtocoloSequence(), mensagemISO8583Esperada.getProtocoloSequence());
		assertEquals(mensagemISO8583Response.get(0).getTipoMensagem(), mensagemISO8583Esperada.getTipoMensagem());

	}
	


}
