package br.com.elo.domain.dto.request;

import br.com.elo.domain.CvnCartao;
import br.com.elo.domain.TipoCartao;
import br.com.elo.domain.TokenCartao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartaoRequestDTO implements Serializable {


    @NotNull(message = "O código Bandeira não pode ser nulo")
    @Max(value = 999, message = "Tamanho máximo do valor deve ser 3 caracteres")
    @Min(value = 1, message = "O valor deve ser maior que zero" )
    private Integer codigoBandeira;

    @NotNull(message = "O código Emissor não pode ser nulo")
    @Max(value = 999, message = "Tamanho máximo do valor deve ser 3 caracteres")
    @Min(value = 1, message = "O valor deve ser maior que zero" )
    private Integer codigoEmissor;

    @NotNull(message = "O tipo de cartão do não pode ser nulo")
    private TipoCartao tipoCartao;

    @NotBlank(message = "O nome do cartão não pode ser em branco e nem nulo")
    @Size(max = 30, message = "Tamanho máximo do nome do cartão deve ser 30 caracteres")
    private String nomeCartao;

    @NotBlank(message = "O PAN não pode ser em branco e nem nulo")
    @Size(min = 8, message = "Tamanho mínimo do PAN deve ser 8 caracteres")
    @Size(max = 19, message = "Tamanho máximo do PAN deve ser 19 caracteres")
    private String pan;

    @NotBlank(message = "O PIN não pode ser em branco e nem nulo")
    @Size(min = 4, message = "Tamanho mínimo do PIN deve ser 4 caracteres")
    @Size(max = 6, message = "Tamanho máximo do PIN deve ser 6 caracteres")
    private String pin;

    @NotNull(message = "O Card Sequence Number não pode ser nulo")
    @Max(value = 999, message = "Tamanho máximo do valor deve ser 3 caracteres")
    private Integer cardSequenceNumber;

    @NotBlank(message = "O CVE2 não pode ser em branco e nem nulo")
    @Size(min = 3, message = "Tamanho mínimo do CVE2 deve ser 3 caracteres")
    @Size(max = 4, message = "Tamanho máximo do CVE2 deve ser 4 caracteres")
    private String cve2;

    @NotBlank(message = "A data de expiração não pode ser em branco e nem nulo")
    private String dataExpiracao;

    @NotNull(message = "O token do cartão do não pode ser nulo")
    private TokenCartao tokenCartao;

    @NotNull(message = "O CVN do cartão do não pode ser nulo")
    private CvnCartao cvnCartao;

    @NotBlank(message = "O Application Identifier não pode ser em branco e nem nulo")
    @Size(max = 20, message = "Tamanho máximo do Application Identifier deve ser 20 caracteres")
    private String applicationIdentifier;

    @NotBlank(message = "O Card App Interchange Profile Data não pode ser em branco e nem nulo")
    @Size(max = 10, message = "Tamanho máximo do Card App Interchange Profile Data deve ser 10 caracteres")
    private String cardAppInterchangeProfile;

    @NotBlank(message = "O Issuer Discr Data não pode ser em branco e nem nulo")
    @Size(max = 50, message = "Tamanho máximo do Issuer Discr Data deve ser 50 caracteres")
    private String issuerDiscrData;

    @NotNull(message = "O Card App Transaction Counter não pode ser nulo")
    @Max(value = 999, message = "Tamanho máximo do valor deve ser 3 caracteres")
    private Integer cardAppTransactionCounter;

    @NotBlank(message = "O Track1 não pode ser em branco e nem nulo")
    @Size(max = 80, message = "Tamanho máximo do Track1 deve ser 80 caracteres")
    private String track1;

    @NotBlank(message = "O Track2 não pode ser em branco e nem nulo")
    @Size(max = 80, message = "Tamanho máximo do Track2 deve ser 80 caracteres")
    private String track2;

    @NotBlank(message = "O Track2 Equivalent Data não pode ser em branco e nem nulo")
    @Size(max = 80, message = "Tamanho máximo do Track2 Equivalent Data deve ser 80 caracteres")
    private String track2EquivalentData;

    @NotBlank(message = "O CVR não pode ser em branco e nem nulo")
    @Size(max = 15, message = "Tamanho máximo do CVR deve ser 15 caracteres")
    private String cvr;

    @NotBlank(message = "O Dki/Kdi não pode ser em branco e nem nulo")
    @Size(max = 5, message = "Tamanho máximo do Dki/Kdi deve ser 5 caracteres")
    private String dkiKdi;

}
