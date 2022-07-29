package peaksoft.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Group;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("select s from Group s inner join s.courses c where upper(s.groupName) " +
            "like concat('%', :name, '%') or upper(c.courseName) like concat('%', :name, '%') ")
    List<Group> searchAndPagination(@Param("name") String name, Pageable pageable);
}