package br.com.elo.steps.produtos;


import br.com.elo.dto.ProdutoResponseDTO;
import br.com.elo.fixture.ProdutoFixture;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProdutoGetIdCodigoSteps {

	@Autowired
	private ProdutoFixture produtoFixture;

	ProdutoResponseDTO produtoEsperado;
	ProdutoResponseDTO produtoResponse;


	@Quando("eu realizo uma busca de produto pelo endpoint de busca por id")
	public void eu_realizo_uma_busca_de_produto_pelo_endpoint_de_busca_por_id() {

		produtoEsperado = this.produtoFixture.getResultado().getBody();
		produtoResponse = produtoFixture.consultaProdutoId(produtoEsperado.getId()).getBody();
	}

	@Quando("eu realizo uma busca de produto pelo endpoint de busca por código")
	public void eu_realizo_uma_busca_de_produto_pelo_endpoint_de_busca_por_codigo() {

		produtoEsperado = this.produtoFixture.getResultado().getBody();
		produtoResponse = produtoFixture.consultaProdutoCodigo(produtoEsperado.getCodigoBandeira(), produtoEsperado.getCodigoProduto()).getBody();
	}

	@Então("o response apresenta os campos do Produto de acordo com a base de dados")
	public void o_response_apresenta_os_campos_do_Produto_de_acordo_com_a_base_de_dados() {

		assertNotNull(produtoResponse);
		assertEquals(produtoResponse.getCodigoBandeira(), produtoEsperado.getCodigoBandeira());
		assertEquals(produtoResponse.getDataCriacao(), produtoEsperado.getDataCriacao());
		assertEquals(produtoResponse.getCodigoProduto(), produtoEsperado.getCodigoProduto());
		assertEquals(produtoResponse.getDescricao(), produtoEsperado.getDescricao());

	}
}
