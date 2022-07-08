package br.com.lucas.wishlist.domain.service;

import br.com.lucas.wishlist.domain.model.Produto;
import br.com.lucas.wishlist.domain.ports.BusinessLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService implements BusinessLogic {

    @Override
    public Produto adicionaProdutoNaWishList(Produto produto) {
        return null;
    }

    @Override
    public void deletaProdutoDaWishList(String id) {

    }

    @Override
    public List<Produto> consultaProdutosDaWishlist() {
        return null;
    }

    @Override
    public Produto consultaSeDeterminadoProdutoEstaNaWishlist(String id) {
        return null;
    }
}
