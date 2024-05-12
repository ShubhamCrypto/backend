package dev.shubham.productcatalog.inheritancedemo.joinedtable.repository;

import dev.shubham.productcatalog.inheritancedemo.joinedtable.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("JoinedTable_mentorRepo")
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    @Override
    <S extends Mentor> S save(S entity);
}
