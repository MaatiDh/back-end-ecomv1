package com.back.ecom.tp.dao;

import com.back.ecom.tp.entity.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Long>, JpaSpecificationExecutor<Panier> {

    List<Panier> getByClient_Email(String email);
    List<Panier> getByArticle_Id(Long id);
}
