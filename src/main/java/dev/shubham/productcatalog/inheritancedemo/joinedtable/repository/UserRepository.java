package dev.shubham.productcatalog.inheritancedemo.joinedtable.repository;

import dev.shubham.productcatalog.inheritancedemo.joinedtable.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("JoinedTable_userRepo")
public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    <S extends User> S save(S entity);
}
