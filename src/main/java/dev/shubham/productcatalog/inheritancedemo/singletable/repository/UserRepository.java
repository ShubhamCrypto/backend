package dev.shubham.productcatalog.inheritancedemo.singletable.repository;

import dev.shubham.productcatalog.inheritancedemo.singletable.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    <S extends User> S save(S entity);
}
