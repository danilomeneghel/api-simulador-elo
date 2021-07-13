#language: pt
#author: Eduardo Oliveira
#versao: 1.0

@APIBandeira @cenarioPositivo @regression
Funcionalidade: API - Pesquisa de Bandeira por endpoint id e código
	
	## GET id - Bandeira
	
	Cenario: CT 01 - Pesquisar Bandeira por ID (api/bandeiras/$id)
	  Dado que possua uma bandeira cadastrada:
	  | codigoBandeira     | descricao            |
	  | 007                | Bandeira teste get Id|
	  Quando eu realizo uma busca de bandeira pelo endpoint de busca por id		
	  Então a requisição é um sucesso com status 200
	  E o response apresenta os campos da Bandeira de acordo com a base de dados
	  
	Cenario: CT 02 - Pesquisar Bandeira por Código (api/bandeiras/seq/$codigo)
	  Dado que possua uma bandeira cadastrada:
	  | codigoBandeira     | descricao            |
	  | 007                | Bandeira teste get codigo|
	  Quando eu realizo uma busca de bandeira pelo endpoint de busca por código		
	  Então a requisição é um sucesso com status 200
	  E o response apresenta os campos da Bandeira de acordo com a base de dados
	  
	  