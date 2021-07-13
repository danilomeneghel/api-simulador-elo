#language: pt
#author: Marcelo Lopes
#versao: 1.0

@APIProtocolo
Funcionalidade: API - Protocolo - Negativos

	## NEGATIVOS ##
	@cenarioNegativo
	Esquema do Cenario: CT <CT> - -NEG- Incluir Protocolo com campo <CAMPO> inválido
		Dado que eu possua um protocolo válido
		E altere o campo <CAMPO> do Protocolo para um valor fora do enum permitido
		Quando realizo o POST do Protocolo
		Então o status code é 400
		E o response exibe a mensagem "-confirmar mensagem-"

		Exemplos:
			| CT | CAMPO                                |
			| 05 | bitsMensagem.alinhamento             |
			| 06 | bitsMensagem.bcdFillerNibblePosition |
			| 07 | bitsMensagem.encodeDadosCampo        |
			| 08 | bitsMensagem.encodeTamCampo          |
			| 09 | bitsMensagem.expandirDadosBinarios   |
			| 10 | bitsMensagem.formatoDadosCampo       |
			| 11 | bitsMensagem.tipoTamCampo            |
			| 12 | encodeBCDLLVARLLLVAR                 |
			| 13 | encodeCodigoMensagem                 |
			| 14 | encodeMapaDeBits                     |
			| 15 | status                               |
			| 16 | tipo                                 |

	@cenarioNegativo
	Esquema do Cenario: CT <CT> - -NEG- <CENARIO>
		Dado que eu possua um protocolo válido
		E remova o campo <CAMPO> do Protocolo para validação da obrigatoriedade
		Quando realizo o POST do Protocolo
		Então o status code é 400
		E o response exibe a mensagem "-confirmar mensagem-"

		Exemplos:
			| CT | CAMPO                                          |
			| 17 | bitsMensagem.alinhamento                       |
			| 18 | bitsMensagem.bcdFillerNibblePosition           |
			| 19 | bitsMensagem.bcdFillerNibbleValue              |
			| 20 | bitsMensagem.caracterPreenchimento             |
			| 21 | bitsMensagem.descricao                         |
			| 22 | bitsMensagem.descricaoDadosCampo.conteudoCampo |
			| 23 | bitsMensagem.descricaoDadosCampo.descricao     |
			| 24 | bitsMensagem.encodeDadosCampo                  |
			| 25 | bitsMensagem.encodeTamCampo                    |
			| 26 | bitsMensagem.expandirDadosBinarios             |
			| 27 | bitsMensagem.formatacaoDataHora                |
			| 28 | bitsMensagem.formatoDadosCampo                 |
			| 29 | bitsMensagem.numeroDoBit                       |
			| 30 | bitsMensagem.tam                               |
			| 31 | bitsMensagem.tamMaximo                         |
			| 32 | bitsMensagem.tamMinimo                         |
			| 33 | bitsMensagem.tipoTamCampo                      |
			| 34 | descricao                                      |
			| 35 | encodeBCDLLVARLLLVAR                           |
			| 36 | encodeCodigoMensagem                           |
			| 37 | encodeMapaDeBits                               |
			| 38 | status                                         |
			| 39 | tipo                                           |
			| 40 | versao                                         |
