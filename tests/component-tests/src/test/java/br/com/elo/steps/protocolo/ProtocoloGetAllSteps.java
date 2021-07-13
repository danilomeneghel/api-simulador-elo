package br.com.elo.steps.protocolo;

import br.com.elo.dto.ProtocoloRequestCriteriaDTO;
import br.com.elo.dto.ProtocoloResponseDTO;
import br.com.elo.fixture.ProtocoloFixture;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProtocoloGetAllSteps{

	@Autowired
	private ProtocoloFixture protocoloFixture;

	ProtocoloResponseDTO protocoloEsperado;
	List<ProtocoloResponseDTO> protocoloResponse;


	@Quando("realizo o getAll da API de protocolo")
	public void realizo_o_getAll_da_API_de_protocolo() {

		protocoloEsperado = this.protocoloFixture.getResultado().getBody();
		protocoloResponse = Arrays.asList(protocoloFixture.buscaProtocolo(ProtocoloRequestCriteriaDTO.builder().codigo(protocoloEsperado.getProtocoloSequence()).build()).getBody());
	}

	@Então("o response apresenta uma lista com um protocolo cadastrado")
	public void o_response_apresenta_uma_lista_com_o_protocolo_cadastrado() {

		assertNotNull(protocoloResponse);
		assertEquals(protocoloResponse.get(0).getProtocoloSequence(), protocoloEsperado.getProtocoloSequence());
		assertEquals(protocoloResponse.get(0).getDataCriacao(), protocoloEsperado.getDataCriacao());
		assertEquals(protocoloResponse.get(0).getTipo(), protocoloEsperado.getTipo());
		assertEquals(protocoloResponse.get(0).getEncodeBCDLLVARLLLVAR(), protocoloEsperado.getEncodeBCDLLVARLLLVAR());
		assertEquals(protocoloResponse.get(0).getEncodeCodigoMensagem(), protocoloEsperado.getEncodeCodigoMensagem());
		assertEquals(protocoloResponse.get(0).getEncodeMapaDeBits(), protocoloEsperado.getEncodeMapaDeBits());
		assertEquals(protocoloResponse.get(0).getStatus(), protocoloEsperado.getStatus());
		assertEquals(protocoloResponse.get(0).getVersao(), protocoloEsperado.getVersao());
		assertEquals(protocoloResponse.get(0).getDescricao(), protocoloEsperado.getDescricao());

	}
	


}
