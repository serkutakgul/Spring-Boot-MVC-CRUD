package com.srkt.country.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.srkt.country.dao.CountryDAO;
import com.srkt.country.dao.RegionDAO;
import com.srkt.country.entity.Country;

@Controller
@RequestMapping("/ulkeler")
public class CountryController {

	private final CountryDAO countryDAO;
	private final RegionDAO regionDAO;

	public CountryController(CountryDAO thecountryDAO, RegionDAO theregionDAO) {
		super();
		this.countryDAO = thecountryDAO;
		this.regionDAO = theregionDAO;
	}

	@RequestMapping("/list")
	public String listCountries(Model model,@ModelAttribute("newCountry") String username) {

		List<Country> countries = countryDAO.findAll();
		model.addAttribute("countries", countries);
		System.out.println(username);
		return "list-countries";
	}

	@GetMapping("/eklemeFormunuGoster")
	public String ekleFormu(Model model) {

		Country newCountry = new Country();
		model.addAttribute("newCountry", newCountry);
		model.addAttribute("kitalar", regionDAO.findAll());

		return "country-form";
	}

	@PostMapping("/kaydet")
	public String kaydet(@Valid @ModelAttribute("newCountry") Country theCountry, BindingResult bindingResult,Model model) {
		// country-form th:object
		if (bindingResult.hasErrors()) {
			model.addAttribute("kitalar", regionDAO.findAll());
			return "country-form";

		} else {
			countryDAO.save(theCountry);

			return "redirect:/ulkeler/list";
		}
	}

	@GetMapping("/guncelleFormunuGoster/{countryId}")
	public String guncelleFormu(@PathVariable String countryId, Model model) {

		Country country = countryDAO.getOne(countryId);
		model.addAttribute("newCountry", country);
		model.addAttribute("kitalar", regionDAO.findAll());

		return "country-form";

	}
/*
 * @GetMapping("/sil/{countryId}")
	public String sil(@PathVariable String countryId, Model model) {

		Country country = countryDAO.getOne(countryId);
		countryDAO.delete(country);

		return "redirect:/ulkeler/list";

	}
*/
	@GetMapping("/sil")
	public String sil(@RequestParam("countryId") String countryId, Model model) {

		Country country = countryDAO.getOne(countryId);
		countryDAO.delete(country);

		return "redirect:/ulkeler/list";

	}

	@GetMapping("/searchCountry")
	public String search(@RequestParam("countryName") String countryName, Model model) {
		System.out.println(countryName);
		List<Country> ulkeListesi = countryDAO.findByCountryNameContainingIgnoreCase(countryName);
		model.addAttribute("countries", ulkeListesi);

		return "list-countries";
	}

}
