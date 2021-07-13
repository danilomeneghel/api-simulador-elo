package br.com.elo.domain.dto.request;

import br.com.elo.domain.CvnCartao;
import br.com.elo.domain.TipoCartao;
import br.com.elo.domain.TokenCartao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartaoRequestCriteriaDTO implements Serializable {

    private String id;

    private Integer codigoBandeira;

    private Integer codigoEmissor;

    private TipoCartao tipoCartao;

    private String nomeCartao;

    private String pan;

    private String pin;

    private Integer cardSequenceNumber;

    private String cve2;

    private String dataExpiracao;

    private TokenCartao tokenCartao;

    private String applicationIdentifier;

    private String cardAppInterchangeProfile;

    private String issuerDiscrData;

    private Integer cardAppTransactionCounter;

    private String track1;

    private String track2;

    private String track2EquivalentData;

    private CvnCartao cvnCartao;

    private String cvr;

    private String dkiKdi;

}
