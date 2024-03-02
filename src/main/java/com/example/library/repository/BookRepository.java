package com.example.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library.entity.Books;

@Repository
public interface BookRepository extends JpaRepository<Books, Long>{

	List<Books> findBybookGenre(String genre);
}
