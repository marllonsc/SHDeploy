package com.marllonsc.br.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.marllonsc.br.entity.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByPathProject(String pathProject);

    List<Project> findByAtivo(int ativo);

    @Query("SELECT p FROM Project p WHERE p.name = :name AND p.ativo = :ativo")
    Project findByNameAndAtivo(@Param("name") String name, @Param("ativo") int ativo);
}
