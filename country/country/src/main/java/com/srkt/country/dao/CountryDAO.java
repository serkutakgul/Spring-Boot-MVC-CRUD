package com.srkt.country.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.srkt.country.entity.Country;

public interface CountryDAO extends JpaRepository<Country, String> {
	
	List<Country> findByCountryNameContainingIgnoreCase(String countryName);
	
	

}
