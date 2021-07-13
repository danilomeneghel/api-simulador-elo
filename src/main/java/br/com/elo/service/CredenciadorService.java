package br.com.elo.service;

import br.com.elo.common.exception.ObjectNotFoundException;
import br.com.elo.common.exception.ParametroInvalidoException;

import br.com.elo.domain.MensagensRetorno;
import br.com.elo.domain.SchemaVersion;

import br.com.elo.domain.dto.request.CredenciadorRequestCriteriaDTO;
import br.com.elo.domain.dto.request.CredenciadorRequestDTO;
import br.com.elo.domain.dto.response.CredenciadorResponseDTO;
import br.com.elo.model.*;
import br.com.elo.repository.CredenciadorRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class CredenciadorService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CredenciadorRepository repository;

    @Autowired
    private  SequencesGeneratorService sequenceGenerator;

    public CredenciadorService(ModelMapper modelMapper, CredenciadorRepository repository, SequencesGeneratorService sequenceGenerator){
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.sequenceGenerator = sequenceGenerator;
    }

    public CredenciadorResponseDTO save(CredenciadorRequestDTO dto){
        if(repository.existsByCredenciadorCodigo(dto.getCredenciadorCodigo())){
            throw new ParametroInvalidoException(MensagensRetorno.CREDENCIADOR_COD_BAD_EXISTE.getDescricao());
        }

        Credenciador credenciador = modelMapper.map(dto, Credenciador.class);
        credenciador.setDataCriacao(LocalDateTime.now());
        credenciador.setSchemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion());

        // Verifica se já existe estabelcimento para o mesmo credenciador
        // já cadastrado.
        if(credenciador.getEstabelecimentoComercial() != null){
            if(existsEstabelecimentoComercialDuplicadas(credenciador.getEstabelecimentoComercial())){
                throw new ParametroInvalidoException(MensagensRetorno.ESTABELECIMENTO_COMERCIAL_DUPLICADA.getDescricao());
            }
        }
        credenciador = repository.save(credenciador);

        return modelMapper.map(credenciador, CredenciadorResponseDTO.class);
    }

    public CredenciadorResponseDTO findById(String id) {
        Credenciador credenciador = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(MensagensRetorno.CREDENCIADOR_NAO_LOCALIZADO.getDescricao()));
        return modelMapper.map(credenciador, CredenciadorResponseDTO.class);
    }

    public CredenciadorResponseDTO findByCodigo(Integer credenciadorCodigo) {
        Credenciador credenciador = repository.findByCredenciadorCodigo(credenciadorCodigo);
        if(credenciador == null)
            throw new ObjectNotFoundException(MensagensRetorno.CREDENCIADOR_NAO_LOCALIZADO.getDescricao());
        return modelMapper.map(credenciador, CredenciadorResponseDTO.class);
    }

    public CredenciadorResponseDTO update(Integer credenciadorCodigo, CredenciadorRequestDTO dto) {
        Credenciador credenciador = modelMapper.map(dto, Credenciador.class);

        if(credenciadorCodigo == null || credenciadorCodigo == 0) {
            throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao());
        }

        Credenciador credenciadorAux = repository.findByCredenciadorCodigo(credenciadorCodigo);

        if( Objects.isNull(credenciadorAux)) {
            throw new ObjectNotFoundException(MensagensRetorno.CREDENCIADOR_NAO_LOCALIZADO.getDescricao());
        }

        //Se for diferente do que esta cadastrado, verificar duplicidade
        if(!credenciadorCodigo.equals(dto.getCredenciadorCodigo()))
        {
            if (repository.existsByCredenciadorCodigo(dto.getCredenciadorCodigo())) {
                throw new ParametroInvalidoException(MensagensRetorno.CREDENCIADOR_COD_BAD_EXISTE.getDescricao());
            }
        }

        credenciador.setId(credenciadorAux.getId());
        credenciador.setDataCriacao(LocalDateTime.now());
        credenciador.setDataAlteracao(LocalDateTime.now());
        credenciador.setSchemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion());

        // Verifica se já existe estabelecimento para o mesmo credenciador
        // já cadastrado.
         if(credenciador.getEstabelecimentoComercial() != null){
             if(existsEstabelecimentoComercialDuplicadas(credenciador.getEstabelecimentoComercial())){
                 throw new ParametroInvalidoException(MensagensRetorno.ESTABELECIMENTO_COMERCIAL_DUPLICADA.getDescricao());
             }
         }

        credenciador = repository.save(credenciador);
        return modelMapper.map(credenciador, CredenciadorResponseDTO.class);
    }

    public List<CredenciadorResponseDTO> findAll(CredenciadorRequestCriteriaDTO dto) {
        Credenciador credenciador = modelMapper.map(dto, Credenciador.class);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIncludeNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);

        Example<Credenciador> example = Example.of(credenciador, matcher);
        return modelMapper.map(repository.findAll(example), new TypeToken<List<CredenciadorResponseDTO>>() {}.getType());
    }

    public static boolean existsEstabelecimentoComercialDuplicadas(List<EstabelecimentoComercial> listaEstabelecimentoComercial) {
        if(listaEstabelecimentoComercial != null) {
            for (EstabelecimentoComercial estabelecimentoComercial : listaEstabelecimentoComercial) {

                long contagem = listaEstabelecimentoComercial.stream().filter(e -> e.getCpfCnpj().equals(estabelecimentoComercial.getCpfCnpj())).count();
                if(contagem > 1)
                    return true;
            }
        }
        return false;
    }
}