package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {


}