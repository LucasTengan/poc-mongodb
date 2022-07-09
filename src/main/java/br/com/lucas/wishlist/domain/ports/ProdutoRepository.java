package br.com.lucas.wishlist.domain.ports;

import br.com.lucas.wishlist.domain.model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {

    Produto criaProduto(Produto produto);
    void deletaProduto(Produto produto);
    List<Produto> listaProdutos();
    Optional<Produto> buscaProdutoPorNomeMarcaDetalhes(String nome, String marca, String detalhes);
}
