package br.com.lucas.wishlist.domain.service;

import br.com.lucas.wishlist.domain.model.Produto;
import br.com.lucas.wishlist.domain.ports.BusinessLogic;
import br.com.lucas.wishlist.domain.ports.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService implements BusinessLogic {

    private ProdutoRepository produtoRepository;

    @Override
    public Produto adicionaProdutoNaWishList(Produto produto) {
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
