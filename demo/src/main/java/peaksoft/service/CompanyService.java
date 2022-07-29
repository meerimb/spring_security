package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.company.CompanyRequest;
import peaksoft.dto.company.CompanyResponse;
import peaksoft.entity.Company;
import peaksoft.repository.CompanyRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyResponse create(CompanyRequest request) {
        Company company = mapToEntity(request);
        repository.save(company);
        return mapToResponse(company);
    }

    public CompanyResponse update(long id, CompanyRequest request) {
        Optional<Company> company = repository.findById(id);
        if (company.isEmpty()) {
            System.out.println("company is not found");
        }
        mapToUpdate(company.get(), request);
        return mapToResponse(repository.save(company.get()));

    }

    public CompanyResponse getById(long id) {

        Optional<Company> company = repository.findById(id);
        if (company.isEmpty()) {
            System.out.println("company is not found");
        }
        return mapToResponse(repository.findById(id).get());
    }

    public CompanyResponse delete(long id) {
        Company company = repository.findById(id).get();
        repository.deleteById(id);
        return mapToResponse(company);
    }

    public List<Company> getAll() {
        return repository.findAll();
    }

    public Company mapToEntity(CompanyRequest request) {
        Company company = new Company();
        company.setCompanyName(request.getCompanyName());
        company.setLocatedCountry(request.getLocatedCountry());
        company.setCreated(LocalDateTime.now());
        return company;

    }

    public CompanyResponse mapToResponse(Company company) {
        return CompanyResponse.builder()
                .companyName(company.getCompanyName())
                .locatedCountry(company.getLocatedCountry())
                .created(company.getCreated())
                .id(company.getId())
                .build();

    }

    public Company mapToUpdate(Company company, CompanyRequest request) {
        List<Company> companies = new ArrayList<>();
        company.setCompanyName(request.getCompanyName());
        company.setLocatedCountry(request.getLocatedCountry());
        company.setCreated(LocalDateTime.now());
        return company;
    }

    public List<CompanyResponse> map(List<Company> companies) {
        List<CompanyResponse> responses = new ArrayList<>();
        for (Company company : companies) {
            responses.add(mapToResponse(company));
        }
        return responses;
    }
}