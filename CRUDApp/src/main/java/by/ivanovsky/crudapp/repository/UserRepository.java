package by.ivanovsky.crudapp.repository;

import by.ivanovsky.crudapp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
