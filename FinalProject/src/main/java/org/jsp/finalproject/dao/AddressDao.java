package org.jsp.finalproject.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.finalproject.dto.Address;
import org.jsp.finalproject.dto.Branch;
import org.jsp.finalproject.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDao {
	@Autowired
	private AddressRepository addressRepository;
	
	public Address saveAddress(Address address) {
		return addressRepository.save(address);
	}

	public boolean deleteById(int id) {
		Optional<Address> recAddress = findById(id);
		if (recAddress.isPresent()) {
			addressRepository.delete(recAddress.get());
			return true;
		}
		return false;
	}

	

	private Optional<Address> findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Address> findByCountry(String country) {
		return addressRepository.findByCountry(country);
	}

	public Optional<Address> verify(String city, int pincode) {
		return addressRepository.verify(city, pincode);
	}

}
