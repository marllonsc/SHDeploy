package com.marllonsc.br.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.marllonsc.br.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByPathProject(String pathProject);

    List<Project> findByAtivo(int ativo);
}
