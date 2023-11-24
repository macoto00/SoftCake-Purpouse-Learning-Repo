package com.example.liquibasedemoprojecttwo.content.repositories;

import com.example.liquibasedemoprojecttwo.content.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Post, Long> {
}
