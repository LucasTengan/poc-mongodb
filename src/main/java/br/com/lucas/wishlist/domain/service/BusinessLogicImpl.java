package br.com.lucas.wishlist.domain.service;

import br.com.lucas.wishlist.domain.model.Produto;
import br.com.lucas.wishlist.domain.ports.BusinessLogic;
import br.com.lucas.wishlist.domain.ports.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessLogicImpl implements BusinessLogic {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto adicionaProdutoNaWishlist(Produto produto) {
        Optional<Produto> produtoProcurado = produtoRepository.buscaProdutoPorNomeMarcaDetalhes(produto.getNome(), produto.getMarca(), produto.getDetalhes());
        produto.setQtd(1);
        if (produtoProcurado.isPresent()) {
            produto = produtoProcurado.get();
            produto.setQtd(produto.getQtd() + 1);
        }
//        produto.setQtd(verificaSeProdutoJaEstaNaWishlist(produto.getNome(), produto.getMarca(), produto.getDetalhes()) ? produto.getQtd()+1 : 1);
        // Atualizar na base de dados

        return produtoRepository.criaProduto(produto);
    }

    @Override
    public void deletaProdutoDaWishList(String nome, String marca, String detalhes) {
        Produto produtoProcurado = verificaSeProdutoEstaNaWishlist(nome, marca, detalhes);
        if (produtoProcurado.getQtd() >= 2) {
            produtoProcurado.setQtd(produtoProcurado.getQtd() - 1);
            produtoRepository.criaProduto(produtoProcurado);
            return;
        }
        produtoRepository.deletaProduto(produtoProcurado);
    }

    @Override
    public List<Produto> consultaProdutosDaWishlist() {
        return produtoRepository.listaProdutos();
    }

    @Override
    public Produto verificaSeProdutoEstaNaWishlist(String nome, String marca, String detalhes) {
        return produtoRepository.buscaProdutoPorNomeMarcaDetalhes(nome, marca, detalhes).orElseThrow(() -> new RuntimeException());
    }
}
