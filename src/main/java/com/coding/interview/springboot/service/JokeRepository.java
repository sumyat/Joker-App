package com.coding.interview.springboot.service;


import com.coding.interview.springboot.model.domain.Joke;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JokeRepository extends CrudRepository<Joke, Integer> {
	Optional<Joke> findById(Integer id);

	@Query(value = "SELECT * FROM joke ORDER BY id DESC LIMIT 1", nativeQuery = true)
	Optional<Joke> findLastJoke();
}
