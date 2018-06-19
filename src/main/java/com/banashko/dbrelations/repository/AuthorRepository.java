/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.banashko.dbrelations.repository;

import com.banashko.dbrelations.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author banashko.dv
 */
@Repository
public interface AuthorRepository extends JpaRepository <Author, Long> {
    
}
