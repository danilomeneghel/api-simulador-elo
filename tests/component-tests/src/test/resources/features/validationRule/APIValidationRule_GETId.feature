#language: pt
#author: Marcelo Lopes
#versao: 1.0

@APIValidationRule @cenarioPositivo @regression
Funcionalidade: API - Pesquisa de ValidationRule por endpoint id
	
	## GET id - ValidationRule
	
	Cenario: CT 01 - Pesquisar ValidationRule por ID
		Dado que possua uma lista validationBitRequestDTO:
			| bitNumber     | validationRule        | fillingMode     | value     |
			| 2             | BITIGUAL              |  VALORFIXO      | 123456    |
		Dado que possua uma validationRules cadastrada:
			| name           | status     |
			| RegraValidacao | ATIVO      |
	  Quando eu realizo uma busca de validationRule pelo endpoint de busca por id
	  Então a requisição é um sucesso com status 200
	  E o response apresenta os campos da ValidationRule de acordo com a base de dados

	  
	  