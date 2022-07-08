package br.com.lucas.wishlist.domain.ports;

import br.com.lucas.wishlist.domain.model.Produto;

import java.util.List;

public interface ProdutoRepository {

    Produto criaProduto(Produto produto);
    void deletaProdutoPorId(String id);
    List<Produto> listaProdutos();
    Produto verificaSeExisteProdutoPorId(String id);
}
