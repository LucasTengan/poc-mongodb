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
        Optional<Produto> produtoProcurado = verificaSeProdutoEstaNaWishlist(produto.getNome(), produto.getMarca(), produto.getDetalhes());
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
        Optional<Produto> produtoProcurado = verificaSeProdutoEstaNaWishlist(nome, marca, detalhes);
        if (produtoProcurado.isEmpty()) {
            throw new RuntimeException();
        }
        produtoRepository.deletaProduto(produtoProcurado.get());
    }

    @Override
    public List<Produto> consultaProdutosDaWishlist() {
        return produtoRepository.listaProdutos();
    }

    @Override
    public Optional<Produto> verificaSeProdutoEstaNaWishlist(String nome, String marca, String detalhes) {
        return produtoRepository.buscaProdutoPorNomeMarcaDetalhes(nome, marca, detalhes);
    }
}
