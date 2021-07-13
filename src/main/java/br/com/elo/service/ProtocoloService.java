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
     * Método para validar o layout dos bits do protocolo
     * @param bitsProtocolo lista de bits
     */
    void validarLayoutDeBitsProtocolo(List<LayoutBitsProtocoloRequestDTO> bitsProtocolo)
    {
        for (LayoutBitsProtocoloRequestDTO bit : bitsProtocolo) {

            //Verifica campo tamanho obrigátorio para o tipo tamanho FIXO.
            if(bit.getTipoTamCampo().equals(TipoTamanho.FIXO) && (bit.getTam() == null || bit.getTam().equals(0))) {
                throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao() + " " + "Tamanho obrigátorio para tipo de tamanho FIXO");
            }

            //Verifica campo tam min ou tam max, não devem ser informados para o tipo tamanho FIXO.
            if((bit.getTipoTamCampo().equals(TipoTamanho.FIXO)) && ((bit.getTamMinimo() != null) || (bit.getTamMaximo() != null )))
                throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "Tamanho Min ou Max, não deve ser informado para tipo de tamanho FIXO");

            //Verifica campo tamanho, não deve ser informado para os tipos tamanho LLVAR OU LLLVAR.
            if((bit.getTipoTamCampo().equals(TipoTamanho.LLVAR) || bit.getTipoTamCampo().equals(TipoTamanho.LLLVAR))  && (bit.getTam() != null)) {
                throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "Tamanho não deve ser informado para tipo de tamanho LLVAR ou LLLVAR");
            }

            //Verifica campo TamMinimo e TamMaximo, são obrigatórios para os tipos tamanho LLVAR OU LLLVAR.
            if((bit.getTipoTamCampo().equals(TipoTamanho.LLVAR) || bit.getTipoTamCampo().equals(TipoTamanho.LLLVAR))
                    && (bit.getTamMinimo() == null || bit.getTamMaximo() == null)){
                throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "Tamanho Min e Max obrigatórios para tipo de tamanho LLVAR ou LLLVAR");
            }

            //Verifica campo encode tamanho, obrigatório para os tipos tamanho LLVAR OU LLLVAR.
            if((bit.getTipoTamCampo().equals(TipoTamanho.LLVAR) || bit.getTipoTamCampo().equals(TipoTamanho.LLLVAR))
                    && bit.getEncodeTamCampo() == null){
                throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "A encapsulação do tamanho é obrigatória para tipo de tamanho LLVAR ou LLLVAR");
            }

            //Verifica campo  bcdFillerNibbleValue, obrigatório para os tipos tamanho LLVAR OU LLLVAR e encode de dados Numero BCD.
            if(((bit.getTipoTamCampo().equals(TipoTamanho.LLVAR) || bit.getTipoTamCampo().equals(TipoTamanho.LLLVAR))
                    && bit.getEncodeDadosCampo().equals(EncodeDadosCampo.NROBCD))
                    && (bit.getBcdFillerNibbleValue() == null || bit.getBcdFillerNibbleValue().isEmpty())){
                throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "O campo bcdFillerNibbleValue é obrigatório para tipo de tamanho LLVAR ou LLLVAR e formatação de dados Número BCD");
            }

            //Verifica campo  bcdFillerNibblePosition, obrigatório para os tipos tamanho LLVAR OU LLLVAR e encode de dados Numero BCD.
            if(((bit.getTipoTamCampo().equals(TipoTamanho.LLVAR) || bit.getTipoTamCampo().equals(TipoTamanho.LLLVAR))
                    && bit.getEncodeDadosCampo().equals(EncodeDadosCampo.NROBCD))
                    && bit.getBcdFillerNibblePosition() == null){
                throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "O campo bcdFillerNibblePosition é obrigatório para tipo de tamanho LLVAR ou LLLVAR e formatação de dados Número BCD");
            }

            //Verifica campo expandirDadosBinarios, somente pode ser usado para o encode de dados binários.
            if(!bit.getEncodeDadosCampo().equals(EncodeDadosCampo.DADOSBINARIOS)  && bit.getExpandirDadosBinarios() != null){
                throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "A opção expandir dados binários só esta disponivel para o tipo de dados binários");
            }

            //Verifica lista descricaoDadosCampo, somente pode ser usada para as formatacoes de dados CODPAIS,CODMOEDA,SRVCODE e MCC
            if((bit.getFormatoDadosCampo() != null &&
                    !bit.getFormatoDadosCampo().equals(FormatacaoDadosCampo.CODPAIS) &&
                    !bit.getFormatoDadosCampo().equals(FormatacaoDadosCampo.CODMOEDA) &&
                    !bit.getFormatoDadosCampo().equals(FormatacaoDadosCampo.SRVCODE) &&
                    !bit.getFormatoDadosCampo().equals(FormatacaoDadosCampo.MCC)) &&
                    (bit.getDescricaoDadosCampo() != null && !bit.getDescricaoDadosCampo().isEmpty())){

                throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "A opção descricaoDadosCampo só esta disponivel para as formatações de dados CODPAIS,CODMOEDA,SRVCODE e MCC");
            }

            //Verifica campo formatacaoDataHora, somente pode ser usado para as formatacao de dados DATAHORA
            if((bit.getFormatoDadosCampo() != null &&
                    !bit.getFormatoDadosCampo().equals(FormatacaoDadosCampo.DATAHORA)) &&
                    (bit.getFormatacaoDataHora() != null && !bit.getFormatacaoDataHora().isEmpty())){

                throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "A opção formatacaoDataHora só esta disponivel para a formatacao de dados DATAHORA");
            }
        }
    }
}
