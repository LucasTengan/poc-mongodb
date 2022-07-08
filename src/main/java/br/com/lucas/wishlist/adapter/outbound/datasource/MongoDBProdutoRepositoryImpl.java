package br.com.lucas.wishlist.adapter.outbound.datasource;

import br.com.lucas.wishlist.domain.model.Produto;
import br.com.lucas.wishlist.domain.ports.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public class MongoDBProdutoRepositoryImpl implements ProdutoRepository {

    @Autowired
    private MongoDBProdutoRepository mongoDBProdutoRepository;

    @Override
    public Produto criaProduto(Produto produto) {
        return mongoDBProdutoRepository.save(produto);
    }

    @Override
    public void deletaProdutoPorId(String id) {
        /* TODO */
    }

    @Override
    public List<Produto> listaProdutos() {
        return mongoDBProdutoRepository.findAll();
    }

    @Override
    public Produto verificaSeExisteProdutoPorId(String id) {
        /* TODO */
        return null;
    }
}
