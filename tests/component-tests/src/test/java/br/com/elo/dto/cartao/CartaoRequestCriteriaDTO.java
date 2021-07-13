package br.com.elo.dto.cartao;

import br.com.elo.domain.CvnCartao;
import br.com.elo.domain.TipoCartao;
import br.com.elo.domain.TokenCartao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @Size(max = 30, message = "Tamanho máximo do nome do cartão deve ser 30 caracteres")
    private String nomeCartao;

    @NotBlank(message = "O PAN não pode ser em branco e nem nulo")
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
