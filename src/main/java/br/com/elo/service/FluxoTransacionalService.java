package br.com.elo.service;


import br.com.elo.common.exception.ObjectNotFoundException;
import br.com.elo.common.exception.ParametroInvalidoException;
import br.com.elo.domain.MensagensRetorno;
import br.com.elo.domain.SchemaVersion;
import br.com.elo.domain.dto.request.FluxoTransacionalRequestCriteriaDTO;
import br.com.elo.domain.dto.request.FluxoTransacionalRequestDTO;
import br.com.elo.domain.dto.response.FluxoTransacionalResponseDTO;
import br.com.elo.model.FluxoTransacional;
import br.com.elo.model.MensagemISO8583;
import br.com.elo.repository.FluxoTransacionalRepository;
import br.com.elo.repository.MensagemISO8583Repository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class FluxoTransacionalService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FluxoTransacionalRepository repository;

    @Autowired
    private MensagemISO8583Repository mensagemISO8583Repository;

    @Autowired
    private SequencesGeneratorService sequenceGenerator;

    public FluxoTransacionalResponseDTO save(FluxoTransacionalRequestDTO dto) {

        if(dto.getMensagemSolicitacaoPernaUmSequence() != null && !mensagemISO8583Repository.existsByMensagemSequence(dto.getMensagemSolicitacaoPernaUmSequence())){
            throw new ParametroInvalidoException(MensagensRetorno.MENSAGEM_SOL_PERNA_UM_NAO_LOCALIZADA.getDescricao());
        }

        if(dto.getMensagemSolicitacaoPernaDoisSequence() != null && !mensagemISO8583Repository.existsByMensagemSequence(dto.getMensagemSolicitacaoPernaDoisSequence())){
            throw new ParametroInvalidoException(MensagensRetorno.MENSAGEM_SOL_PERNA_DOIS_NAO_LOCALIZADA.getDescricao());
        }

        if(dto.getMensagemRespostaPernaTresSequence() != null && !mensagemISO8583Repository.existsByMensagemSequence(dto.getMensagemRespostaPernaTresSequence())){
            throw new ParametroInvalidoException(MensagensRetorno.MENSAGEM_RESP_PERNA_TRES_NAO_LOCALIZADA.getDescricao());
        }

        if(dto.getMensagemRespostaPernaQuatroSequence() != null && !mensagemISO8583Repository.existsByMensagemSequence(dto.getMensagemRespostaPernaQuatroSequence())){
            throw new ParametroInvalidoException(MensagensRetorno.MENSAGEM_RESP_PERNA_QUATRO_NAO_LOCALIZADA.getDescricao());
        }

        if(dto.getBitVinculacaoMensagensPernasUmQuatro() != null) {

            MensagemISO8583 mensagemISO8583 = mensagemISO8583Repository.findNroBitByMensagemSequence(dto.getMensagemSolicitacaoPernaUmSequence(), dto.getBitVinculacaoMensagensPernasUmQuatro());

            if(mensagemISO8583 == null || mensagemISO8583.getBitsMensagem() == null){
                throw new ParametroInvalidoException(MensagensRetorno.MENSAGEM_BIT_NAO_LOCALIZADO_FLUXO_UM_QUATRO.getDescricao());
            }

            mensagemISO8583 = mensagemISO8583Repository.findNroBitByMensagemSequence(dto.getMensagemRespostaPernaQuatroSequence(), dto.getBitVinculacaoMensagensPernasUmQuatro());

            if(mensagemISO8583 == null || mensagemISO8583.getBitsMensagem() == null){
                throw new ParametroInvalidoException(MensagensRetorno.MENSAGEM_BIT_NAO_LOCALIZADO_FLUXO_UM_QUATRO.getDescricao());
            }
        }

        if(dto.getBitVinculacaoMensagensPernasDoisTres() != null) {

            MensagemISO8583 mensagemISO8583 = mensagemISO8583Repository.findNroBitByMensagemSequence(dto.getMensagemSolicitacaoPernaDoisSequence(), dto.getBitVinculacaoMensagensPernasDoisTres());

            if(mensagemISO8583 == null || mensagemISO8583.getBitsMensagem() == null){
                throw new ParametroInvalidoException(MensagensRetorno.MENSAGEM_BIT_NAO_LOCALIZADO_FLUXO_DOIS_TRES.getDescricao());
            }

            mensagemISO8583 = mensagemISO8583Repository.findNroBitByMensagemSequence(dto.getMensagemRespostaPernaTresSequence(), dto.getBitVinculacaoMensagensPernasDoisTres());

            if(mensagemISO8583 == null || mensagemISO8583.getBitsMensagem() == null){
                throw new ParametroInvalidoException(MensagensRetorno.MENSAGEM_BIT_NAO_LOCALIZADO_FLUXO_DOIS_TRES.getDescricao());
            }
        }


        FluxoTransacional fluxoTransacional = modelMapper.map(dto, FluxoTransacional.class);
        fluxoTransacional.setDataCriacao(LocalDateTime.now());
        fluxoTransacional.setFluxoTransacionalSequence(sequenceGenerator.generateSequence(FluxoTransacional.SEQUENCE_NAME));
        fluxoTransacional.setSchemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion());
        fluxoTransacional = repository.save(fluxoTransacional);
        return modelMapper.map(fluxoTransacional, FluxoTransacionalResponseDTO.class);
    }

    public FluxoTransacionalResponseDTO update(Long fluxoTransacionalSequence, FluxoTransacionalRequestDTO dto) {

        if(fluxoTransacionalSequence == null || fluxoTransacionalSequence == 0) {
            throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao());
        }

        if(dto.getMensagemSolicitacaoPernaUmSequence() != null && !mensagemISO8583Repository.existsByMensagemSequence(dto.getMensagemSolicitacaoPernaUmSequence())){
            throw new ParametroInvalidoException(MensagensRetorno.MENSAGEM_SOL_PERNA_UM_NAO_LOCALIZADA.getDescricao());
        }

        if(dto.getMensagemSolicitacaoPernaDoisSequence() != null && !mensagemISO8583Repository.existsByMensagemSequence(dto.getMensagemSolicitacaoPernaDoisSequence())){
            throw new ParametroInvalidoException(MensagensRetorno.MENSAGEM_SOL_PERNA_DOIS_NAO_LOCALIZADA.getDescricao());
        }

        if(dto.getMensagemRespostaPernaTresSequence() != null && !mensagemISO8583Repository.existsByMensagemSequence(dto.getMensagemRespostaPernaTresSequence())){
            throw new ParametroInvalidoException(MensagensRetorno.MENSAGEM_RESP_PERNA_TRES_NAO_LOCALIZADA.getDescricao());
        }

        if(dto.getMensagemRespostaPernaQuatroSequence() != null && !mensagemISO8583Repository.existsByMensagemSequence(dto.getMensagemRespostaPernaQuatroSequence())){
            throw new ParametroInvalidoException(MensagensRetorno.MENSAGEM_RESP_PERNA_QUATRO_NAO_LOCALIZADA.getDescricao());
        }

        if(dto.getBitVinculacaoMensagensPernasUmQuatro() != null) {

            MensagemISO8583 mensagemISO8583 = mensagemISO8583Repository.findNroBitByMensagemSequence(dto.getMensagemSolicitacaoPernaUmSequence(), dto.getBitVinculacaoMensagensPernasUmQuatro());

            if(mensagemISO8583 == null || mensagemISO8583.getBitsMensagem() == null){
                throw new ParametroInvalidoException(MensagensRetorno.MENSAGEM_BIT_NAO_LOCALIZADO_FLUXO_UM_QUATRO.getDescricao());
            }

            mensagemISO8583 = mensagemISO8583Repository.findNroBitByMensagemSequence(dto.getMensagemRespostaPernaQuatroSequence(), dto.getBitVinculacaoMensagensPernasUmQuatro());

            if(mensagemISO8583 == null || mensagemISO8583.getBitsMensagem() == null){
                throw new ParametroInvalidoException(MensagensRetorno.MENSAGEM_BIT_NAO_LOCALIZADO_FLUXO_UM_QUATRO.getDescricao());
            }
        }

        if(dto.getBitVinculacaoMensagensPernasDoisTres() != null) {

            MensagemISO8583 mensagemISO8583 = mensagemISO8583Repository.findNroBitByMensagemSequence(dto.getMensagemSolicitacaoPernaDoisSequence(), dto.getBitVinculacaoMensagensPernasDoisTres());

            if(mensagemISO8583 == null || mensagemISO8583.getBitsMensagem() == null){
                throw new ParametroInvalidoException(MensagensRetorno.MENSAGEM_BIT_NAO_LOCALIZADO_FLUXO_DOIS_TRES.getDescricao());
            }

            mensagemISO8583 = mensagemISO8583Repository.findNroBitByMensagemSequence(dto.getMensagemRespostaPernaTresSequence(), dto.getBitVinculacaoMensagensPernasDoisTres());

            if(mensagemISO8583 == null || mensagemISO8583.getBitsMensagem() == null){
                throw new ParametroInvalidoException(MensagensRetorno.MENSAGEM_BIT_NAO_LOCALIZADO_FLUXO_DOIS_TRES.getDescricao());
            }
        }

        FluxoTransacional fluxoTransacionalAux =  repository.findByFluxoTransacionalSequence(fluxoTransacionalSequence);

        if(fluxoTransacionalAux == null) {
            throw new ObjectNotFoundException(MensagensRetorno.FLUXO_TRANSACIONAL_NAO_LOCALIZADO.getDescricao());
        }

        FluxoTransacional fluxoTransacional = modelMapper.map(dto, FluxoTransacional.class);
        fluxoTransacional.setId(fluxoTransacionalAux.getId());
        fluxoTransacional.setFluxoTransacionalSequence(fluxoTransacionalAux.getFluxoTransacionalSequence());
        fluxoTransacional.setDataCriacao(fluxoTransacionalAux.getDataCriacao());
        fluxoTransacional.setDataAlteracao(LocalDateTime.now());
        fluxoTransacional.setSchemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion());
        fluxoTransacional = repository.save(fluxoTransacional);
        return modelMapper.map(fluxoTransacional, FluxoTransacionalResponseDTO.class);
    }

    public FluxoTransacionalResponseDTO findById(String id) {
        FluxoTransacional fluxoTransacional = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(MensagensRetorno.FLUXO_TRANSACIONAL_NAO_LOCALIZADO.getDescricao()));
        return modelMapper.map(fluxoTransacional, FluxoTransacionalResponseDTO.class);
    }

    public FluxoTransacionalResponseDTO findByFluxoTransacionalSequence(Long fluxoTransacionalSequence) {

        FluxoTransacional fluxoTransacional = repository.findByFluxoTransacionalSequence(fluxoTransacionalSequence);

        if(fluxoTransacional == null)
            throw new ObjectNotFoundException(MensagensRetorno.FLUXO_TRANSACIONAL_NAO_LOCALIZADO.getDescricao());

        return modelMapper.map(fluxoTransacional, FluxoTransacionalResponseDTO.class);
    }

    public List<FluxoTransacionalResponseDTO> findAll(FluxoTransacionalRequestCriteriaDTO dto) {
        //Pageable paging = PageRequest.of(page, size);

        FluxoTransacional fluxoTransacional = modelMapper.map(dto, FluxoTransacional.class);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(fluxoTransacional, matcher);

        List<FluxoTransacionalResponseDTO> fluxoTransacionalList = modelMapper.map(repository.findAll(example), new TypeToken<List<FluxoTransacionalResponseDTO>>() {}.getType());

        return fluxoTransacionalList;
    }



}
