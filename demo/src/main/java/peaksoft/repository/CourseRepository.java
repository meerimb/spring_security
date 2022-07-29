package peaksoft.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Course;

import java.util.List;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select s from Course s inner join s.company c where upper(s.courseName) " +
            "like concat('%', :name, '%') or upper(c.companyName) like concat('%', :name, '%') ")
    List<Course> searchAndPagination(@Param("name") String name, Pageable pageable);
}