package br.com.elo.steps.produtos;


import br.com.elo.domain.TipoPlataforma;
import br.com.elo.dto.ProdutoRequestDTO;
import br.com.elo.dto.ProdutoResponseDTO;
import br.com.elo.fixture.BandeiraFixture;
import br.com.elo.fixture.ProdutoFixture;
import cucumber.api.DataTable;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProdutoPostSteps{

	@Autowired
	private ProdutoFixture produtoFixture;

	@Autowired
	private BandeiraFixture bandeiraFixture;

	ProdutoRequestDTO produtoRequest;
	ProdutoResponseDTO produtoResponse;

	@Dado("que possua um produto com payload:")
	public void que_possua_um_produto_com_payload(DataTable dataTable) {

		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> columns : rows) {
			produtoRequest = ProdutoRequestDTO.builder()
					.codigoProduto(Integer.parseInt(columns.get("codigoProduto")))
					.codigoBandeira(Integer.parseInt(columns.get("codigoBandeira")))
					.descricao(columns.get("descricao"))
					.tipoPlataforma(TipoPlataforma.valueOf(columns.get("tipoPlataforma")))
					.build();
		}
	}

	@Quando("realizo um POST deste produto")
	public void realizo_um_post_deste_produto() {

		produtoResponse = produtoFixture.incluirProduto(produtoRequest).getBody();
	}

	@Então("o response apresenta todos os campos referente o produto preenchidos corretamente")
	public void o_response_apresenta_todos_os_campos_referente_o_produto_preenchidos_corretamente() {

		assertNotNull(produtoResponse.getId());
		assertNotNull(produtoResponse.getCodigoProduto());
		assertNotNull(produtoResponse.getCodigoBandeira());
		assertNotNull(produtoResponse.getDataCriacao());
		assertEquals(produtoResponse.getCodigoProduto(), produtoRequest.getCodigoProduto());
		assertEquals(produtoResponse.getDescricao(), produtoRequest.getDescricao());
		assertEquals(produtoResponse.getCodigoBandeira(), produtoRequest.getCodigoBandeira());
		assertEquals(produtoResponse.getTipoPlataforma(), produtoRequest.getTipoPlataforma());

	}

}