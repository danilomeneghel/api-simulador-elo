#language: pt
#author: Eduardo Oliveira
#versao: 1.0
@APIAmbiente @regression
Funcionalidade: API - Pesquisa de Ambiente

  @cenarioPositivo
  Esquema do Cenario: CT <CT> - <CENARIO> - Retorno único
    Dado que eu possua um Ambiente
    Quando eu realizo uma busca por <TIPO>
    Então o status code é 200
    E o response apresenta os campos do Ambiente corretamente conforme a base de dados

    Exemplos:
      | CT | CENARIO                                 | TIPO             |
      | 01 | Pesquisar Ambiente - AmbienteSequence   | ambienteSequence |
      | 02 | Pesquisar Ambiente - Identificador (ID) | id               |

#  @cenarioPositivo
#  Esquema do Cenario: CT <CT> - <CENARIO> - Retorno multiplo
#    Dado que eu possua mais de um Ambiente com o <CAMPO> com o mesmo valor
#    Quando eu realizo uma busca por <CAMPO>
#    Então o status code é 200
#    E o response apresenta uma lista com a quantidade correta de registros que atendem a condição
#    E todos os ambientes possuem o campo <CAMPO> com o valor buscado
#    E o response apresenta os campos do Ambiente corretamente conforme a base de dados
#
#    Exemplos:
#      | CT | CENARIO                        | CAMPO     |
#      | 03 | Pesquisar Ambiente - Versão    | versao    |
#      | 04 | Pesquisar Ambiente - Descrição | descricao |
#
#  @cenarioPositivo
#  Esquema do Cenario: CT <CT> - <CENARIO> - Retorno multiplo
#    Dado que eu possua mais de um Ambiente com o <CAMPO> com o mesmo valor
#    Quando eu realizo uma busca por <CAMPO> com valor <VALOR>
#    Então o status code é 200
#    E o response apresenta uma lista com a quantidade correta de registros que atendem a condição
#    E todos os ambientes possuem o campo <CAMPO> com o valor <VALOR>
#    E o resultado é de acordo com a base de dados
#
#    Exemplos:
#      | CT | CENARIO                               | CAMPO  | VALOR     |
#      | 05 | Pesquisar Ambiente - Tipo - Injetor   | tipo   | INJETOR   |
#      | 06 | Pesquisar Ambiente - Tipo - Rebatedor | tipo   | REBATEDOR |
#      | 07 | Pesquisar Ambiente - Status - ATIVO   | status | ATIVO     |
#      | 08 | Pesquisar Ambiente - Status - INATIVO | status | INATIVO   |

