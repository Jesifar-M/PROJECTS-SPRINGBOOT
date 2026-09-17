package com.example.MobileApp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MobileRepository extends JpaRepository<MobileModel, Long> {

    // 1. All phone names and prices
    @Query("SELECT m.name, m.price FROM MobileModel m")
    List<Object[]> findNameAndPrice();

    // 2. Phones costing less than 20,000
    @Query("SELECT m FROM MobileModel m WHERE m.price < 20000")
    List<MobileModel> findBelow20000();

    // 3. Total phones grouped by type
    @Query("SELECT m.type, COUNT(m) FROM MobileModel m GROUP BY m.type")
    List<Object[]> countByType();
}