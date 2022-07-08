package br.com.lucas.wishlist.domain.ports;

import br.com.lucas.wishlist.domain.model.Produto;

import java.util.List;

public interface BusinessLogic {

    Produto adicionaProdutoNaWishList(Produto produto);
    void deletaProdutoDaWishList(String id);
    List<Produto> consultaProdutosDaWishlist();
    Produto consultaSeDeterminadoProdutoEstaNaWishlist(String id);
}
