package com.example.operazioniCRUD.repository;

import com.example.operazioniCRUD.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
