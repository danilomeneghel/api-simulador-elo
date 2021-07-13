package br.com.elo.service;

import br.com.elo.common.exception.ObjectNotFoundException;
import br.com.elo.common.exception.ParametroInvalidoException;
import br.com.elo.common.utils.converters.MapConverters;
import br.com.elo.domain.MensagensRetorno;
import br.com.elo.domain.SchemaVersion;
import br.com.elo.domain.dto.request.AmbienteRequestCriteriaDTO;
import br.com.elo.domain.dto.request.AmbienteRequestDTO;
import br.com.elo.domain.dto.response.AmbienteResponseDTO;
import br.com.elo.model.Ambiente;
import br.com.elo.model.PortaCliente;
import br.com.elo.model.PortaServidor;
import br.com.elo.model.Protocolo;
import br.com.elo.repository.AmbienteRepository;
import br.com.elo.repository.ProtocoloRepository;
import com.mongodb.client.result.UpdateResult;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AmbienteService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AmbienteRepository repository;

    @Autowired
    private ProtocoloRepository protocoloRepository;

    @Autowired
    private SequencesGeneratorService sequenceGenerator;

    @Autowired
    private MongoOperations mongoOperations;

    private static String SEQUENCE = "ambienteSequence";

    public AmbienteResponseDTO save(AmbienteRequestDTO dto) {
        Ambiente ambiente = modelMapper.map(dto, Ambiente.class);
        ambiente.setDataCriacao(LocalDateTime.now());
        ambiente.setAmbienteSequence(sequenceGenerator.generateSequence(Ambiente.SEQUENCE_NAME));
        ambiente.setSchemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion());

        if(ambiente.getPortasServidores() != null) {
            if(existsPortasServidoresDuplicadas(ambiente.getPortasServidores())) {
                throw new ParametroInvalidoException(MensagensRetorno.PORTA_SERVIDOR_DUPLICADA.getDescricao());
            }
            if(portaServidorExistente(ambiente, 0L)) {
                throw new ParametroInvalidoException(MensagensRetorno.PORTA_SERVIDOR_JA_CADASTRADA.getDescricao());
            }

            geraDataAlteracaoPortaServidor(ambiente.getPortasServidores());
        }

        if(ambiente.getPortasClientes() != null) {

            if(existsNomePortasClientesDuplicadas(ambiente.getPortasClientes())) {
                throw new ParametroInvalidoException(MensagensRetorno.NOME_PORTA_CLIENTE_DUPLICADO.getDescricao());
            }

            geraDataAlteracaoPortaCliente(ambiente.getPortasClientes());
        }

        if(!existsProtocolo(ambiente)) {
            throw new ParametroInvalidoException(MensagensRetorno.PROTOCOLO_NAO_LOCALIZADO.getDescricao());
        }

        ambiente = repository.save(ambiente);
        return modelMapper.map(ambiente, AmbienteResponseDTO.class);
    }

    public AmbienteResponseDTO findById(String id) {
        Ambiente ambiente = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(MensagensRetorno.AMBIENTE_NAO_LOCALIZADO.getDescricao()));
        return modelMapper.map(ambiente, AmbienteResponseDTO.class);
    }

    public AmbienteResponseDTO findByAmbienteSequence(Long ambienteSequence) {
        Ambiente ambiente = repository.findByAmbienteSequence(ambienteSequence);
        if(ambiente == null)
            throw new ObjectNotFoundException(MensagensRetorno.AMBIENTE_NAO_LOCALIZADO.getDescricao());
        return modelMapper.map(ambiente, AmbienteResponseDTO.class);
    }

    public AmbienteResponseDTO update(Long ambienteSequence, AmbienteRequestDTO dto) {
        Ambiente ambiente = modelMapper.map(dto, Ambiente.class);

       if(ambienteSequence == null || ambienteSequence == 0) {
            throw new ParametroInvalidoException(MensagensRetorno.BAD_REQUEST.getDescricao());
        }

        Ambiente ambienteAux = repository.findByAmbienteSequence(ambienteSequence);

        if(ambienteAux == null) {
            throw new ObjectNotFoundException(MensagensRetorno.AMBIENTE_NAO_LOCALIZADO.getDescricao());
        }

        if(existsPortasServidoresDuplicadas(ambiente.getPortasServidores())) {
            throw new ParametroInvalidoException(MensagensRetorno.PORTA_SERVIDOR_DUPLICADA.getDescricao());
        }

        if(existsNomePortasClientesDuplicadas(ambiente.getPortasClientes())) {
            throw new ParametroInvalidoException(MensagensRetorno.NOME_PORTA_CLIENTE_DUPLICADO.getDescricao());
        }

        if(portaServidorExistente(ambiente, ambienteSequence)) {
            throw new ParametroInvalidoException(MensagensRetorno.PORTA_SERVIDOR_JA_CADASTRADA.getDescricao());
        }

        if(ambiente.getPortasServidores() != null) {
            if(existsPortasServidoresDuplicadas(ambiente.getPortasServidores())) {
                throw new ParametroInvalidoException(MensagensRetorno.PORTA_SERVIDOR_DUPLICADA.getDescricao());
            }

            if(portaServidorExistente(ambiente, ambienteSequence)) {
                throw new ParametroInvalidoException(MensagensRetorno.PORTA_SERVIDOR_JA_CADASTRADA.getDescricao());
            }

            geraDataAlteracaoPortaServidor(ambiente.getPortasServidores());
        }

        if(ambiente.getPortasClientes() != null) {

            if(nomePortaClienteExistente(ambiente,ambienteSequence)) {
                throw new ParametroInvalidoException(MensagensRetorno.NOME_PORTA_CLIENTE_DUPLICADO.getDescricao());
            }

            if (!existsProtocolo(ambiente)) {
                throw new ParametroInvalidoException(MensagensRetorno.PROTOCOLO_NAO_LOCALIZADO.getDescricao());
            }
            geraDataAlteracaoPortaCliente(ambiente.getPortasClientes());
        }

        ambiente.setAmbienteSequence(ambienteAux.getAmbienteSequence());
        ambiente.setId(ambienteAux.getId());
        ambiente.setDataCriacao(ambienteAux.getDataCriacao());
        ambiente.setStatus(dto.getStatus());
        ambiente.setDataAlteracao(LocalDateTime.now());
        ambiente.setDescricao(dto.getDescricao());
        ambiente.setSchemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion());
        ambiente = repository.save(ambiente);
        return modelMapper.map(ambiente, AmbienteResponseDTO.class);
    }

    public List<AmbienteResponseDTO> findAll(AmbienteRequestCriteriaDTO dto) {
        modelMapper.addConverter(MapConverters.toStatus);

        Ambiente ambiente = modelMapper.map(dto, Ambiente.class);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIncludeNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);

        Example<Ambiente> example = Example.of(ambiente, matcher);

        return modelMapper.map(repository.findAll(example), new TypeToken<List<AmbienteResponseDTO>>() {}.getType());
    }

    /***
     * Verifica se a porta servidor existe para algum ambiente.
     * @param novaConfAmbiente ambiente a ser cadastrado
     * @param ambienteSequence do ambiente
     * @return retorna se a porta existe em outro ambiente
     */
    public boolean portaServidorExistente(Ambiente novaConfAmbiente, Long ambienteSequence) {
        if(novaConfAmbiente.getPortasServidores() != null) {
            for (PortaServidor portaServidor : novaConfAmbiente.getPortasServidores()) {
                if (repository.findPortaServidor(ambienteSequence, portaServidor.getNumeroPorta()) != null)
                    return true;
            }
        }

        return false;
    }

    /***
     * Verifica se a porta cliente existe para algum ambiente.
     * @param novaConfAmbiente ambiente a ser cadastrado
     * @param ambienteSequence do ambiente
     * @return retorna se a porta existe em outro ambiente
     */
    public boolean portaClienteExistente(Ambiente novaConfAmbiente, Long ambienteSequence) {
        if(novaConfAmbiente.getPortasClientes() != null) {
            for (PortaCliente portaCliente : novaConfAmbiente.getPortasClientes()) {
                if (repository.findPortaCliente(ambienteSequence, portaCliente.getNumeroPorta()) != null)
                    return true;
            }
        }

        return false;
    }

    /***
     * Verifica se o nome da porta cliente existe.
     * @param novaConfAmbiente ambiente a ser cadastrado
     * @param ambienteSequence do ambiente
     * @return retorna se a porta existe em outro ambiente
     */
    public boolean nomePortaClienteExistente(Ambiente novaConfAmbiente, Long ambienteSequence) {
        if(novaConfAmbiente.getPortasClientes() != null) {
            for (PortaCliente portaCliente : novaConfAmbiente.getPortasClientes()) {
                if (repository.findNomePortaCliente(ambienteSequence, portaCliente.getNomePorta()) != null && repository.findNomePortaCliente(ambienteSequence, portaCliente.getNomePorta()).size() > 1)
                    return true;
            }
        }

        return false;
    }


    /***
     * Verifica portas duplicadas na lista de porta servidores
     * @param listaPortaServidores lista de portas servidores
     * @return retorna se a lista possui portas duplicadas
     */
    public static boolean existsPortasServidoresDuplicadas(List<PortaServidor> listaPortaServidores){
        if(listaPortaServidores != null) {
            for (PortaServidor portaServidor : listaPortaServidores) {
                long contagem = listaPortaServidores.stream().filter(e -> e.getNumeroPorta().equals(portaServidor.getNumeroPorta())).count();

                if(contagem > 1) {
                    return true;
                }
            }
        }

        return false;
    }

    /***
     * Verifica portas duplicadas na lista de porta clientes
     * @param listaPortaClientes lista de portas servidores
     * @return retorna se a lista possui portas duplicadas
     */
    public static boolean existsPortasClientesDuplicadas(List<PortaCliente> listaPortaClientes) {
        if(listaPortaClientes != null) {

            for (PortaCliente portaCliente : listaPortaClientes) {
                long contagem = listaPortaClientes.stream().filter(e -> e.getNumeroPorta().equals(portaCliente.getNumeroPorta())).count();

                if(contagem > 1)
                    return true;
            }
        }

        return false;
    }

    /***
     * Verifica portas duplicadas na lista de porta clientes
     * @param listaPortaClientes lista de portas servidores
     * @return retorna se a lista possui portas duplicadas
     */
    public static boolean existsNomePortasClientesDuplicadas(List<PortaCliente> listaPortaClientes) {
        if(listaPortaClientes != null) {

            for (PortaCliente portaCliente : listaPortaClientes) {

                long contagem = listaPortaClientes.stream().filter(e -> e.getNomePorta().equals(portaCliente.getNomePorta())).count();

                if(contagem > 1)
                    return true;
            }
        }

        return false;
    }


    public void geraDataAlteracaoPortaCliente(List<PortaCliente> listaPortaClientes) {
        if(listaPortaClientes != null) {
            for (PortaCliente portaCliente : listaPortaClientes) {
                portaCliente.setDataAlteracao(LocalDateTime.now());
            }
        }
    }

    public void geraDataAlteracaoPortaServidor(List<PortaServidor> listaPortaServidor) {
        if(listaPortaServidor != null) {
            for (PortaServidor portaServidor : listaPortaServidor) {
                portaServidor.setDataAlteracao(LocalDateTime.now());
            }
        }
    }

    /**
     * Verifica a existência de um protocolo
     * @param ambiente ambiente ao qual será verificado a existência do protocolo para as portas cadastradas.
     * @return TRUE se o protocolo informado é válido e FALSE se não é
     */
    public boolean existsProtocolo(Ambiente ambiente) {
        if(ambiente.getPortasClientes() != null) {
            for (PortaCliente portaCliente : ambiente.getPortasClientes()) {
                if (portaCliente.getProtocoloSequence() != null) {
                    Protocolo protocolo = protocoloRepository.findByProtocoloSequence(portaCliente.getProtocoloSequence());
                    if (protocolo != null) {
                        continue;
                    } else {
                        return false;
                    }
                }
            }
        }
        if(ambiente.getPortasServidores() != null) {
            for(PortaServidor portaServidor : ambiente.getPortasServidores()) {
                if(portaServidor.getProtocoloSequence() != null) {
                    Protocolo protocolo = protocoloRepository.findByProtocoloSequence(portaServidor.getProtocoloSequence());
                    if(protocolo != null) {
                        continue;
                    } else {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public AmbienteResponseDTO updateNSU(Long ambienteSequence) {

        mongoOperations.updateFirst(new Query(Criteria.where(SEQUENCE).is(ambienteSequence)),
                new Update()
                        .inc("nsu", 1),
                        Ambiente.class);
        Query query = new Query();
        query.addCriteria(Criteria.where(SEQUENCE).is(ambienteSequence));
        Ambiente ambiente = mongoOperations.findOne(query, Ambiente.class);
        return modelMapper.map(ambiente, AmbienteResponseDTO.class);
    }
}
