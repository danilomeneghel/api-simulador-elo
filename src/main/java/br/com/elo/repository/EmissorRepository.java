package br.com.elo.repository;

import br.com.elo.model.Emissor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmissorRepository extends MongoRepository<Emissor, String> {
    Emissor findByCodigoEmissor(Integer codigoEmissor);
    Emissor findByCodigoEmissorAndCodigoBandeira(Integer codigoEmissor, Integer codigoBandeira);
    boolean existsByCodigoBandeira(Integer codigoBandeira);
    boolean existsByCodigoEmissor(Integer codigoEmissor);
    boolean existsByCodigoEmissorAndCodigoBandeira(Integer codigoEmissor, Integer codigoBandeira);
}
