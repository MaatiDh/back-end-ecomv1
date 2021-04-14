package com.back.ecom.tp.mapper;

import com.back.ecom.tp.dto.UtilisateurDTO;
import com.back.ecom.tp.entity.Utilisateur;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface UtilisateurMapper {

    Utilisateur mapToEntity(UtilisateurDTO utilisateurDTO);

    UtilisateurDTO mapToDTO(Utilisateur utilisateur);

    List<Utilisateur> mapToListOfEntity(List<UtilisateurDTO> utilisateurDTOList);

    List<UtilisateurDTO> mapToListOfDTO(List<Utilisateur> utilisateurList);
}
