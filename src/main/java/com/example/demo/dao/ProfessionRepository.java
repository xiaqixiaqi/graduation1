package com.example.demo.dao;

import com.example.demo.po.Profession;
import org.springframework.data.repository.CrudRepository;

public interface ProfessionRepository extends CrudRepository<Profession,Integer> {
}
