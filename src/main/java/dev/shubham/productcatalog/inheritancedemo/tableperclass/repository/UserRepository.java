package dev.shubham.productcatalog.inheritancedemo.tableperclass.repository;

import dev.shubham.productcatalog.inheritancedemo.tableperclass.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("tablePerClass_userRepo")
public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    <S extends User> S save(S entity);
}
