package com.back.ecom.tp.mapper;

import com.back.ecom.tp.dto.PanierDTO;
import com.back.ecom.tp.entity.Panier;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface PanierMapper {

    Panier mapToEntity(PanierDTO panierDTO);

    PanierDTO mapToDTO(Panier panier);

    List<Panier> mapToListOfEntity(List<PanierDTO> panierDTOList);

    List<PanierDTO> mapToListOfDTO(List<Panier> panierList);
}
