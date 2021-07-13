#language: pt
#author: Marcelo Lopes
#versao: 1.0

@APIProduto @cenarioPositivo @regression
Funcionalidade: API - Cadastro de Produtos

  @cenarioPositivo
  Esquema do Cenario: CT <CT> - <CENARIO>
    Dado que possua uma bandeira cadastrada:
    | codigoBandeira     | descricao             |
    | 007                | Bandeira teste get all|
    Dado que possua um produto com payload:
    | codigoProduto     | codigoBandeira     |tipoPlataforma     | descricao     |
    | <exCODIGOPRODUTO> | <exCODIGOBANDEIRA> |<exTIPOPLATAFORMA> | <exDESCRICAO> |
    Quando realizo um POST deste produto
    Então a requisição é um sucesso com status 201
    E o response apresenta todos os campos referente o produto preenchidos corretamente

    Exemplos: 
      | CT | CENARIO                                      |exCODIGOPRODUTO  | exCODIGOBANDEIRA    |exTIPOPLATAFORMA | exDESCRICAO        |
      | 01 | Incluir Produto - Crédito - código 1 dígito  | 001             |       007           |CREDITO          | Produto CT 1       |
      | 02 | Incluir Produto - Débito - código 2 dígitos  | 002             |       007           |DEBITO           | Produto CT 2       |
      | 03 | Incluir Produto - Crédito - código 3 dígitos | 003             |       007           |DEBITO           | Produto CT 3       |
