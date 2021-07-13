package br.com.elo.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class BCDFillerPositionEnumTest {

    
    @Test
    @DisplayName("Testa BCDFillerPosition Id")
    public void testBCDFillerPosition() {

        //Id valido
        BCDFillerPosition bcdFillerPosition = BCDFillerPosition.get(1);
        assertThat(bcdFillerPosition).isEqualTo(BCDFillerPosition.INICIO);

        //Id invalido
        bcdFillerPosition = BCDFillerPosition.get(3);
        assertThat(bcdFillerPosition).isEqualTo(null);

    }

}