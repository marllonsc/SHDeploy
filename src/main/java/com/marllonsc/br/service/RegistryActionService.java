package com.marllonsc.br.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marllonsc.br.entity.RegistryAction;
import com.marllonsc.br.repository.RegistryActionRepository;

import io.micrometer.common.util.StringUtils;

import java.util.List;

@Service
@Transactional
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
        if (StringUtils.isEmpty(registryAction.getUser())) {
            registryAction.setUser("Linux Server");
        }
        return registryActionRepository.save(registryAction);
    }

    public Page<RegistryAction> findPagedWithProject(int page, int pageSize) {
        // Create a PageRequest object to specify the page number and page size
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);

        // Fetch a paginated list of RegistryAction entities along with their associated
        // Project entities
        return registryActionRepository.findAllWithProjectOrderedByIdDesc(pageRequest);
    }

    public long countAll() {
        return registryActionRepository.count();
    }
}
