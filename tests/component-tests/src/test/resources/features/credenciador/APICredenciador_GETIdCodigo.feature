#language: pt
#author: Lucas Gabriel
#versao: 1.0

@APICredenciador @cenarioPositivo @regression
Funcionalidade: API - Pesquisa de Credenciador por endpoint id e código

  ## GET id - Credenciador
  Cenario: CT 01 - Pesquisar Credenciador por ID (api/credenciadores/$id)
    Dado que possua um credenciador cadastrado:
    | nome                | credenciadorCodigo     |   status    |
    | Credenciador Teste  | 001                    |    ATIVO    |
    Quando eu realizo uma busca de credenciador pelo endpoint de busca por id
    Então a requisição é um sucesso com status 200
    E o response apresenta os campos do Credenciador de acordo com a base de dados


  ## GET codigo - Credenciador
  Cenario: CT 02 - Pesquisar Credenciador por Codigo (api/credenciadores/seq/$codigo)
    Dado que possua um credenciador cadastrado:
    | nome                | credenciadorCodigo   |   status    |
    | Credenciador Teste  | 001                    |    ATIVO    |
    Quando eu realizo uma busca de credenciador pelo endpoint de busca por código
    Então a requisição é um sucesso com status 200
    E o response apresenta os campos do Credenciador de acordo com a base de dados