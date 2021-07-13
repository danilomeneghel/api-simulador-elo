package br.com.elo.steps.mensagens;


import br.com.elo.domain.Status;
import br.com.elo.domain.TipoMensagem;
import br.com.elo.dto.MensagemISO8583RequestDTO;
import br.com.elo.dto.MensagemISO8583ResponseDTO;
import br.com.elo.fixture.MensagemISO8583Fixture;
import br.com.elo.fixture.ProtocoloFixture;
import cucumber.api.DataTable;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class MensagemISO8583PostSteps{

	@Autowired
	private MensagemISO8583Fixture mensagemISO8583Fixture;

	@Autowired
	private ProtocoloFixture protocoloFixture;

	MensagemISO8583RequestDTO mensagemISO8583Request;
	MensagemISO8583ResponseDTO mensagemISO8583Response;

	@Dado("que possua uma mensagemISO8583 com payload:")
	public void que_possua_uma_mensagemISO8583_com_payload(DataTable dataTable) {

		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> columns : rows) {
			mensagemISO8583Request = MensagemISO8583RequestDTO.builder()
					                 .codigoMensagem(Integer.parseInt(columns.get("codigoMensagem")))
									 .descricao(columns.get("descricao"))
									 .tipoMensagem(TipoMensagem.valueOf(columns.get("tipoMensagem")))
									 .status(Status.ATIVO.valueOf(columns.get("status")))
									 .build();

			mensagemISO8583Request.setProtocoloSequence(protocoloFixture.getResultado().getBody().getProtocoloSequence());
		}
	}

	@Quando("realizo um POST desta mensagemISO8583")
	public void realizo_um_post_desta_mensagemISO8583() {

		mensagemISO8583Response = mensagemISO8583Fixture.incluirMensagemISO8583(mensagemISO8583Request).getBody();
	}

	@Então("o response apresenta todos os campos referente a mensagemISO8583 preenchidos corretamente")
	public void o_response_apresenta_todos_os_campos_referente_a_mensagemISO8583_preenchidos_corretamente() {

		assertNotNull(mensagemISO8583Response.getId());
		assertNotNull(mensagemISO8583Response.getMensagemSequence());
		assertNotNull(mensagemISO8583Response.getDataCriacao());
		assertEquals(mensagemISO8583Response.getCodigoMensagem(), mensagemISO8583Request.getCodigoMensagem());
		assertEquals(mensagemISO8583Response.getDescricao(), mensagemISO8583Request.getDescricao());
		assertEquals(mensagemISO8583Response.getTipoMensagem(), mensagemISO8583Request.getTipoMensagem());
		assertEquals(mensagemISO8583Response.getStatus(), mensagemISO8583Request.getStatus());

	}
		
}