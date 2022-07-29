package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.company.CompanyRequest;
import peaksoft.dto.company.CompanyResponse;
import peaksoft.entity.Company;
import peaksoft.service.CompanyService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
@PreAuthorize("hasAnyAuthority('ADMIN','EDITOR')")

public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public CompanyResponse create(@RequestBody CompanyRequest request) {
        return companyService.create(request);
    }

    @PutMapping("{id}")
    public CompanyResponse update(@PathVariable long id,
                                 @RequestBody CompanyRequest request) {
        return companyService.update(id, request);
    }

    @GetMapping(("{id}"))
    public CompanyResponse getById(@PathVariable long id) {
        return companyService.getById(id);
    }

    @DeleteMapping("{id}")
    public CompanyResponse delete(@PathVariable long id) {
        return companyService.delete(id);
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAll();
    }
}

