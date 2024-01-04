/*

package com.example.cucumberdemo.config;

import com.example.cucumberdemo.models.User;
import com.example.cucumberdemo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@Deprecated // Should be replaced with Liquibase in the future
public class DatabaseFiller implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        List<User> initialUsers = createInitialUsers();
        List<User> usersToAdd = new ArrayList<>();

        for (User user : initialUsers) {
            if (!userRepository.existsUserByEmail(user.getEmail())) {
                usersToAdd.add(user);
            }
        }

        if (!usersToAdd.isEmpty()) {
            userRepository.saveAll(usersToAdd);
        }
    }

    private List<User> createInitialUsers() {
        User user1 = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();

        User user2 = User.builder()
                .firstName("Jane")
                .lastName("Smith")
                .email("jane@example.com")
                .build();

        User user3 = User.builder()
                .firstName("Alice")
                .lastName("Johnson")
                .email("alice@example.com")
                .build();

        return List.of(user1, user2, user3);
    }

}

 */