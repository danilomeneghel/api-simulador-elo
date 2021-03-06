package br.com.elo.service;

import br.com.elo.common.exception.ObjectNotFoundException;
import br.com.elo.common.exception.ParametroInvalidoException;
import br.com.elo.common.utils.converters.MapConverters;
import br.com.elo.domain.*;
import br.com.elo.domain.dto.request.LayoutBitsProtocoloRequestDTO;
import br.com.elo.domain.dto.request.ProtocoloRequestCriteriaDTO;
import br.com.elo.domain.dto.request.ProtocoloRequestDTO;
import br.com.elo.domain.dto.response.ProtocoloResponseDTO;
import br.com.elo.model.Protocolo;
import br.com.elo.repository.ProtocoloRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ProtocoloService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProtocoloRepository repository;

    @Autowired
    private SequencesGeneratorService sequenceGenerator;

    public ProtocoloService(ModelMapper modelMapper, SequencesGeneratorService sequenceGenerator, ProtocoloRepository repository) {
        this.modelMapper = modelMapper;
        this.sequenceGenerator = sequenceGenerator;
        this.repository = repository;
    }

    public ProtocoloResponseDTO save(ProtocoloRequestDTO dto) {
        Protocolo protocolo = modelMapper.map(dto, Protocolo.class);

        //Valida os bits da mensagem.

        if(dto.getBitsProtocolo() != null)
            validarLayoutDeBitsProtocolo(dto.getBitsProtocolo());

        protocolo.setDataCriacao(LocalDateTime.now());
        protocolo.setProtocoloSequence(sequenceGenerator.generateSequence(Protocolo.SEQUENCE_NAME));
        protocolo.setSchemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion());
        protocolo = repository.save(protocolo);
        return modelMapper.map(protocolo, ProtocoloResponseDTO.class);
    }

    public ProtocoloResponseDTO findById(String id) {
        Protocolo protocolo = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(MensagensRetorno.PROTOCOLO_NAO_LOCALIZADO.getDescricao()));
        return modelMapper.map(protocolo, ProtocoloResponseDTO.class);
    }

    public ProtocoloResponseDTO findByProtocoloSequence(Long protocoloSequence) {

        Protocolo protocolo = repository.findByProtocoloSequence(protocoloSequence);

        if(protocolo == null)
            throw new ObjectNotFoundException(MensagensRetorno.PROTOCOLO_NAO_LOCALIZADO.getDescricao());

        return modelMapper.map(protocolo, ProtocoloResponseDTO.class);
    }

    public ProtocoloResponseDTO update(Long protocoloSequence, ProtocoloRequestDTO dto) {
        Protocolo protocolo = modelMapper.map(dto, Protocolo.class);

        if(protocoloSequence == null || protocoloSequence == 0) {
            throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao());
        }

        Protocolo protocoloAux =  repository.findByProtocoloSequence(protocoloSequence);
        if(protocoloAux == null) {
            throw new ObjectNotFoundException(MensagensRetorno.PROTOCOLO_NAO_LOCALIZADO.getDescricao());
        }

        //Valida os bits da mensagem.
        if(dto.getBitsProtocolo() != null)
            validarLayoutDeBitsProtocolo(dto.getBitsProtocolo());

        protocolo.setProtocoloSequence(protocoloAux.getProtocoloSequence());
        protocolo.setId(protocoloAux.getId());
        protocolo.setDataAlteracao(LocalDateTime.now());
        protocolo.setDataCriacao(protocoloAux.getDataCriacao());
        protocolo.setSchemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion());
        protocolo = repository.save(protocolo);
        return modelMapper.map(protocolo, ProtocoloResponseDTO.class);

    }

    public List<ProtocoloResponseDTO> findAll(ProtocoloRequestCriteriaDTO dto) {
        //Pageable paging = PageRequest.of(page, size);


        Protocolo protocolo = modelMapper.map(dto, Protocolo.class);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);

        Example<Protocolo> protocoloExample = Example.of(protocolo, matcher);

        List<Protocolo>  protocoloList = repository.findAll(protocoloExample);
        List<ProtocoloResponseDTO>  protocoloResponseList = modelMapper.map(protocoloList, new TypeToken<List<ProtocoloResponseDTO>>() {}.getType());

        return  protocoloResponseList;
    }

    /***
     * M??todo para validar o layout dos bits do protocolo
     * @param bitsProtocolo lista de bits
     */
    void validarLayoutDeBitsProtocolo(List<LayoutBitsProtocoloRequestDTO> bitsProtocolo)
    {
        for (LayoutBitsProtocoloRequestDTO bit : bitsProtocolo) {

            //Verifica campo tamanho obrig??torio para o tipo tamanho FIXO.
            if(bit.getTipoTamCampo().equals(TipoTamanho.FIXO) && (bit.getTam() == null || bit.getTam().equals(0))) {
                throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao() + " " + "Tamanho obrig??torio para tipo de tamanho FIXO");
            }

            //Verifica campo tam min ou tam max, n??o devem ser informados para o tipo tamanho FIXO.
            if((bit.getTipoTamCampo().equals(TipoTamanho.FIXO)) && ((bit.getTamMinimo() != null) || (bit.getTamMaximo() != null )))
                throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "Tamanho Min ou Max, n??o deve ser informado para tipo de tamanho FIXO");

            //Verifica campo tamanho, n??o deve ser informado para os tipos tamanho LLVAR OU LLLVAR.
            if((bit.getTipoTamCampo().equals(TipoTamanho.LLVAR) || bit.getTipoTamCampo().equals(TipoTamanho.LLLVAR))  && (bit.getTam() != null)) {
                throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "Tamanho n??o deve ser informado para tipo de tamanho LLVAR ou LLLVAR");
            }

            //Verifica campo TamMinimo e TamMaximo, s??o obrigat??rios para os tipos tamanho LLVAR OU LLLVAR.
            if((bit.getTipoTamCampo().equals(TipoTamanho.LLVAR) || bit.getTipoTamCampo().equals(TipoTamanho.LLLVAR))
                    && (bit.getTamMinimo() == null || bit.getTamMaximo() == null)){
                throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "Tamanho Min e Max obrigat??rios para tipo de tamanho LLVAR ou LLLVAR");
            }

            //Verifica campo encode tamanho, obrigat??rio para os tipos tamanho LLVAR OU LLLVAR.
            if((bit.getTipoTamCampo().equals(TipoTamanho.LLVAR) || bit.getTipoTamCampo().equals(TipoTamanho.LLLVAR))
                    && bit.getEncodeTamCampo() == null){
                throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "A encapsula????o do tamanho ?? obrigat??ria para tipo de tamanho LLVAR ou LLLVAR");
            }

            //Verifica campo  bcdFillerNibbleValue, obrigat??rio para os tipos tamanho LLVAR OU LLLVAR e encode de dados Numero BCD.
            if(((bit.getTipoTamCampo().equals(TipoTamanho.LLVAR) || bit.getTipoTamCampo().equals(TipoTamanho.LLLVAR))
                    && bit.getEncodeDadosCampo().equals(EncodeDadosCampo.NROBCD))
                    && (bit.getBcdFillerNibbleValue() == null || bit.getBcdFillerNibbleValue().isEmpty())){
                throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "O campo bcdFillerNibbleValue ?? obrigat??rio para tipo de tamanho LLVAR ou LLLVAR e formata????o de dados N??mero BCD");
            }

            //Verifica campo  bcdFillerNibblePosition, obrigat??rio para os tipos tamanho LLVAR OU LLLVAR e encode de dados Numero BCD.
            if(((bit.getTipoTamCampo().equals(TipoTamanho.LLVAR) || bit.getTipoTamCampo().equals(TipoTamanho.LLLVAR))
                    && bit.getEncodeDadosCampo().equals(EncodeDadosCampo.NROBCD))
                    && bit.getBcdFillerNibblePosition() == null){
                throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "O campo bcdFillerNibblePosition ?? obrigat??rio para tipo de tamanho LLVAR ou LLLVAR e formata????o de dados N??mero BCD");
            }

            //Verifica campo expandirDadosBinarios, somente pode ser usado para o encode de dados bin??rios.
            if(!bit.getEncodeDadosCampo().equals(EncodeDadosCampo.DADOSBINARIOS)  && bit.getExpandirDadosBinarios() != null){
                throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "A op????o expandir dados bin??rios s?? esta disponivel para o tipo de dados bin??rios");
            }

            //Verifica lista descricaoDadosCampo, somente pode ser usada para as formatacoes de dados CODPAIS,CODMOEDA,SRVCODE e MCC
            if((bit.getFormatoDadosCampo() != null &&
                    !bit.getFormatoDadosCampo().equals(FormatacaoDadosCampo.CODPAIS) &&
                    !bit.getFormatoDadosCampo().equals(FormatacaoDadosCampo.CODMOEDA) &&
                    !bit.getFormatoDadosCampo().equals(FormatacaoDadosCampo.SRVCODE) &&
                    !bit.getFormatoDadosCampo().equals(FormatacaoDadosCampo.MCC)) &&
                    (bit.getDescricaoDadosCampo() != null && !bit.getDescricaoDadosCampo().isEmpty())){

                throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "A op????o descricaoDadosCampo s?? esta disponivel para as formata????es de dados CODPAIS,CODMOEDA,SRVCODE e MCC");
            }

            //Verifica campo formatacaoDataHora, somente pode ser usado para as formatacao de dados DATAHORA
            if((bit.getFormatoDadosCampo() != null &&
                    !bit.getFormatoDadosCampo().equals(FormatacaoDadosCampo.DATAHORA)) &&
                    (bit.getFormatacaoDataHora() != null && !bit.getFormatacaoDataHora().isEmpty())){

                throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "A op????o formatacaoDataHora s?? esta disponivel para a formatacao de dados DATAHORA");
            }
        }
    }
}
