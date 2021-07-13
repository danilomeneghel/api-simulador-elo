#language: pt
#author: Eduardo Oliveira
#versao: 1.0
#@APIEmissor @regression
Funcionalidade: API - Alteração de Emissor

 ####
  ## NEGATIVOS
 # @cenarioNegativo
  Cenario: CT 07 - -NEG- Atualizar Emissor inexistente
    Quando realizo um PUT para um Emissor inexistente
    Então o status code é 404
    E o response exibe a mensagem "-confirmar mensagem-"

  #@cenarioNegativo
  Cenario: CT 08 - -NEG- Atualizar Emissor para Código existente
    Dado que se possua dois Emissores cadastrados
    Quando realizo um PUT com o código de Emissor já existente
    Então o status code é 400
    E o response exibe a mensagem "-confirmar mensagem-"
    E o Emissor é apresentado na base de dados sem nenhuma alteração

  #@cenarioNegativo
  Cenario: CT 09 - -NEG- Atualizar Emissor sem enviar nenhuma informação
    Dado que se possua um Emissor cadastrado
    Quando realizo um PUT do Emissor sem preenchimento de nenhum campo
    Então o status code é 400
    E o response exibe a mensagem "-confirmar mensagem-"
    E o Emissor é apresentado na base de dados sem nenhuma alteração

  #@cenarioNegativo
  Esquema do Cenario: CT <CT> - -NEG- <CENARIO>
    Dado que se possua um Emissor cadastrado
    Quando realizo um PUT do Emissor com o campo código do Emissor com valor <VALOR>
    Então o status code é 400
    E o response exibe a mensagem "-confirmar mensagem-"
    E o Emissor é apresentado na base de dados sem nenhuma alteração

    Exemplos:
      | CT | CENARIO                                          | VALOR |
      | 10 | Alterar Emissor com código inválido - maior 5    | 12345 |
      | 11 | Alterar Emissor com código inválido - com letras | A123  |
      | 12 | Altarar Emissor com código zerado                |  0000 |

  #@cenarioNegativo
  Cenario: CT 13 - -NEG- Atualizar Emissor com Plataforma inválida
    Dado que se possua um Emissor cadastrado
    Quando realizo um PUT do Emissor com preenchimento do campo Plataforma inexistente
    Então o status code é 400
    E o response exibe a mensagem "-confirmar mensagem-"
    E o Emissor é apresentado na base de dados sem nenhuma alteração

  #@cenarioNegativo
  Esquema do Cenario: CT <CT> - -NEG- Atualizar Emissor sem enviar campo <CAMPO>
    Dado que se possua um Emissor cadastrado
    Quando realizo um PUT do Emissor sem preenchimento do campo <CAMPO>
    Então o status code é 400
    E o response exibe a mensagem "-confirmar mensagem-"
    E o Emissor é apresentado na base de dados sem nenhuma alteração

    Exemplos:
      | CT | CAMPO          |
      | 14 | codigoBandeira |
      | 15 | codigoEmissor  |
      | 16 | nome           |
      | 17 | processadora   |
      | 18 | plataforma     |

  #@cenarioNegativo
  Cenario: CT 18 - -NEG- Atualizar Emissor com Bandeira inexistente
    Dado que se possua um Emissor cadastrado
    Quando realizo um PUT do Produto com preenchimento do campo Código da Bandeira inexistente
    Então o status code é 400
    E o response exibe a mensagem "-confirmar mensagem-"
    E o Emissor é apresentado na base de dados sem nenhuma alteração
