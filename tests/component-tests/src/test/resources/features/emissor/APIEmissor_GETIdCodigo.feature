#language: pt
#author: Eduardo Oliveira
#versao: 1.0

@APIEmissor @cenarioPositivo @regression
Funcionalidade: API - Pesquisa de Emissor por endpoint id e código
	
	## GET id - Emissor
  Cenario: CT 01 - Pesquisar Emissor por ID (api/emissores/$id)
    Dado que possua uma bandeira cadastrada para o emissor
    Dado que eu possua um Emissor cadastrado
      | codigoEmissor | nomeEmissor  | codigoProcessadora | plataforma | codigoBandeira |
      | 1             | Emissor CT 1 | 2222               | CREDITO    | 1              |
      | 1111          | Emissor CT 2 | 2                  | DEBITO     | 1              |
      | 1111          | Emissor CT 3 | 1003               | CREDITO    | 1              |
      | 456           | Emissor CT 4 | 123                | MULTIPLOS  | 1              |
    Quando eu realizo uma busca pelo endpoint de busca de Emissor por id
    Então a requisição é um sucesso com status 200
    E o response apresenta os campos dos Emissor corretamente
	  
	## GET codigo - Emissor
  Cenario: CT 02 - Pesquisar Emissor por Codigo (api/emissores/cod/$codigoBandeira/$codigoEmissor)
    Dado que possua uma bandeira cadastrada para o emissor
    Dado que eu possua um Emissor cadastrado
      | codigoEmissor | nomeEmissor  | codigoProcessadora | plataforma | codigoBandeira |
      | 1             | Emissor CT 1 | 2222               | CREDITO    | 1              |
      | 1111          | Emissor CT 2 | 2                  | DEBITO     | 1              |
      | 1111          | Emissor CT 3 | 1003               | CREDITO    | 1              |
      | 456           | Emissor CT 4 | 123                | MULTIPLOS  | 1              |
    Quando eu realizo uma busca pelo endpoint de busca de Emissor por codigoBandeira e codigoEmissor
    Então a requisição é um sucesso com status 200
    E o response apresenta os campos dos Emissor corretamente
	  
