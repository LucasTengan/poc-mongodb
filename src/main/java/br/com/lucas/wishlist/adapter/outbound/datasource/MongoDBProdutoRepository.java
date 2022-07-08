package br.com.lucas.wishlist.adapter.outbound.datasource;

import br.com.lucas.wishlist.domain.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoDBProdutoRepository extends MongoRepository<Produto, String> {
}
