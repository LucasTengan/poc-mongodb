package br.com.lucas.wishlist.domain.ports;

import br.com.lucas.wishlist.domain.model.entity.Produto;

import java.util.List;

public interface BusinessLogic {

    Produto adicionaProdutoNaWishlist(Produto produto);
    void deletaProdutoDaWishList(String nome, String marca, String detalhes);
    List<Produto> consultaProdutosDaWishlist();
    Produto verificaSeProdutoEstaNaWishlist(String nome, String marca, String detalhes);
}
