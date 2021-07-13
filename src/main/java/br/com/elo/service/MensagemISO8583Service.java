package br.com.elo.service;

import br.com.elo.common.exception.ObjectNotFoundException;
import br.com.elo.common.exception.ParametroInvalidoException;
import br.com.elo.domain.*;
import br.com.elo.domain.dto.request.MensagemISO8583RequestCriteriaDTO;
import br.com.elo.domain.dto.request.MensagemISO8583RequestDTO;
import br.com.elo.domain.dto.response.MensagemISO8583ResponseDTO;
import br.com.elo.model.BitMensagem;
import br.com.elo.model.LayoutBitsProtocolo;
import br.com.elo.model.MensagemISO8583;
import br.com.elo.model.Protocolo;
import br.com.elo.repository.MensagemISO8583Repository;
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
public class MensagemISO8583Service {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MensagemISO8583Repository repository;

    @Autowired
    private ProtocoloRepository protocoloRepository;

    @Autowired
    private SequencesGeneratorService sequenceGenerator;

    public MensagemISO8583ResponseDTO save(MensagemISO8583RequestDTO dto) {
        MensagemISO8583 mensagem = modelMapper.map(dto, MensagemISO8583.class);

        if(!protocoloRepository.existsByProtocoloSequence(mensagem.getProtocoloSequence()))
            throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao());

        if(mensagem.getBitsMensagem() != null) {

            validarPresencaBitsProtocolo(mensagem.getProtocoloSequence(), mensagem.getBitsMensagem());
            preencherDescricaoBitsMensagemProtocolo(mensagem.getBitsMensagem(), protocoloRepository.findByProtocoloSequence(mensagem.getProtocoloSequence()).getBitsProtocolo());
        }

        mensagem.setDataCriacao(LocalDateTime.now());
        mensagem.setMensagemSequence(sequenceGenerator.generateSequence(MensagemISO8583.SEQUENCE_NAME));
        mensagem = repository.save(mensagem);
        return modelMapper.map(mensagem, MensagemISO8583ResponseDTO.class);
    }

    public MensagemISO8583ResponseDTO findById(String id) {
        MensagemISO8583 mensagem = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(MensagensRetorno.MENSAGEM_NAO_LOCALIZADA.getDescricao()));
        return modelMapper.map(mensagem, MensagemISO8583ResponseDTO.class);
    }

    public MensagemISO8583ResponseDTO findByMensagemSequence(Long mensagemSequence) {

        MensagemISO8583 mensagem = repository.findByMensagemSequence(mensagemSequence);

        if(mensagem == null)
            throw new ObjectNotFoundException(MensagensRetorno.MENSAGEM_NAO_LOCALIZADA.getDescricao());

        return modelMapper.map(mensagem, MensagemISO8583ResponseDTO.class);
    }

    public MensagemISO8583ResponseDTO update(Long mensagemSequence, MensagemISO8583RequestDTO dto) {
        MensagemISO8583 mensagem = modelMapper.map(dto, MensagemISO8583.class);

        if(mensagemSequence == null || mensagemSequence == 0) {
            throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao());
        }

        if(!protocoloRepository.existsByProtocoloSequence(mensagem.getProtocoloSequence()))
            throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao());

        MensagemISO8583 mensagemAux =  repository.findByMensagemSequence(mensagemSequence);
        if(mensagemAux == null) {
            throw new ObjectNotFoundException(MensagensRetorno.MENSAGEM_NAO_LOCALIZADA.getDescricao());
        }


        if(mensagem.getBitsMensagem() != null) {

            validarPresencaBitsProtocolo(mensagem.getProtocoloSequence(), mensagem.getBitsMensagem());
            preencherDescricaoBitsMensagemProtocolo(mensagem.getBitsMensagem(), protocoloRepository.findByProtocoloSequence(mensagem.getProtocoloSequence()).getBitsProtocolo());
        }

        mensagem.setMensagemSequence(mensagemAux.getMensagemSequence());
        mensagem.setId(mensagemAux.getId());
        mensagem.setDataAlteracao(LocalDateTime.now());
        mensagem.setDataCriacao(mensagemAux.getDataCriacao());
        mensagem.setSchemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion());
        mensagem = repository.save(mensagem);
        return modelMapper.map(mensagem, MensagemISO8583ResponseDTO.class);

    }

    public List<MensagemISO8583ResponseDTO> findAll(MensagemISO8583RequestCriteriaDTO dto) {


        MensagemISO8583 mensagem = modelMapper.map(dto, MensagemISO8583.class);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);

        Example<MensagemISO8583> mensagemExample = Example.of(mensagem, matcher);

        List<MensagemISO8583>  mensagemList = repository.findAll(mensagemExample);

        return   modelMapper.map(mensagemList, new TypeToken<List<MensagemISO8583ResponseDTO>>() {}.getType());
    }


    /***
     * Método para validar se todos os bits da mensagem estao cadastrados no protocolo
     * @param protocoloCodigo
     * @param bitsMensagem
     */
    void validarPresencaBitsProtocolo(Long protocoloCodigo,List<BitMensagem> bitsMensagem)
    {
        for (BitMensagem bit : bitsMensagem) {

            if(!validarPresencaBitProtocolo(protocoloCodigo,bit.getNumeroDoBit()))
                throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao()+ " " + "Bit número "+bit.getNumeroDoBit()+" não cadastrado no protocolo");

        }
    }

    /***
     * Método para validar se um nrobit esta cadastrado no protocolo
     * @param protocoloCodigo codigo do protocolo
     * @param nroBit numero do bit
     * @return
     */
    boolean validarPresencaBitProtocolo(Long protocoloCodigo,Integer nroBit)
    {
        Protocolo protocolo = protocoloRepository.findNroBitByProtocoloSequence(protocoloCodigo,nroBit);
        return protocolo != null && protocolo.getBitsProtocolo() != null;
    }


    /***
     * Método para preencher os bits com a descricao de acordo com os bits do protocolo
     * @param bitsMensagem bits da mensagem
     * @param bitsProtocolo bits do protcolo
     */
    void preencherDescricaoBitsMensagemProtocolo(List<BitMensagem> bitsMensagem, List<LayoutBitsProtocolo> bitsProtocolo)
    {
        for (BitMensagem bit : bitsMensagem) {

            LayoutBitsProtocolo layoutCampo = bitsProtocolo.stream().filter(e -> e.getNumeroDoBit().equals(bit.getNumeroDoBit())).findFirst().get();

            if(layoutCampo != null)
                bit.setDescricao(layoutCampo.getDescricao());
        }
    }

}
