#language: pt
#author: Eduardo Oliveira
#versao: 1.0

#@APIEmissor @cenarioPositivo @regression
Funcionalidade: API - Pesquisa de Emissores

 #### NEGATIVOS
	
	Esquema do Cenario: CT <CT> - <CENARIO> 
	  Quando realizo uma busca de Emissor por parametro "<TIPO>" com o valor "<VALOR>"
	  Então o status code é 404
	  E o response exibe a mensagem "Não foi possível localizar o Emissor informado"
		
		Exemplos:
		| CT | CENARIO                                                       | TIPO               | VALOR          |
		| 01 | FindAll - Pesquisar Emissor Inexistente - Código              | codigo             | 6666           |
		| 02 | FindAll - Pesquisar Emissor Inexistente - Identificador (ID)  | id                 | Não Encontrado |
		| 05 | FindAll - Pesquisar Emissor Inexistente - Código Emissor      | codigoEmissor      | Não Encontrado |
		| 06 | FindAll - Pesquisar Emissor Inexistente - Código Processadora | codigoProcessadora | Não Encontrado |
		| 06 | FindAll - Pesquisar Emissor Inexistente - Plataforma          | plataforma         | Não Encontrado |
		| 06 | FindAll - Pesquisar Emissor Inexistente - Código da Bandeira  | codigoBandeira     | Não Encontrado |
		| 06 | FindAll - Pesquisar Emissor Inexistente - Status              | status             | Não Encontrado |