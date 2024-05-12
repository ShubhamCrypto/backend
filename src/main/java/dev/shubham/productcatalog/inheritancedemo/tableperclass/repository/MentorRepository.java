package dev.shubham.productcatalog.inheritancedemo.tableperclass.repository;

import dev.shubham.productcatalog.inheritancedemo.tableperclass.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("tablePerClass_mentorRepo")
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    @Override
    <S extends Mentor> S save(S entity);
}
