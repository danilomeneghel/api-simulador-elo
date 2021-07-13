package br.com.elo.steps.produtos;


import br.com.elo.dto.MensagemISO8583RequestCriteriaDTO;
import br.com.elo.dto.ProdutoRequestCriteriaDTO;
import br.com.elo.dto.ProdutoResponseDTO;
import br.com.elo.fixture.ProdutoFixture;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProdutoGetAllSteps {

	@Autowired
	private ProdutoFixture produtoFixture;

	ProdutoResponseDTO produtoEsperado;
	List<ProdutoResponseDTO> produtoResponse;


	@Quando("realizo o getAll da API de produto")
	public void realizo_o_getAll_da_API_de_produto() {

		produtoEsperado = this.produtoFixture.getResultado().getBody();
		produtoResponse = Arrays.asList(produtoFixture.buscaProduto(ProdutoRequestCriteriaDTO.builder().codigoProduto(produtoEsperado.getCodigoProduto()).build()).getBody());
	}

	@Então("o response apresenta uma lista com o produto cadastrado")
	public void o_response_apresenta_uma_lista_com_o_produto_cadastrado() {

		assertNotNull(produtoResponse);
		assertEquals(produtoResponse.get(0).getId(), produtoEsperado.getId());
		assertEquals(produtoResponse.get(0).getCodigoBandeira(), produtoEsperado.getCodigoBandeira());
		assertEquals(produtoResponse.get(0).getDataCriacao(), produtoEsperado.getDataCriacao());
		assertEquals(produtoResponse.get(0).getCodigoProduto(), produtoEsperado.getCodigoProduto());
		assertEquals(produtoResponse.get(0).getDescricao(), produtoEsperado.getDescricao());

	}

}
