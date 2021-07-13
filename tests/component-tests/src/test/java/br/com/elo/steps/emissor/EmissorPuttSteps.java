package br.com.elo.steps.emissor;

import br.com.elo.domain.Plataforma;
import br.com.elo.dto.emissor.EmissorRequestDTO;
import br.com.elo.dto.emissor.EmissorResponseDTO;
import br.com.elo.fixture.emissor.EmissorFixture;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class EmissorPuttSteps {

    @Autowired
    private EmissorFixture emissorFixture;

    EmissorResponseDTO emissorCadastrado;
    EmissorRequestDTO emissorAlterado;
    EmissorResponseDTO emissorResponseDTO;

    @Quando("^realizo uma alteração do nome do Emissor$")
    public void realizo_uma_alteração_do_nome_do_Emissor(){
        emissorCadastrado = this.emissorFixture.getResultado().getBody();
        emissorAlterado = EmissorRequestDTO
                .builder()
                .codigoBandeira(emissorCadastrado.getCodigoBandeira())
                .codigoEmissor(emissorCadastrado.getCodigoEmissor())
                .plataforma(emissorCadastrado.getPlataforma())
                .nomeEmissor("descricao alterada teste")
                .codigoProcessadora(emissorCadastrado.getCodigoProcessadora())
                .plataforma(emissorCadastrado.getPlataforma())
                .build();
        emissorResponseDTO = emissorFixture.atualizarEmissor(emissorAlterado, emissorCadastrado.getCodigoEmissor(), emissorCadastrado.getCodigoBandeira()).getBody();

    }

    @Quando("^realizo uma alteração do Código da Processadora do Emissor$")
    public void realizo_uma_alteração_do_Código_da_Processadora_do_Emissor() throws Exception {
        emissorCadastrado = this.emissorFixture.getResultado().getBody();
        emissorAlterado = EmissorRequestDTO
                .builder()
                .codigoBandeira(emissorCadastrado.getCodigoBandeira())
                .codigoEmissor(emissorCadastrado.getCodigoEmissor())
                .plataforma(emissorCadastrado.getPlataforma())
                .nomeEmissor(emissorCadastrado.getNomeEmissor())
                .codigoProcessadora(2)
                .build();
      //  emissorResponseDTO = emissorFixture.atualizarEmissor(emissorAlterado, emissorCadastrado.getCodigo()).getBody();
        emissorResponseDTO = emissorFixture.atualizarEmissor(emissorAlterado, emissorCadastrado.getCodigoEmissor(), emissorCadastrado.getCodigoBandeira()).getBody();
    }

    @Quando("^realizo uma alteração de Código da Bandeira do Emissor$")
    public void realizo_uma_alteração_de_Código_da_Bandeira_do_Emissor(){
        emissorCadastrado = this.emissorFixture.getResultado().getBody();
        emissorAlterado = EmissorRequestDTO
                .builder()
                .codigoBandeira(emissorCadastrado.getCodigoBandeira())
                .codigoEmissor(emissorCadastrado.getCodigoEmissor())
                .nomeEmissor(emissorCadastrado.getNomeEmissor())
                .codigoProcessadora(emissorCadastrado.getCodigoProcessadora())
                .plataforma(emissorCadastrado.getPlataforma())
                .build();
     //   emissorResponseDTO = emissorFixture.atualizarEmissor(emissorAlterado, emissorCadastrado.getCodigoEmissor()).getBody();
        emissorResponseDTO = emissorFixture.atualizarEmissor(emissorAlterado, emissorCadastrado.getCodigoEmissor(), emissorCadastrado.getCodigoBandeira()).getBody();
    }

    @Quando("^realizo uma alteração de todos os dados do Emissor$")
    public void realizo_uma_alteração_de_todos_os_dados_do_Emissor(){
        emissorCadastrado = this.emissorFixture.getResultado().getBody();
        emissorAlterado = EmissorRequestDTO
                .builder()
                .codigoBandeira(emissorCadastrado.getCodigoBandeira())
                .codigoEmissor(emissorCadastrado.getCodigoEmissor())
                .nomeEmissor("descricao alterada teste")
                .codigoProcessadora(2)
                .plataforma(Plataforma.CREDITO)
                .build();
        //emissorResponseDTO = emissorFixture.atualizarEmissor(emissorAlterado, emissorCadastrado.getCodigo()).getBody();
        emissorResponseDTO = emissorFixture.atualizarEmissor(emissorAlterado, emissorCadastrado.getCodigoEmissor(), emissorCadastrado.getCodigoBandeira()).getBody();
    }

    @Então("^o response da alteração de Emissor exibe a data de atualização corretamente$")
    public void o_response_da_alteração_de_Emissor_exibe_a_data_de_atualização_corretamente(){
        assertThat(emissorResponseDTO.getDataCriacao()).isNotNull();

    }

}
