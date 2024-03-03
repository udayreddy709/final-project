package org.jsp.finalproject.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.finalproject.dto.Address;
import org.jsp.finalproject.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDao {
	@Autowired
	private AddressRepository addressRepository;
	
	

	public List<Address> findByCountry(String country) {
		return addressRepository.findByCountry(country);
	}
	
	public List<Address> findByStreet(String street) {
		return addressRepository.findByStreet(street);
	}

	public List<Address> findByCity(String city) {
		return addressRepository.findByCity(city);
	}

	
	public Optional<Address> verify(String city, int pincode) {
		return addressRepository.verify(city, pincode);
	}
	
	
	
}
