package br.com.elo.steps.produtos;


import br.com.elo.dto.ProdutoRequestDTO;
import br.com.elo.dto.ProdutoResponseDTO;
import br.com.elo.fixture.BandeiraFixture;
import br.com.elo.fixture.ProdutoFixture;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProdutoPutSteps{

	@Autowired
	private ProdutoFixture produtoFixture;

	@Autowired
	private BandeiraFixture bandeiraFixture;

	ProdutoResponseDTO produtoCadastrado;
	ProdutoRequestDTO produtoAlterado;
	ProdutoResponseDTO produtoResponse;


	@Quando("realizo um put com o codigoProduto alterado")
	public void realizo_um_put_com_o_codigoProduto_alterado() {

		produtoCadastrado = this.produtoFixture.getResultado().getBody();
		produtoAlterado = ProdutoRequestDTO.builder().codigoProduto(8).descricao(produtoCadastrado.getDescricao()).codigoBandeira(produtoCadastrado.getCodigoBandeira()).tipoPlataforma(produtoCadastrado.getTipoPlataforma()).build();
		produtoResponse = produtoFixture.atualizaProduto(produtoAlterado, produtoCadastrado.getCodigoBandeira(), produtoCadastrado.getCodigoProduto()).getBody();
	}

	@Quando("realizo um put com a descricao do produto alterada")
	public void realizo_um_put_com_a_descricao_do_produto_alterada() {

		produtoCadastrado = this.produtoFixture.getResultado().getBody();
		produtoAlterado = ProdutoRequestDTO.builder().codigoProduto(produtoCadastrado.getCodigoProduto()).descricao("descricao alterada teste").codigoBandeira(produtoCadastrado.getCodigoBandeira()).tipoPlataforma(produtoCadastrado.getTipoPlataforma()).build();
		produtoResponse = produtoFixture.atualizaProduto(produtoAlterado, produtoCadastrado.getCodigoBandeira(), produtoCadastrado.getCodigoProduto()).getBody();
	}

	@Quando("realizo um put com o codigoProduto e descricao alterados")
	public void realizo_um_put_com_o_codigoProduto_e_descricao_alterados() {

		produtoCadastrado = this.produtoFixture.getResultado().getBody();
		produtoAlterado = ProdutoRequestDTO.builder().codigoProduto(8).descricao("descricao alterada teste").codigoBandeira(produtoCadastrado.getCodigoBandeira()).tipoPlataforma(produtoCadastrado.getTipoPlataforma()).build();
		produtoResponse = produtoFixture.atualizaProduto(produtoAlterado, produtoCadastrado.getCodigoBandeira(), produtoCadastrado.getCodigoProduto()).getBody();
	}

	@Então("o response da alteração de Produto exibe os dados alterados corretamente")
	public void o_response_da_alteração_de_Produto_exibe_os_dados_alterados_corretamente() {

		assertNotNull(produtoResponse.getId());
		assertNotNull(produtoResponse.getCodigoProduto());
		assertNotNull(produtoResponse.getCodigoBandeira());
		assertNotNull(produtoResponse.getDataCriacao());
		assertNotNull(produtoResponse.getDataAlteracao());
		assertEquals(produtoResponse.getCodigoProduto(), produtoAlterado.getCodigoProduto());
		assertEquals(produtoResponse.getCodigoBandeira(), produtoAlterado.getCodigoBandeira());
		assertEquals(produtoResponse.getDescricao(), produtoAlterado.getDescricao());
	}

}
