package br.com.elo.dto.cartao;

import br.com.elo.domain.CvnCartao;
import br.com.elo.domain.TipoCartao;
import br.com.elo.domain.TokenCartao;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartaoResponseDTO implements Serializable {

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataAlteracao;

}
