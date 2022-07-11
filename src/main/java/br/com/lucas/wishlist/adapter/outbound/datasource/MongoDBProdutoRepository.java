package br.com.lucas.wishlist.adapter.outbound.datasource;

import br.com.lucas.wishlist.domain.model.entity.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoDBProdutoRepository extends MongoRepository<Produto, String> {

    Optional<Produto> findByNomeAndMarcaAndDetalhes(String nome, String marca, String detalhes);
}
