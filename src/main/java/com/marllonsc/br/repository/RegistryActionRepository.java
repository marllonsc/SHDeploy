package com.marllonsc.br.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.marllonsc.br.entity.RegistryAction;

import java.util.List;

@Repository
public interface RegistryActionRepository extends JpaRepository<RegistryAction, Long> {

    // Method to retrieve all RegistryAction instances with their associated
    // projects
    @Query("SELECT ra FROM RegistryAction ra LEFT JOIN FETCH ra.projectId")
    List<RegistryAction> findAllWithProject();

    @Query("SELECT ra FROM RegistryAction ra JOIN FETCH ra.projectId ORDER BY ra.dateExecuted DESC")
    Page<RegistryAction> findAllWithProjectOrderedByIdDesc(Pageable pageable);

}
