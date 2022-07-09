package com.example.examprepbattleship.repository;


import com.example.examprepbattleship.model.entity.Ship;
import com.example.examprepbattleship.model.service.ShipServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {

    Optional<Ship> findByName(String name);

    @Query("select s from Ship s where s.user.id = ?1")
    List<Ship> findAllByUserId(long id);

    @Query("select s from Ship s where s.user.id <> ?1")
    List<Ship> findAllByUserIdIsNot(long id);

    @Query("select s from Ship s order by s.name, s.health, s.power")
    List<Ship> getAllBy();

    Ship findById(long id);

}
