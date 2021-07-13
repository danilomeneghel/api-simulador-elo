#language: pt
#author: Lucas Gabriel
#versao: 1.0

@APISimulador @cenarioPositivo @regression
Funcionalidade: API - Cadastro de Credenciador

  ## POST - CREDENCIADOR
  Esquema do Cenario: CT <CT> - <CENARIO>
    Dado que possua um credenciador com payload:
      | NOME      | CREDENCIADORCODIGO     |   STATUS    |
      | <exNOME>  | <exCREDENCIADORCODIGO> |  <exSTATUS> |
    Quando realizo um POST deste credenciador
    Então a requisição é um sucesso com status 201
    E o response apresenta todos os campos referente ao credenciador preenchidos corretamente

    Exemplos:
      | CT | CENARIO                                 | exCREDENCIADORCODIGO   |exNOME             | exSTATUS   |
      | 01 | Incluir Credenciador - Injetor Ativo    |    25                  |TestAut1 ATIVO     | ATIVO      |
      | 02 | Incluir Credenciador - Injetor Inativo  |    10                  |TestAut2 INATIVO   | INATIVO    |
