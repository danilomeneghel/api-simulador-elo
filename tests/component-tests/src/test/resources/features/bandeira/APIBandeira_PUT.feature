#language: pt
#author: Eduardo Oliveira
#versao: 1.0

@APIBandeira @cenarioPositivo @regression
Funcionalidade: API - Alteração de Bandeiras
 	
	Cenario: CT 01 - Atualiza dados da Bandeira - Código
	  Dado que possua uma bandeira cadastrada:
	  | codigoBandeira     | descricao         |
	  | 007                | Bandeira teste put|
	  Quando realizo um put com o codigoBandeira alterado
	  Então a requisição é um sucesso com status 200
	  E o response da alteração de Bandeira exibe os dados alterados corretamente
	
	Cenario: CT 02 - Atualiza dados da Bandeira - Descrição
	  Dado que possua uma bandeira cadastrada:
	  | codigoBandeira     | descricao           |
	  | 007                | Bandeira teste put 2|
	  Quando realizo um put com a descricao da bandeira alterada
	  Então a requisição é um sucesso com status 200
	  E o response da alteração de Bandeira exibe os dados alterados corretamente
		
	Cenario: CT 03 - Atualiza dados da Bandeira - Código e Descrição
	  Dado que possua uma bandeira cadastrada:
	  | codigoBandeira     | descricao             |
	  | 007                | Bandeira teste put 3|
	  Quando realizo um put com o codigoBandeira e descricao alterados
	  Então a requisição é um sucesso com status 200
	  E o response da alteração de Bandeira exibe os dados alterados corretamente