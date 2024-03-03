package org.jsp.finalproject.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.finalproject.dto.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BranchRepository extends JpaRepository<Branch, Integer>{
	
	@Query("select b from Branch b where b.phone=?1 and b.email=?2")
	public Optional<Branch> verify(long phone, String email);
	
	@Query("select b from Branch b where b.name=?1")
	public List<Branch> findByName(String name);


}





