package br.com.elo.steps.mensagens;


import br.com.elo.dto.MensagemISO8583RequestDTO;
import br.com.elo.fixture.MensagemISO8583Fixture;
import br.com.elo.fixture.ProtocoloFixture;
import cucumber.api.java.pt.Dado;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class MensagemISO8583CommonSteps{


	@Autowired
	private MensagemISO8583Fixture mensagemISO8583Fixture;


	@Autowired
	private ProtocoloFixture protocoloFixture;

	@Dado("que possua uma mensagemISO8583 cadastrada:")
	public void que_possua_uma_mensagemISO8583_cadastrada(List<MensagemISO8583RequestDTO> mensagemISO8583RequestDTOList) {


		for (MensagemISO8583RequestDTO mensagemISO8583RequestDTO : mensagemISO8583RequestDTOList) {

			mensagemISO8583RequestDTO.setProtocoloSequence(protocoloFixture.getResultado().getBody().getProtocoloSequence());
			mensagemISO8583Fixture.incluirMensagemISO8583(mensagemISO8583RequestDTO);
		}
	}
}
