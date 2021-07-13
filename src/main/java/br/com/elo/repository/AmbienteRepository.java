package br.com.elo.repository;

import br.com.elo.model.Ambiente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmbienteRepository extends MongoRepository<Ambiente, String> {
    Ambiente findByAmbienteSequence(Long ambienteSequence);

    boolean existsByAmbienteSequence(Long ambienteSequence);

    @Query("{'ambienteSequence': { $ne: ?0 }, 'portasServidores': { $elemMatch: { numeroPorta: ?1}}}")
    Ambiente findPortaServidor(Long ambienteSequence, Integer numeroPorta);

    @Query("{'ambienteSequence': { $ne: ?0 }, 'portasClientes': { $elemMatch: { numeroPorta: ?1}}}")
    Ambiente findPortaCliente(Long ambienteSequence, Integer numeroPorta);

    @Query("{'ambienteSequence': { $eq: ?0 }, 'portasClientes': { $elemMatch: { nomePorta: ?1}}}")
    List<Ambiente> findNomePortaCliente(Long ambienteSequence, String nomePorta);
}
