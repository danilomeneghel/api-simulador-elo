#language: pt
#author: Eduardo Oliveira
#versao: 1.0
@APIEmissor @regression
Funcionalidade: API - Cadastro de Emissores

  @cenarioPositivo
  Esquema do Cenario: CT <CT> - <CENARIO>
    Dado que possua uma bandeira cadastrada para o emissor
    E um Emissor com payload:
      | CODIGOEMI     | NOMEEMI     | CODPROCESSADORA     | PLATAFORMA     | BANDEIRACOD     |
      | <exCODIGOEMI> | <exNOMEEMI> | <exCODPROCESSADORA> | <exPLATAFORMA> | <exBANDEIRACOD> |
    Quando realizo um POST deste Emissor
    Então a requisição é um sucesso com status 201
    E o response apresenta todos os campos referente ao Emissor preenchidos corretamente


    Exemplos:
      | CT | CENARIO                                                  | exCODIGOEMI | exNOMEEMI    | exCODPROCESSADORA | exPLATAFORMA | exBANDEIRACOD |
      | 01 | Incluir Emissor - Crédito - 1 digito                     | 1           | Emissor CT 1 | 2222              | CREDITO      | 1             |
      | 02 | Incluir Emissor - Débito - 4 digitos                     | 1111        | Emissor CT 2 | 0002              | DEBITO       | 1             |
      | 03 | Incluir Emissor - Emissor Existente - Outra Processadora | 1111        | Emissor CT 3 | 1003              | CREDITO      | 1             |
      | 04 | Incluir Emissor - Plataforma Crédito e Débito            | 456         | Emissor CT 4 | 0123              | MULTIPLOS    | 1             |

