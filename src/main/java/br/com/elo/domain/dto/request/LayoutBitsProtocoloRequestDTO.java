package br.com.elo.domain.dto.request;

import br.com.elo.common.utils.annotations.EnumNamePattern;
import br.com.elo.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LayoutBitsProtocoloRequestDTO implements Serializable {

    @NotNull(message = "O numero do bit não pode ser nulo")
    @Min(value = 2, message = "O valor minimo para o numero de bit deve ser 2" )
    @Max(value = 256, message = "O valor máximo para o numero de bit é 256")
    private Integer numeroDoBit;

    @NotBlank(message = "A descrição não pode ser em branco e nem nulo")
    @Size(max = 80, message = "Tamanho máximo da descriação deve ser 80 caracteres")
    private String descricao;

    @NotNull(message = "O tipo do tamanho do campo não pode ser em branco e nem nulo")
    private TipoTamanho tipoTamCampo;

    private EncodeTamanho encodeTamCampo;

    @NotNull(message = "O Encode dos dados do campo não pode ser em branco e nem nulo")
    private EncodeDadosCampo encodeDadosCampo;

    //Somente usado para o campo tamanho fixo
    private Integer tam;

    //Somente usado para campos com tamanho variável (LLVAR OU LLLVAR)
    private Integer tamMinimo;

    //Somente usado para campos com tamanho variável (LLVAR OU LLLVAR)
    private Integer tamMaximo;

    @NotNull(message = "O alinhamento dos dados do campo não pode ser em branco e nem nulo")
    private Alinhamento alinhamento;

    @NotNull(message = "O caracterPreenchimento dos dados não pode ser nulo")
    @Size(max = 1, message = "Tamanho máximo para o preenchimento deve ser 1 caracter")
    private String caracterPreenchimento;

    //Opcional quando utilizado o encodeDadosCampo DadosBinarios
    private Boolean expandirDadosBinarios;

    //Opcional quando utilizado o encodeDadosCampo NumeroBCD (numero impar de digitos)
    private BCDFillerPosition bcdFillerNibblePosition;

    //Opcional quando utilizado o encodeDadosCampo NumeroBCD (numero impar de digitos)
    @Size(max = 1, message = "Tamanho máximo para o preenchimento de filler bcd deve ser 1 caracter")
    private String bcdFillerNibbleValue;

    private FormatacaoDadosCampo formatoDadosCampo;

    @Size(max = 30, message = "Tamanho máximo para a formatacaoDataHora deve ser 30 caracteres")
    private String formatacaoDataHora;

    //Somente utilizado para as formatacoes de dados CODPAIS,CODMOEDA,SRVCODE e MCC
    private List<DescricaoDadosRequestDTO> descricaoDadosCampo;

}
