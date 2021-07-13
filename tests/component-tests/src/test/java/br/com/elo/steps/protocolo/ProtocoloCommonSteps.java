package br.com.elo.steps.protocolo;


import br.com.elo.dto.ProtocoloRequestDTO;
import br.com.elo.fixture.ProtocoloFixture;
import cucumber.api.java.pt.Dado;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ProtocoloCommonSteps {

    @Autowired
    private ProtocoloFixture protocoloFixture;


    @Dado("que possua um protocolo cadastrado:")
    public void que_possua_um_protocolo_cadastrado(List<ProtocoloRequestDTO> protocoloRequestDTOList) {

        for (ProtocoloRequestDTO protocoloRequestDTO : protocoloRequestDTOList) {
            protocoloFixture.incluirProtocolo(protocoloRequestDTO);
        }
    }


}
