#language: pt
#author: Eduardo Oliveira
#versao: 1.0

@APIEmissor @cenarioPositivo @regression
Funcionalidade: API - Pesquisa de Emissores
	
	## GET FindAll Emissor
  Esquema do Cenario: CT <CT> - <CENARIO> - Retorno único
    Dado que possua uma bandeira cadastrada para o emissor
    Dado que eu possua um Emissor cadastrado
      | codigoEmissor | nomeEmissor  | codigoProcessadora | plataforma | codigoBandeira |
      | 1             | Emissor CT 1 | 2222               | CREDITO    | 1              |

    Quando eu realizo uma busca de Emissor por parametro "<PARAMETRO>" com o valor "<VALOR>"
    Então a requisição é um sucesso com status 200
    E o response apresenta o Emissor pesquisado com os campos corretos

    Exemplos:
      | CT | CENARIO                                    | PARAMETRO          | VALOR |
      | 01 | Pesquisar Emissor - Código do Emissor      | codigoEmissor      | 1     |
      | 02 | Pesquisar Emissor - Código da Processadora | codigoProcessadora | 2222  |
      | 03 | Pesquisar Emissor - Código da Bandeira     | bandeiraCodigo     | 1     |


	

