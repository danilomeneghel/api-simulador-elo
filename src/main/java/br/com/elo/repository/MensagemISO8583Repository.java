package br.com.elo.repository;

import br.com.elo.model.MensagemISO8583;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MensagemISO8583Repository extends MongoRepository<MensagemISO8583, String> {

    MensagemISO8583 findByMensagemSequence(Long codigo);
    boolean existsByMensagemSequence(Long codigo);
    @Query("{'mensagemSequence':?0, 'bitsMensagem': { $elemMatch: { numeroDoBit: ?1}}}")
    MensagemISO8583 findNroBitByMensagemSequence(Long codigo, Integer nroBit);
}
