package br.com.elo.steps.protocolo;


import br.com.elo.dto.ProtocoloResponseDTO;
import br.com.elo.fixture.ProtocoloFixture;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProtocoloGetIdCodigoSteps{



	@Autowired
	private ProtocoloFixture protocoloFixture;

	ProtocoloResponseDTO protocoloEsperado;
	ProtocoloResponseDTO protocoloResponse;


	@Quando("eu realizo uma busca de protocolo pelo endpoint de busca por id")
	public void eu_realizo_uma_busca_de_protocolo_pelo_endpoint_de_busca_por_id() {

		protocoloEsperado = this.protocoloFixture.getResultado().getBody();
		protocoloResponse = protocoloFixture.consultaProtocoloId(protocoloEsperado.getId()).getBody();
	}

	@Quando("eu realizo uma busca de protocolo pelo endpoint de busca por código")
	public void eu_realizo_uma_busca_de_protocolo_pelo_endpoint_de_busca_por_codigo() {

		protocoloEsperado = this.protocoloFixture.getResultado().getBody();
		protocoloResponse = protocoloFixture.consultaProtocoloCodigo(protocoloEsperado.getProtocoloSequence()).getBody();
	}

	@Então("o response apresenta os campos do Protocolo de acordo com a base de dados")
	public void o_response_apresenta_os_campos_da_Protocolo_de_acordo_com_a_base_de_dados() {

		assertNotNull(protocoloResponse);
		assertEquals(protocoloResponse.getProtocoloSequence(), protocoloEsperado.getProtocoloSequence());
		assertEquals(protocoloResponse.getDataCriacao(), protocoloEsperado.getDataCriacao());
		assertEquals(protocoloResponse.getTipo(), protocoloEsperado.getTipo());
		assertEquals(protocoloResponse.getEncodeBCDLLVARLLLVAR(), protocoloEsperado.getEncodeBCDLLVARLLLVAR());
		assertEquals(protocoloResponse.getEncodeCodigoMensagem(), protocoloEsperado.getEncodeCodigoMensagem());
		assertEquals(protocoloResponse.getEncodeMapaDeBits(), protocoloEsperado.getEncodeMapaDeBits());
		assertEquals(protocoloResponse.getStatus(), protocoloEsperado.getStatus());
		assertEquals(protocoloResponse.getVersao(), protocoloEsperado.getVersao());
		assertEquals(protocoloResponse.getDescricao(), protocoloEsperado.getDescricao());

	}
	
}
