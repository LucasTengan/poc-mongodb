package br.com.lucas.wishlist.domain.service;

import br.com.lucas.wishlist.domain.model.Produto;
import br.com.lucas.wishlist.domain.ports.BusinessLogic;
import br.com.lucas.wishlist.domain.ports.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessLogicImpl implements BusinessLogic {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto adicionaProdutoNaWishlist(Produto produto) {
        return produtoRepository.criaProduto(produto);
    }

    @Override
    public void deletaProdutoDaWishList(String id) {
        /* TODO */
    }

    @Override
    public List<Produto> consultaProdutosDaWishlist() {
        return produtoRepository.listaProdutos();
    }

    @Override
    public Produto consultaSeDeterminadoProdutoEstaNaWishlist(String id) {
        /* TODO */
        return null;
    }
}
