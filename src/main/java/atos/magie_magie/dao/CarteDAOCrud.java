/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.dao;

import atos.magie_magie.entity.Carte;
import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Administrateur
 */
public interface CarteDAOCrud extends CrudRepository<Carte, Long>{
    
}
