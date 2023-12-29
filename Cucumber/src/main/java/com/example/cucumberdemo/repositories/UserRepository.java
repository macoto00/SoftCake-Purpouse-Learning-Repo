package com.example.cucumberdemo.repositories;

import com.example.cucumberdemo.models.User;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(String email);
    User findByEmail(String email);

    default void checkIfExistByEmailAndThrow(String email, Long userId) {
        if (existsUserByEmail(email)) {
            if (!Objects.equals(findByEmail(email).getId(), userId)) {
                throw new EntityExistsException("Entity with email " + email + " already exists.");
            }
        }
    }

    default User getUserByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
    }
}

