#language: pt
#author: Eduardo Oliveira
#versao: 1.0

@APIBandeira @cenarioPositivo @regression
Funcionalidade: API - Cadastro de Bandeiras
  
  ## POST - Bandeira
  Esquema do Cenario: CT <CT> - <CENARIO>
    Dado que possua uma bandeira com payload:
    | codigoBandeira     | descricao     |
    | <exCODIGOBND>      | <exDESCRICAO> |
    Quando realizo um POST desta bandeira
    Então a requisição é um sucesso com status 201
    E o response apresenta todos os campos referente a bandeira preenchidos corretamente
		
    Exemplos: 
		| CT | CENARIO                      | exCODIGOBND | exDESCRICAO         |
		| 01 | Incluir Bandeira - 1 dígitos | 007         | Bandeira CT 1       |
		| 02 | Incluir Bandeira - 2 dígitos | 077         | Bandeira CT 2       |
		| 03 | Incluir Bandeira - 3 dígitos | 777         | Bandeira CT 3       |
		
	