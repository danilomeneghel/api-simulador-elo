#language: pt
#author: Lucas Gabriel
#versao: 1.0

@APICredenciador @cenarioPositivo @regression
Funcionalidade: API - Alteração do credenciador

  Cenario: CT 01 - Atualiza o nome com sucesso
    Dado que possua um credenciador cadastrado:
      | credenciadorCodigo   | nome               | status   |
      | 1                    | Crendeciador teste | ATIVO    |
    Quando realizo um put com o nome do credenciador alterado
    Então a requisição é um sucesso com status 200
    E o response da alteração do Credenciador exibe os dados alterados corretamente