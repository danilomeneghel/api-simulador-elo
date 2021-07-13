package br.com.elo.repository;

import br.com.elo.model.Protocolo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProtocoloRepository extends MongoRepository<Protocolo, String> {

    Protocolo findByProtocoloSequence(Long codigo);
    boolean existsByProtocoloSequence(Long codigo);

    @Query("{'protocoloSequence':?0, 'bitsProtocolo': { $elemMatch: { numeroDoBit: ?1}}}")
    Protocolo findNroBitByProtocoloSequence(Long protocoloSequence, Integer nroBit);
}
