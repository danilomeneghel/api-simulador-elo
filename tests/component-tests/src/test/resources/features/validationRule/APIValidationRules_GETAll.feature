#language: pt
#author: Eduardo Oliveira
#versao: 1.0

@APIValidationRule @cenarioPositivo @regression
Funcionalidade: API - Pesquisa de bandeira por filtros

	## GET FindAll ValidationRuleSequence
	Cenario: CT 01 - Pesquisar por ValidationRuleSequence
		Dado que possua uma lista validationBitRequestDTO:
			| bitNumber     | validationRule        | fillingMode     | value     |
			| 2             | BITIGUAL              |  VALORFIXO      | 123456    |
		Dado que possua uma validationRules cadastrada:
			| name           | status     |
			| RegraValidacao | ATIVO      |
	  Quando eu realizo uma busca por validationRulesSequence
	  Então a requisição é um sucesso com status 200
	  E o response apresenta os campos da ValidationRule corretamente conforme a base de dados


