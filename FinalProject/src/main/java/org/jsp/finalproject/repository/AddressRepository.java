package org.jsp.finalproject.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.finalproject.dto.Address;
import org.jsp.finalproject.dto.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Branch, Integer> {
	@Query("select a from Address a where a.city=?1 and b.pincode=?2")
	public Optional<Address> verify(String city, int pincode);
	
	@Query("select b from Address b where b.street=?1")
	public List<Address> findByStreet(String street);

	@Query("select b from Address b where b.city=?1")
	public List<Address> findByCity(String city);

	@Query("select b from Address b where b.country=?1")
	public List<Address> findByCountry(String country);

}
