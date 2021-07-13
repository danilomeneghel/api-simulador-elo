#language: pt
#author: Eduardo Oliveira
#versao: 1.0
#@APIEmissor @regression
Funcionalidade: API - Cadastro de Emissores


  ## Negativo Campos Obrigatórios
  #@cenarioNegativo
  Esquema do Cenario: CT <CT> - -NEG- <CENARIO>
    Dado um Emissor com payload:
      | CODIGOEMI     | NOMEEMI     | CODPROCESSADORA     | PLATAFORMA     |
      | <exCODIGOEMI> | <exNOMEEMI> | <exCODPROCESSADORA> | <exPLATAFORMA> |
    Quando realizo um POST deste Emissor
    Então o status code é 400
    E o response exibe a mensagem "-confirmar mensagem-"

    Exemplos:
      | CT | CENARIO                                              | exCODIGOEMI | exNOMEEMI          | exCODPROCESSADORA | exPLATAFORMA   |
      | 05 | Incluir Emissor sem preencher Código do Emissor      |             | Emissor CT 5 õÉâ!@ |              2222 | Crédito        |
      | 06 | Incluir Emissor sem preencher Nome do Emissor        |        1111 |                    |              0002 | Débito         |
      | 07 | Incluir Emissor sem preencher Código da Processadora |        0456 | Emissor CT 6       |                   | Crédito/Débito |
      | 08 | Incluir Emissor sem preencher Plataforma             |        0457 | Emissor CT 7       |              1234 |                |
      | 09 | Incluir Emissor sem preencher nenhum campo           |             |                    |                   |                |

  ## Negativo Campos Inválidos
  #@cenarioNegativo
  Esquema do Cenario: CT <CT> - -NEG- <CENARIO>
    Dado um Emissor com payload:
      | CODIGOEMI     | NOMEEMI     | CODPROCESSADORA     | PLATAFORMA     |
      | <exCODIGOEMI> | <exNOMEEMI> | <exCODPROCESSADORA> | <exPLATAFORMA> |
    Quando realizo um POST deste Emissor
    Então o status code é 400
    E o response exibe a mensagem "-confirmar mensagem-"

    Exemplos:
      | CT | CENARIO                                                  | exCODIGOEMI | exNOMEEMI     | exCODPROCESSADORA | exPLATAFORMA   |
      | 10 | Incluir Emissor com Código do Emissor zerado             |        0000 | Emissor CT 9  |              0002 | Débito         |
      | 11 | Incluir Emissor com Código da Processadora zerado        |        0456 | Emissor CT 10 |              0000 | Crédito/Débito |
      | 12 | Incluir Emissor com Plataforma Inválida                  |        0456 | Emissor CT 11 |              0000 | Inválido       |
      | 13 | Incluir Emissor com Código Emissor com letras            | 0ABC        | Emissor CT 12 |              0000 | Inválido       |
      | 14 | Incluir Emissor com Código da Processadora com letras    |        0456 | Emissor CT 13 | 1ABC              | Crédito        |
      | 15 | Incluir Emissor com Código do Emissor 5 dígitos          |       12345 | Emissor CT 14 |              1234 | Crédito        |
      | 16 | Incluir Emissor com Código da Processadora com 5 dígitos |        1234 | Emissor CT 15 |             12345 | Débito         |

  ## Negativo - Duplicado
  #@cenarioNegativo
  Cenario: CT 17 - -NEG- Incluir Emissor duplicado
    Dado que eu possua uma bandeira cadastrada para o emissor
    Dado que eu possua um Emissor cadastrado
    Quando realizo um POST com o mesmo Código Emissor e Processadora
    Então o status code é 400
    E o response exibe a mensagem "-confirmar mensagem-"

  Cenario: CT 18 - -NEG- Incluir Emissor sem preencher o Código da Bandeira
    Quando realizo um POST sem preencher o Código da Bandeira
    Então o status code é 400
    E o response exibe a mensagem "-confirmar mensagem-"

  #@cenarioPositivo
  Cenario: CT 19 - Incluir Emissor com mesmo código em outra Bandeira
    Dado que eu possua duas bandeiras cadastradas
    E que eu possua um Emissor cadastrado para uma bandeira
    Quando realizo o POST de um novo Emissor com os mesmos dados para outra bandeira
    Então o status code é 201
    E o response apresenta todos os campos referente ao Emissor preenchidos corretamente
    E o Emissor criado é apresentada na base de dados