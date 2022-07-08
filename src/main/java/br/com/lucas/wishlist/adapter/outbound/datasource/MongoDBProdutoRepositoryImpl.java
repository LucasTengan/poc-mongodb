package br.com.lucas.wishlist.adapter.outbound.datasource;

import br.com.lucas.wishlist.domain.model.Produto;
import br.com.lucas.wishlist.domain.ports.ProdutoRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MongoDBProdutoRepositoryImpl implements ProdutoRepository {

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
