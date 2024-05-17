package com.example.operazioniCRUD.controller;

import com.example.operazioniCRUD.model.Car;
import com.example.operazioniCRUD.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()) {
            return ResponseEntity.ok(car.get());
        } else {
            return ResponseEntity.ok(new Car());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCarType(@PathVariable Long id, @RequestParam String type) {
        if (carRepository.existsById(id)) {
            Car car = carRepository.findById(id).get();
            car.setType(type);
            carRepository.save(car);
            return ResponseEntity.ok(car);
        } else {
            return ResponseEntity.ok(new Car());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping
    public void deleteAllCars() {
        carRepository.deleteAll();
    }
}
