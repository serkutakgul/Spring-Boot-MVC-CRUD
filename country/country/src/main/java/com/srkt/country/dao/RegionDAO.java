package com.srkt.country.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srkt.country.entity.Region;

public interface RegionDAO extends JpaRepository<Region, Integer>{

}
