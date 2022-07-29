package peaksoft.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Student;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select s from Student s where upper(s.firstname) like concat('%',:name,'%') "+
            "or upper(s.email) like concat('%', :name, '%') or upper(s.studyFormat)" +
            "like concat('%', :name, '%')")
    List<Student> searchAndPagination(@Param("name") String name, Pageable pageable);
}
