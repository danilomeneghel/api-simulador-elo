package br.com.elo.steps.fluxoTransacional;


import br.com.elo.dto.FluxoTransacionalRequestCriteriaDTO;
import br.com.elo.dto.FluxoTransacionalResponseDTO;
import br.com.elo.dto.MensagemISO8583RequestCriteriaDTO;
import br.com.elo.fixture.FluxoTransacionalFixture;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FluxoTransacionalGetAllSteps {

	@Autowired
	private FluxoTransacionalFixture fluxoTransacionalFixture;


	FluxoTransacionalResponseDTO fluxoTransacionalEsperado;
	List<FluxoTransacionalResponseDTO> fluxoTransacionalResponse;


	@Quando("realizo o getAll da API de fluxotransacional")
	public void realizo_o_getAll_da_API_de_fluxotransacional() {

		fluxoTransacionalEsperado = this.fluxoTransacionalFixture.getResultado().getBody();
		fluxoTransacionalResponse = Arrays.asList(fluxoTransacionalFixture.buscaFluxoTransacional(FluxoTransacionalRequestCriteriaDTO.builder().fluxoTransacionalSequence(fluxoTransacionalEsperado.getFluxoTransacionalSequence()).build()).getBody());
	}

	@Então("o response apresenta uma lista com um fluxotransacional cadastrado")
	public void o_response_apresenta_uma_lista_com_uma_fluxotransacional_cadastrado() {

		assertNotNull(fluxoTransacionalResponse);
		assertEquals(fluxoTransacionalResponse.get(0).getId(), fluxoTransacionalEsperado.getId());
		assertEquals(fluxoTransacionalResponse.get(0).getFluxoTransacionalSequence(), fluxoTransacionalEsperado.getFluxoTransacionalSequence());
		assertEquals(fluxoTransacionalResponse.get(0).getDataCriacao(), fluxoTransacionalEsperado.getDataCriacao());
		assertEquals(fluxoTransacionalResponse.get(0).getMensagemSolicitacaoPernaUmSequence(), fluxoTransacionalEsperado.getMensagemSolicitacaoPernaUmSequence());
		assertEquals(fluxoTransacionalResponse.get(0).getMensagemSolicitacaoPernaDoisSequence(), fluxoTransacionalEsperado.getMensagemSolicitacaoPernaDoisSequence());
		assertEquals(fluxoTransacionalResponse.get(0).getDescricao(), fluxoTransacionalEsperado.getDescricao());

	}
	


}
