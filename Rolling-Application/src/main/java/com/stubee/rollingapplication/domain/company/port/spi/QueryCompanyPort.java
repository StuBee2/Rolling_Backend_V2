package com.stubee.rollingapplication.domain.company.port.spi;

import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollingcore.domain.company.dto.response.CompanyQueryResponse;
import com.stubee.rollingcore.domain.company.model.Company;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QueryCompanyPort {

    boolean existsByCompanyId(UUID companyId);

    Optional<CompanyQueryResponse> findById(UUID id);

    List<Company> findByNameContaining(String name, PageRequest pageRequest);

    List<Company> findByRegistrantId(UUID registrantId, PageRequest pageRequest);

    List<Company> findAll(PageRequest pageRequest);

    List<Company> findByTotalGrade();

    List<Company> findBySalaryAndBenefits();

    List<Company> findByWorkLifeBalance();

    List<Company> findByOrganizationalCulture();

    List<Company> findByCareerAdvancement();

}