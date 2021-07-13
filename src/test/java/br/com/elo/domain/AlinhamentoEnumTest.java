package br.com.elo.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import javax.validation.Validator;
import static org.assertj.core.api.Assertions.assertThat;


public class AlinhamentoEnumTest {


    @Test
    @DisplayName("Testa alinhamento Id")
    public void testAlinhamento() {

        //Id valido
        Alinhamento alinhamento = Alinhamento.get(1);
        assertThat(alinhamento).isEqualTo(Alinhamento.ESQUERDA);

        //Id invalido
        alinhamento = Alinhamento.get(3);
        assertThat(alinhamento).isEqualTo(null);

    }

}