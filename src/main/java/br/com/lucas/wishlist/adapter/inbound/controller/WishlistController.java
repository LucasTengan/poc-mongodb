package br.com.lucas.wishlist.adapter.inbound.controller;

import br.com.lucas.wishlist.adapter.inbound.dto.ProdutoRequest;
import br.com.lucas.wishlist.adapter.inbound.dto.ProdutoResponse;
import br.com.lucas.wishlist.adapter.inbound.mapper.ProdutoMapper;
import br.com.lucas.wishlist.domain.model.entity.Produto;
import br.com.lucas.wishlist.domain.model.exception.FaltandoParametroParaVerificarException;
import br.com.lucas.wishlist.domain.ports.BusinessLogic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/netshoes/wishlist")
public class WishlistController {

    @Autowired
    private BusinessLogic businessLogic;
    private ProdutoMapper produtoMapper = ProdutoMapper.INSTANCE;

    @PostMapping("/")
    public ResponseEntity<ProdutoResponse> postProdutoNaWishlist(@RequestBody ProdutoRequest produtoRequest) {
        Produto produtoASerAdicionadoNaWishlist = produtoMapper.requestToEntity(produtoRequest);
        Produto produtoAdicionado = businessLogic.adicionaProdutoNaWishlist(produtoASerAdicionadoNaWishlist);
        ProdutoResponse produtoAdicionadoASerRetornado = produtoMapper.entityToResponse(produtoAdicionado);

        return new ResponseEntity<>(produtoAdicionadoASerRetornado, HttpStatus.CREATED);
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteProdutoDaWishlist(String nome, String marca, String detalhes) {
        businessLogic.deletaProdutoDaWishList(nome, marca, detalhes);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> listProdutosDaWishlist(@RequestParam(required = false) String nome, @RequestParam(required = false) String marca, @RequestParam(required = false) String detalhes) {
        if (StringUtils.isAllBlank(nome, marca, detalhes)) {
            List<Produto> produtos = businessLogic.consultaProdutosDaWishlist();
            List<ProdutoResponse> produtosNaWishlistASeremRetornados = produtoMapper.entitiesToResponse(produtos);

            return new ResponseEntity<>(produtosNaWishlistASeremRetornados, HttpStatus.OK);
        }
        if (StringUtils.isNoneBlank(nome, marca, detalhes)) {
            Produto produtoProcurado = businessLogic.verificaSeProdutoEstaNaWishlist(nome, marca, detalhes);
            ProdutoResponse produtoEncontradoASerRetornado = produtoMapper.entityToResponse(produtoProcurado);

            return new ResponseEntity<>(produtoEncontradoASerRetornado, HttpStatus.OK);
        }

        throw new FaltandoParametroParaVerificarException();
    }
}
