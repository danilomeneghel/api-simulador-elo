package br.com.elo.steps.produtos;


import br.com.elo.dto.ProdutoRequestDTO;
import br.com.elo.fixture.BandeiraFixture;
import br.com.elo.fixture.ProdutoFixture;
import cucumber.api.java.pt.Dado;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProdutoCommonSteps {


	@Autowired
	private ProdutoFixture produtoFixture;


	@Dado("que possua um produto cadastrado:")
	public void que_possua_um_produto_cadastrado(List<ProdutoRequestDTO> ProdutoRequestDTOList) {

		for (ProdutoRequestDTO produtoRequestDTO : ProdutoRequestDTOList) {
			produtoFixture.incluirProduto(produtoRequestDTO);
		}
	}
}
