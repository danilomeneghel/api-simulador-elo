package br.com.elo.service;

import br.com.elo.common.exception.ObjectNotFoundException;
import br.com.elo.common.exception.ParametroInvalidoException;
import br.com.elo.domain.MensagensRetorno;
import br.com.elo.domain.SchemaVersion;
import br.com.elo.domain.dto.request.CartaoRequestCriteriaDTO;
import br.com.elo.domain.dto.request.CartaoRequestDTO;
import br.com.elo.domain.dto.response.CartaoResponseDTO;
import br.com.elo.model.Cartao;
import br.com.elo.repository.CartaoRepository;
import br.com.elo.repository.EmissorRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartaoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CartaoRepository repository;

    @Autowired
    private EmissorRepository emissorRepository;


    public CartaoService(ModelMapper modelMapper, CartaoRepository repository, EmissorRepository emissorRepository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.emissorRepository = emissorRepository;
    }

    public CartaoResponseDTO save(CartaoRequestDTO dto) {
        Cartao cartao = modelMapper.map(dto, Cartao.class);

        if(!emissorRepository.existsByCodigoEmissorAndCodigoBandeira(dto.getCodigoEmissor(), dto.getCodigoBandeira())) {
            throw new ParametroInvalidoException(MensagensRetorno.CARTAO_EMISSOR_BANDEIRA_NAO_LOCALIZADO.getDescricao());
        }

        if(repository.existsByPan(dto.getPan())) {
            throw new ParametroInvalidoException(MensagensRetorno.CARTAO_PAN_EXISTENTE.getDescricao());
        }

        cartao.setDataCriacao(LocalDateTime.now());
        cartao.setSchemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion());
        cartao = repository.save(cartao);
        return modelMapper.map(cartao, CartaoResponseDTO.class);
    }

    public CartaoResponseDTO findById(String id) {
        Cartao cartao = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(MensagensRetorno.CARTAO_NAO_LOCALIZADO.getDescricao()));
        return modelMapper.map(cartao, CartaoResponseDTO.class);
    }

    public CartaoResponseDTO findByPan(String pan) {

        Cartao cartao = repository.findByPan(pan);

        if(cartao == null)
            throw new ObjectNotFoundException(MensagensRetorno.CARTAO_NAO_LOCALIZADO.getDescricao());

        return modelMapper.map(cartao, CartaoResponseDTO.class);
    }

    public CartaoResponseDTO update(String pan, CartaoRequestDTO dto) {
        Cartao cartao = modelMapper.map(dto, Cartao.class);

        if(pan == null) {
            throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao());
        }

        if(!emissorRepository.existsByCodigoEmissorAndCodigoBandeira(dto.getCodigoEmissor(), dto.getCodigoBandeira())) {
            throw new ParametroInvalidoException(MensagensRetorno.CARTAO_EMISSOR_BANDEIRA_NAO_LOCALIZADO.getDescricao());
        }

        Cartao cartaoAux =  repository.findByPan(pan);
        if(cartaoAux == null) {
            throw new ObjectNotFoundException(MensagensRetorno.CARTAO_NAO_LOCALIZADO.getDescricao());
        }

        //Se pan do request diferente do cadastrado, validar duplicidade.
        if(!pan.equals(dto.getPan())){
            if(repository.existsByPan(dto.getPan()))
                throw new ParametroInvalidoException(MensagensRetorno.CARTAO_PAN_EXISTENTE.getDescricao());
        }

        cartao.setId(cartaoAux.getId());
        cartao.setDataAlteracao(LocalDateTime.now());
        cartao.setDataCriacao(cartaoAux.getDataCriacao());
        cartao.setSchemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion());
        cartao = repository.save(cartao);
        return modelMapper.map(cartao, CartaoResponseDTO.class);

    }

    public List<CartaoResponseDTO> findAll(CartaoRequestCriteriaDTO dto) {
        Cartao cartao = modelMapper.map(dto, Cartao.class);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
        Example example = Example.of(cartao, matcher);

        List<CartaoResponseDTO> cartaoList = modelMapper.map(repository.findAll(example), new TypeToken<List<CartaoResponseDTO>>() {}.getType());

        return cartaoList;
    }

}
