#language: pt
#author: Marcelo Lopes
#versao: 1.0

@APIProduto @cenarioPositivo @regression
Funcionalidade: API - Pesquisa de Produto por endpoint id e código
	
	## GET id - Produto
	Cenario: CT 01 - Pesquisar Produto por ID (api/produtos/$id)
	  Dado que possua uma bandeira cadastrada:
	  | codigoBandeira     | descricao             |
	  | 007                | Bandeira teste get all|
	  Dado que possua um produto cadastrado:
	  | codigoProduto   | codigoBandeira   |tipoPlataforma   | descricao     |
	  | 001 	        | 007              |CREDITO          | produto teste |
	  Quando eu realizo uma busca de produto pelo endpoint de busca por id
	  Então a requisição é um sucesso com status 200
	  E o response apresenta os campos do Produto de acordo com a base de dados
	  
	## GET codigo - Produto
	Cenario: CT 02 - Pesquisar Produto por Codigo (api/produtos/seq/$codigo)
	  Dado que possua uma bandeira cadastrada:
	  | codigoBandeira     | descricao             |
	  | 007                | Bandeira teste get all|
	  Dado que possua um produto cadastrado:
	  | codigoProduto   | codigoBandeira   |tipoPlataforma   | descricao     |
	  | 001 	        | 007              |CREDITO          | produto teste |
	  Quando eu realizo uma busca de produto pelo endpoint de busca por código
	  Então a requisição é um sucesso com status 200
	  E o response apresenta os campos do Produto de acordo com a base de dados
