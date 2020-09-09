package com.example.tacocloud.Repositories;

import com.example.tacocloud.Models.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
