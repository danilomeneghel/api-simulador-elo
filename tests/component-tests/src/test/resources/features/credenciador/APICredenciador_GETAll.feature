#language: pt
#author: Lucas Gabriel
#versao: 1.0

@APICredenciador @cenarioPositivo @regression
Funcionalidade: API - Pesquisa de Credenciador

## GET FindAll Credenciador - Retorno Unico
  Cenario: CT <CT> - <CENARIO> - Retorno único
    Dado que possua um credenciador cadastrado:
    | nome                | credenciadorCodigo     |   status    |
    | Credenciador Teste  | 001                    |    ATIVO    |
    Quando realizo o getAll da API de credenciador
    Então a requisição é um sucesso com status 200
    E o response apresenta uma lista com o credenciador cadastrado

