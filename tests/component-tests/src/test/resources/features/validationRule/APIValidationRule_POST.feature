#language: pt
#author: Marcelo Lopes
#versao: 1.0

@APIValidationRule @cenarioPositivo @regression
Funcionalidade: API - Cadastro de ValidationRules
  
  ## POST - Bandeira
  Esquema do Cenario: CT <CT> - <CENARIO>
    Dado que possua uma lista validationBitRequestDTO:
    | bitNumber     | validationRule        | fillingMode     | value     |
    | 2             | BITIGUAL              |  VALORFIXO      | 123456    |
    Dado que possua uma validationRule com payload:
    | name     | status     |
    | <exNAME> | <exSTATUS> |
    Quando realizo um POST desta validationRule
    Então a requisição é um sucesso com status 201
    E o response apresenta todos os campos referente a validationRule preenchidos corretamente
		
    Exemplos: 
		| CT | CENARIO                            | exNAME              | exSTATUS |
		| 01 | Incluir ValidationRule - ATIVA     | RegrasValidacao1    | ATIVO    |
		| 02 | Incluir ValidationRule - ATIVA     | RegrasValidacao2    | ATIVO    |
		| 03 | Incluir ValidationRule - INATIVA   | RegrasValidacao3    | INATIVO  |
		
	