package br.com.lucas.wishlist.adapter.inbound.controller;

import br.com.lucas.wishlist.adapter.inbound.dto.ProdutoRequest;
import br.com.lucas.wishlist.adapter.inbound.dto.ProdutoResponse;
import br.com.lucas.wishlist.adapter.inbound.mapper.ProdutoMapper;
import br.com.lucas.wishlist.domain.model.Produto;
import br.com.lucas.wishlist.domain.ports.BusinessLogic;
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

    @GetMapping("/")
    public ResponseEntity<List<Produto>> listAllProdutosNaWishlist() {
        return new ResponseEntity<>(businessLogic.consultaProdutosDaWishlist(), HttpStatus.OK);
    }
}
