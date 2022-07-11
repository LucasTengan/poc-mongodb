package br.com.lucas.wishlist.adapter.outbound.datasource;

import br.com.lucas.wishlist.domain.model.entity.Produto;
import br.com.lucas.wishlist.domain.ports.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
    public Produto atualizaProduto(Produto produto) {
        return mongoDBProdutoRepository.save(produto);
    }

    @Override
    public void deletaProduto(Produto produto) {
        mongoDBProdutoRepository.delete(produto);
    }

    @Override
    public List<Produto> listaProdutos() {
        return mongoDBProdutoRepository.findAll();
    }

    @Override
    public Optional<Produto> buscaProdutoPorNomeMarcaDetalhes(String nome, String marca, String detalhes) {
        return mongoDBProdutoRepository.findByNomeAndMarcaAndDetalhes(nome, marca, detalhes);
    }
}
