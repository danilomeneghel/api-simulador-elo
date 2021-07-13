#language: pt
#author: Eduardo Oliveira
#versao: 1.0
@APIAmbiente @regression @dev
Funcionalidade: API - Alteração de Ambientes

  @cenarioPositivo
  Cenario: CT 01 - Alterar dados básicos do Ambiente
    Dado que eu possua um Ambiente
    Quando eu realizo o PUT deste ambiente alterando os dados de Descrição, Tipo, Versão, Status, Chave de PIN e Chave CAVV
    Então o status code é 200
    E o response retorna os campos do Ambiente alterado
    E os dados do ambiente são alterados na base de dados

  @cenarioPositivo
  Cenario: CT 02 - Alterar Ambiente para incluir Porta Cliente sem porta
    Dado que possua um Ambiente criado sem nenhuma porta cadastrada
    Quando eu realizo o PUT deste ambiente para incluir uma nova Porta Cliente
    Então o status code é 200
    E o response retorna os campos do Ambiente alterado
    E os dados do ambiente são alterados na base de dados

  @cenarioPositivo
  Cenario: CT 03 - Alterar Ambiente para incluir Porta Servidor sem porta
    Dado que possua um Ambiente criado sem nenhuma porta cadastrada
    Quando eu realizo o PUT deste ambiente para incluir uma nova Porta Servidor
    Então o status code é 200
    E o response retorna os campos do Ambiente alterado
    E os dados do ambiente são alterados na base de dados

  @cenarioPositivo
  Cenario: CT 04 - Alterar Ambiente para incluir Porta Cliente com porta
    Dado que possua um Ambiente criado com Porta Cliente cadastrada
    Quando eu realizo o PUT deste ambiente para incluir uma segunda Porta Cliente
    Então o status code é 200
    E o response retorna os campos do Ambiente alterado
    E os dados do ambiente são alterados na base de dados

  @cenarioPositivo
  Cenario: CT 05 - Alterar Ambiente para incluir Porta Servidor com porta
    Dado que possua um Ambiente criado com Porta Servidor cadastrada
    Quando eu realizo o PUT deste ambiente para incluir uma segunda Porta Servidor
    Então o status code é 200
    E o response retorna os campos do Ambiente alterado
    E os dados do ambiente são alterados na base de dados

  @cenarioPositivo
  Cenario: CT 06 - Alterar Ambiente para remover Porta Cliente
    Dado que possua um Ambiente criado com Porta Cliente cadastrada
    Quando eu realizo o PUT deste ambiente para remover uma nova Porta Cliente
    Então o status code é 200
    E o response retorna os campos do Ambiente alterado
    E os dados do ambiente são alterados na base de dados

  @cenarioPositivo
  Cenario: CT 07 - Alterar Ambiente para remover Porta Servidor
    Dado que possua um Ambiente criado com Porta Servidor cadastrada
    Quando eu realizo o PUT deste ambiente para remover uma nova Porta Servidor
    Então o status code é 200
    E o response retorna os campos do Ambiente alterado
    E os dados do ambiente são alterados na base de dados

  @cenarioPositivo
  Cenario: CT 08 - Alterar Ambiente para alterar dados das Portas Cliente e Servidor
    Dado que possua um Ambiente criado com Porta Cliente e Porta Servidor cadastrada
    Quando eu realizo o PUT deste ambiente para editar todos os dados de uma Porta Cliente e de uma Porta Servidor
    Então o status code é 200
    E o response retorna os campos do Ambiente alterado
    E os dados do ambiente são alterados na base de dados

#  @cenarioPositivo
#  Cenario: CT 09 - Alterar Ambiente para inativar Porta Cliente
#    Dado que possua um Ambiente criado com Porta Cliente com status ATIVO
#    Quando eu realizo o PUT deste ambiente alterando o status da Porta Cliente para INATIVO
#    Então o status code é 200
#    E o response retorna os campos do Ambiente alterado
#    E os dados do ambiente são alterados na base de dados
#
#  @cenarioPositivo
#  Cenario: CT 10 - Alterar Ambiente para inativar Porta Servidor
#    Dado que possua um Ambiente criado com Porta Servidor com status ATIVO
#    Quando eu realizo o PUT deste ambiente alterando o status da Porta Servidor para INATIVO
#    Então o status code é 200
#    E o response retorna os campos do Ambiente alterado
#    E os dados do ambiente são alterados na base de dados
