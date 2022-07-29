package peaksoft.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Student;
import peaksoft.entity.Teacher;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("select s from Teacher s where upper(s.firstname) like concat('%',:name,'%') "+
            "or upper(s.email) like concat('%', :name, '%') or upper(s.lastname)" +
            "like concat('%', :name, '%')")
    List<Teacher> searchAndPagination(@Param("name") String name, Pageable pageable);
}