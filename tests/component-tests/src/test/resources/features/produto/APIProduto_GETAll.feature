#language: pt
#author: Marcelo Lopes
#versao: 1.0

@APIProduto @cenarioPositivo @regression
Funcionalidade: API - Pesquisa de Produtos
	
	## GET FindAll Produtos - Retorno Unico
    Cenario: CT <CT> - <CENARIO> - Retorno único
	  Dado que possua uma bandeira cadastrada:
	  | codigoBandeira     | descricao             |
	  | 007                | Bandeira teste get all|
	  Dado que possua um produto cadastrado:
	  | codigoProduto   | codigoBandeira   |tipoPlataforma   | descricao     |
	  | 001 	        | 007              |CREDITO          | produto teste |
	  Quando realizo o getAll da API de produto
	  Então a requisição é um sucesso com status 200
	  E o response apresenta uma lista com o produto cadastrado

