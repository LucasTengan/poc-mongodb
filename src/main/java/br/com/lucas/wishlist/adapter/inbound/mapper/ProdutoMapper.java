package br.com.lucas.wishlist.adapter.inbound.mapper;

import br.com.lucas.wishlist.adapter.inbound.dto.ProdutoRequest;
import br.com.lucas.wishlist.adapter.inbound.dto.ProdutoResponse;
import br.com.lucas.wishlist.domain.model.entity.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    Produto requestToEntity(ProdutoRequest produtoRequest);
    ProdutoResponse entityToResponse(Produto produto);
    List<ProdutoResponse> entitiesToResponse(List<Produto> produtos);
}
