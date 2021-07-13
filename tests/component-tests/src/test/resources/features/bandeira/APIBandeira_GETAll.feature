#language: pt
#author: Eduardo Oliveira
#versao: 1.0

@APIBandeira @cenarioPositivo @regression
Funcionalidade: API - Pesquisa de bandeira por filtros

	## GET FindAll Bandeira
	Cenario: CT 01 - Pesquisar por todas as Bandeiras
	  Dado que possua uma bandeira cadastrada:
	  | codigoBandeira     | descricao             |
      | 007                | Bandeira teste get all|
	  Quando realizo o getAll da API de bandeira
	  Então a requisição é um sucesso com status 200
	  E o response apresenta uma lista com a bandeira cadastrada


