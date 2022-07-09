package br.com.lucas.wishlist.domain.ports;

import br.com.lucas.wishlist.domain.model.Produto;

import java.util.List;
import java.util.Optional;

public interface BusinessLogic {

    Produto adicionaProdutoNaWishlist(Produto produto);
    void deletaProdutoDaWishList(String nome, String marca, String detalhes);
    List<Produto> consultaProdutosDaWishlist();
    Produto verificaSeProdutoEstaNaWishlist(String nome, String marca, String detalhes);
}
