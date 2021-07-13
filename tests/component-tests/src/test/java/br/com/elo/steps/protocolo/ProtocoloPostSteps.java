package br.com.elo.steps.protocolo;

import br.com.elo.domain.*;
import br.com.elo.dto.ProtocoloRequestDTO;
import br.com.elo.dto.ProtocoloResponseDTO;
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


public class ProtocoloPostSteps{

	@Autowired
	private ProtocoloFixture protocoloFixture;

	ProtocoloRequestDTO protocoloRequest;
	ProtocoloResponseDTO protocoloResponse;

	@Dado("que possua um protocolo com payload:")
	public void que_possua_um_protocolo_com_payload(DataTable dataTable) {

		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> columns : rows) {
			protocoloRequest = ProtocoloRequestDTO.builder()
					          .tipo(TipoProtocolo.valueOf(columns.get("tipo")))
					          .descricao(columns.get("descricao"))
					          .encodeCodigoMensagem(EncodeCodigoMensagem.valueOf(columns.get("encodeCodigoMensagem")))
					          .encodeMapaDeBits(EncodeMapaDeBits.valueOf(columns.get("encodeMapaDeBits")))
					          .encodeBCDLLVARLLLVAR(EncodeBCDLLVARLLLVAR.valueOf(columns.get("encodeBCDLLVARLLLVAR")))
					          .status(Status.valueOf(columns.get("status")))
							  .versao(columns.get("versao"))
					          .build();
		}
	}

	@Quando("realizo um POST deste protocolo")
	public void realizo_um_post_deste_protocolo() {

		protocoloResponse = protocoloFixture.incluirProtocolo(protocoloRequest).getBody();
	}

	@Então("o response apresenta todos os campos referente o protocolo preenchidos corretamente")
	public void o_response_apresenta_todos_os_campos_referente_o_protocolo_preenchidos_corretamente() {

		assertNotNull(protocoloResponse.getId());
		assertNotNull(protocoloResponse.getProtocoloSequence());
		assertNotNull(protocoloResponse.getDataCriacao());
		assertEquals(protocoloResponse.getDescricao(), protocoloRequest.getDescricao());
		assertEquals(protocoloResponse.getTipo(), protocoloRequest.getTipo());
		assertEquals(protocoloResponse.getVersao(), protocoloRequest.getVersao());
		assertEquals(protocoloResponse.getEncodeCodigoMensagem(), protocoloRequest.getEncodeCodigoMensagem());
		assertEquals(protocoloResponse.getEncodeBCDLLVARLLLVAR(), protocoloRequest.getEncodeBCDLLVARLLLVAR());
		assertEquals(protocoloResponse.getEncodeMapaDeBits(), protocoloRequest.getEncodeMapaDeBits());
		assertEquals(protocoloResponse.getStatus(), protocoloRequest.getStatus());

	}
}