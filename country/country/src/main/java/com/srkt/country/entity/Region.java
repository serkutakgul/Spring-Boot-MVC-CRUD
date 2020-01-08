package com.srkt.country.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "REGIONS")
public class Region {
	@Id
	@Column(name = "region_id")
	private Integer regionId;
	@Column(name="region_name")
	public String regionName;
	@OneToMany(mappedBy = "region", cascade = {CascadeType.DETACH,CascadeType.MERGE,
											CascadeType.PERSIST,CascadeType.REFRESH})
									//Country sınıfındaki region propertysine bak
	private List<Country> countries;//Country classındaki joincolumn bilgiyi kıta için atanan ülkeleri
									//bulmak için kullan
	public Integer getRegionId() {
		return regionId;
	}
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public List<Country> getCountries() {
		return countries;
	}
	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

}
