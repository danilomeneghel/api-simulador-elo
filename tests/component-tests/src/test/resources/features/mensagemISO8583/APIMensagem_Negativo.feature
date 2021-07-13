#language: pt
#author: Eduardo Oliveira
#versao: 1.0

Funcionalidade: API - Cadastro de MensagensISO8583 - Testes Negativos

  ## POST - SIMULADOR - NEGATIVO - DADOS NÃO PREENCHIDOS

  Esquema do Cenario: CT <CT> - <CENARIO>
    Dado que possua um simulador com payload:
    | DESCRICAO     | TIPO     | VERSAO     | STATUS     |
    | <exDESCRICAO> | <exTIPO> | <exVERSAO> | <exSTATUS> |
    Quando realizo um POST deste simulador
    Então o status code é 400
    E o response da mensagemISO8583 exibe a mensagem "Campos obrigatórios não preenchidos"

    Exemplos:
		| CT | CENARIO                                           | exDESCRICAO        | exTIPO    | exVERSAO | exSTATUS |
		| 01 | POST - Incluir Simulador Inválido - Vazio         |                    | ""        |          | ""       |
		| 04 | POST - Incluir Simulador Inválido - Sem Descrição |                    | REBATEDOR | 4.0      | INATIVO  |
		| 05 | POST - Incluir Simulador Inválido - Sem Tipo      | TestAut - Invalido | ""        | 4.0      | INATIVO  |
		| 06 | POST - Incluir Simulador Inválido - Sem Versão    | TestAut - Invalido | REBATEDOR |          | INATIVO  |
		| 07 | POST - Incluir Simulador Inválido - Sem Status    | TestAut - Invalido | REBATEDOR | 4.0      | ""       |

	## GET FindAll SIMULADOR - NEGATIVO - SIMULADOR NÃO ENCONTRADO

	Esquema do Cenario: CT <CT> - <CENARIO>
	  Quando realizo uma busca por "<TIPO>" com o valor "<VALOR>"
	  Então o status code é 404
	  E o response exibe a mensagem "Não foi possível localizar o simulador informado"

		Exemplos:
		| CT | CENARIO                                                        | TIPO      | VALOR              |
		| 01 | FindAll - Pesquisar Simulador Inexistente - Código             | codigo    | 6666               |
		| 02 | FindAll - Pesquisar Simulador Inexistente - Identificador (ID) | id        | Não Encontrado     |
		| 05 | FindAll - Pesquisar Simulador Inexistente - Versão             | versao    | Não Encontrado     |
		| 06 | FindAll - Pesquisar Simulador Inexistente - Descrição          | descricao | Não Encontrado     |

	## GET id SIMULADOR - NEGATIVO - SIMULADOR NÃO ENCONTRADO
	Cenario: CT 01 - GET - Pesquisar Simulador por ID (Endpoint id) inexistente
	Quando realizo uma busca pelo endpoint de busca por id com valor "Não Encontrado"
	Então o status code é 404
	E o response exibe a mensagem "Não foi possível localizar o simulador informado"

	## GET codigo SIMULADOR - NEGATIVO - SIMULADOR NÃO ENCONTRADO
	Cenario: CT 01 - GET - Pesquisar Simulador por Código (Endpoint código) inexistente
	Quando realizo uma busca pelo endpoint de busca por código com valor "0"
	Então o status code é 404
	E o response exibe a mensagem "Não foi possível localizar o simulador informado"


	## PUT - SIMULADOR - NEGATIVO - SIMULADOR NÃO ENCONTRADO
	Cenario: CT 01 - PUT - Realizar requisição PUT para simulador inexistente - NEGATIVO
	Quando realizo uma requisição de PUT para um simulador inexistente
	Então o status code é 404
	E o response exibe a mensagem "Não foi possível localizar o simulador informado"