package br.com.elo.repository;

import br.com.elo.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {

    Produto findByCodigoBandeiraAndCodigoProduto(Integer codigoBandeira, Integer codigoProduto);
    boolean existsByCodigoBandeiraAndCodigoProduto(Integer codigoBandeira, Integer codigoProduto);
}
