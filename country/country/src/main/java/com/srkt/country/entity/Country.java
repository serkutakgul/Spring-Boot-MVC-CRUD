package com.srkt.country.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

@Entity
@Table(name = "COUNTRIES")
public class Country {
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "REGION_ID")
	private Region region;
	
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	@Id
	@Column(name = "COUNTRY_ID")
	@Size(min=2,max=2,message = "Iki Karakter Giriniz")
	private	String countryId;
	@Column(name = "COUNTRY_NAME")
	@NotEmpty(message = "Bos Gecilemez")
	private String countryName;
	
	
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	
	@Override
	public String toString() {
		return "Country [region=" + region + ", countryId=" + countryId + ", countryName=" + countryName + "]";
	}
	public Country() {
		
	}
	

}
