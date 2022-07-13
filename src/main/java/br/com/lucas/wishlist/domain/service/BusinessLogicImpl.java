package br.com.lucas.wishlist.domain.service;

import br.com.lucas.wishlist.domain.model.entity.Produto;
import br.com.lucas.wishlist.domain.model.exception.NaoEPossivelAdicionarMaisProdutosNaWishlist;
import br.com.lucas.wishlist.domain.model.exception.ProdutoNaoEncontradoNaWishlistException;
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
        List<Produto> produtos = consultaProdutosDaWishlist();
        long quantidadeTotalDeProdutos = produtos.stream().mapToInt(Produto::getQtd).sum();
        if (quantidadeTotalDeProdutos >= 20) throw new NaoEPossivelAdicionarMaisProdutosNaWishlist();
        Optional<Produto> produtoProcurado = produtoRepository.buscaProdutoPorNomeMarcaDetalhes(produto.getNome(), produto.getMarca(), produto.getDetalhes());
        produto.setQtd(1);
        if (produtoProcurado.isPresent()) {
            produto = produtoProcurado.get();
            produto.setQtd(produto.getQtd() + 1);
        }

        return produtoRepository.criaProduto(produto);
    }

    @Override
    public void deletaProdutoDaWishList(String nome, String marca, String detalhes) {
        Produto produtoProcurado = verificaSeProdutoEstaNaWishlist(nome, marca, detalhes);
        if (produtoProcurado.getQtd() >= 2) {
            produtoProcurado.setQtd(produtoProcurado.getQtd() - 1);
            produtoRepository.atualizaProduto(produtoProcurado);
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
        return produtoRepository.buscaProdutoPorNomeMarcaDetalhes(nome, marca, detalhes).orElseThrow(ProdutoNaoEncontradoNaWishlistException::new);
    }
}
