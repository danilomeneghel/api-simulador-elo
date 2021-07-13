package br.com.elo.steps.protocolo;

import br.com.elo.domain.Tipo;
import br.com.elo.domain.TipoProtocolo;
import br.com.elo.dto.ProtocoloRequestDTO;
import br.com.elo.dto.ProtocoloResponseDTO;
import br.com.elo.fixture.ProtocoloFixture;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProtocoloPutSteps{

	@Autowired
	private ProtocoloFixture protocoloFixture;

	ProtocoloResponseDTO protocoloCadastrado;
	ProtocoloRequestDTO protocoloAlterado;
	ProtocoloResponseDTO protocoloResponse;


	@Quando("realizo um put com o tipo protocolo alterado")
	public void realizo_um_put_com_o_tipo_protocolo_alterado() {

		protocoloCadastrado = this.protocoloFixture.getResultado().getBody();
		protocoloAlterado = ProtocoloRequestDTO.builder()
							.tipo(TipoProtocolo.EMISSOR)
							.descricao(protocoloCadastrado.getDescricao())
							.versao(protocoloCadastrado.getVersao())
							.status(protocoloCadastrado.getStatus())
							.encodeBCDLLVARLLLVAR(protocoloCadastrado.getEncodeBCDLLVARLLLVAR())
							.encodeMapaDeBits(protocoloCadastrado.getEncodeMapaDeBits())
							.encodeCodigoMensagem(protocoloCadastrado.getEncodeCodigoMensagem())
							.build();

		protocoloResponse = protocoloFixture.atualizaProtocolo(protocoloAlterado, protocoloCadastrado.getProtocoloSequence()).getBody();
	}

	@Quando("realizo um put com a descricao do protocolo alterada")
	public void realizo_um_put_com_a_descricao_do_protocolo_alterada() {

		protocoloCadastrado = this.protocoloFixture.getResultado().getBody();
		protocoloAlterado = ProtocoloRequestDTO.builder().tipo(protocoloCadastrado.getTipo())
							.descricao("descricao alterada teste")
							.versao(protocoloCadastrado.getVersao())
							.status(protocoloCadastrado.getStatus())
							.encodeBCDLLVARLLLVAR(protocoloCadastrado.getEncodeBCDLLVARLLLVAR())
							.encodeMapaDeBits(protocoloCadastrado.getEncodeMapaDeBits())
							.encodeCodigoMensagem(protocoloCadastrado.getEncodeCodigoMensagem())
							.build();
		protocoloResponse = protocoloFixture.atualizaProtocolo(protocoloAlterado, protocoloCadastrado.getProtocoloSequence()).getBody();
	}

	@Quando("realizo um put com a descricao e o tipo protocolo alterados")
	public void realizo_um_put_com_a_descricao_e_o_tipo_protocolo_alterados() {

		protocoloCadastrado = this.protocoloFixture.getResultado().getBody();
		protocoloAlterado = ProtocoloRequestDTO.builder()
							.tipo(TipoProtocolo.EMISSOR)
							.descricao("descricao alterada teste")
							.versao(protocoloCadastrado.getVersao())
							.status(protocoloCadastrado.getStatus())
							.encodeBCDLLVARLLLVAR(protocoloCadastrado.getEncodeBCDLLVARLLLVAR())
							.encodeMapaDeBits(protocoloCadastrado.getEncodeMapaDeBits())
							.encodeCodigoMensagem(protocoloCadastrado.getEncodeCodigoMensagem())
							.build();
		protocoloResponse = protocoloFixture.atualizaProtocolo(protocoloAlterado, protocoloCadastrado.getProtocoloSequence()).getBody();
	}

	@Então("o response da alteração do Protocolo exibe os dados alterados corretamente")
	public void o_response_da_alteração_de_Protocolo_exibe_os_dados_alterados_corretamente() {

		assertNotNull(protocoloResponse.getId());
		assertNotNull(protocoloResponse.getProtocoloSequence());
		assertNotNull(protocoloResponse.getDataCriacao());
		assertNotNull(protocoloResponse.getDataAlteracao());
		assertEquals(protocoloResponse.getTipo(), protocoloAlterado.getTipo());
		assertEquals(protocoloResponse.getDescricao(), protocoloAlterado.getDescricao());
	}
	
}
