package com.back.ecom.tp.controller;

import com.back.ecom.tp.dto.PanierDTO;
import com.back.ecom.tp.service.PanierService;
import com.back.ecom.tp.util.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.back.ecom.tp.constant.PathConstants.PANIER;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping(PANIER)
public class PanierController {

    private final PanierService panierService;

    @PostMapping(value = "/{id}")
    public ResponseEntity<PanierDTO> ajouterPanier(@PathVariable Long id,
                                                   @RequestParam(value = "quantite") Integer quantite) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(panierService.ajouterAuPanier(id, AuthenticationUtils.getUsername(),quantite));
    }

    @GetMapping
    public ResponseEntity<List<PanierDTO>> getMyCart() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(panierService.getMyCart(AuthenticationUtils.getUsername()));
    }

    @GetMapping(value = "/validate")
    public ResponseEntity<List<PanierDTO>> validateCart() {
        panierService.validateCart(AuthenticationUtils.getUsername());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
