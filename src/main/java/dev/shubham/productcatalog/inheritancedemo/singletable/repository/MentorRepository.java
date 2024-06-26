package dev.shubham.productcatalog.inheritancedemo.singletable.repository;

import dev.shubham.productcatalog.inheritancedemo.singletable.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("singleTable_mentorRepo")
public interface MentorRepository extends JpaRepository<Mentor, Long> {

    @Override
    <S extends Mentor> S save(S entity);
}
