package br.com.elo.steps.credenciador;

import br.com.elo.domain.Status;
import br.com.elo.dto.CredenciadorRequestDTO;
import br.com.elo.dto.CredenciadorResponseDTO;
import br.com.elo.fixture.CredenciadorFixture;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CredenciadorPutSteps {

    @Autowired
    private CredenciadorFixture credenciadorFixture;

    CredenciadorResponseDTO credenciadorCadastrado;
    CredenciadorRequestDTO credenciadorAlterado;
    CredenciadorResponseDTO credenciadorResponse;

//    @Quando("realizo um put com o codigoCredenciador alterado")
//    public void realizo_um_put_com_o_codigoCredenciador_alterado() {
//
//        credenciadorCadastrado = this.credenciadorFixture.getResultado().getBody();
//        credenciadorAlterado = CredenciadorRequestDTO.builder().credenciadorCodigo(1L).nome(credenciadorCadastrado.getNome()).status(StatusEnum.get(credenciadorCadastrado.getStatus().getId())).build();
//        credenciadorResponse = credenciadorFixture.atualizaCredenciador(credenciadorAlterado, credenciadorCadastrado.getCodigo()).getBody();
//    }

    @Quando("realizo um put com o nome do credenciador alterado")
    public void realizo_um_put_com_o_nome_do_credenciador_alterado() {

        credenciadorCadastrado = this.credenciadorFixture.getResultado().getBody();
        credenciadorAlterado = CredenciadorRequestDTO.builder().credenciadorCodigo(credenciadorCadastrado.getCredenciadorCodigo()).nome("nome alterado teste").status(Status.get(credenciadorCadastrado.getStatus().getId())).build();
        credenciadorResponse = credenciadorFixture.atualizaCredenciador(credenciadorAlterado, credenciadorCadastrado.getCredenciadorCodigo()).getBody();
    }

    @Entao("o response da alteração do Credenciador exibe os dados alterados corretamente")
    public void o_response_da_alteração_do_Credenciador_exibe_os_dados_alterados_corretamente() {

        assertNotNull(credenciadorResponse.getId());
        //assertNotNull(credenciadorResponse.getCodigo());
        assertNotNull(credenciadorResponse.getDataCriacao());
        assertNotNull(credenciadorResponse.getDataAlteracao());
        assertEquals(credenciadorResponse.getCredenciadorCodigo(), credenciadorAlterado.getCredenciadorCodigo());
        assertEquals(credenciadorResponse.getNome(), credenciadorAlterado.getNome());
    }
}