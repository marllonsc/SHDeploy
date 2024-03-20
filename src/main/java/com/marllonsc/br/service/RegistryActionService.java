package com.marllonsc.br.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marllonsc.br.entity.RegistryAction;
import com.marllonsc.br.repository.RegistryActionRepository;

import java.util.List;

@Service
public class RegistryActionService {

    private final RegistryActionRepository registryActionRepository;

    @Autowired
    public RegistryActionService(RegistryActionRepository registryActionRepository) {
        this.registryActionRepository = registryActionRepository;
    }

    // Method to retrieve all RegistryAction instances with their associated
    // projects
    public List<RegistryAction> findAllWithProject() {
        return registryActionRepository.findAllWithProject();
    }

    // Method to insert a new RegistryAction instance
    public RegistryAction insertRegistryAction(RegistryAction registryAction) {
        return registryActionRepository.save(registryAction);
    }
}
