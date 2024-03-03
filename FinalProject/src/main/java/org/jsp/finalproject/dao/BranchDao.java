package org.jsp.finalproject.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.finalproject.dto.Branch;
import org.jsp.finalproject.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BranchDao {
	@Autowired
	private BranchRepository branchRepository;
	
	public Branch saveBranch(Branch branch) {
		return branchRepository.save(branch);
	}

	public Optional<Branch> findById(int id) {
		return branchRepository.findById(id);
	}

	public List<Branch> findAll() {
		return branchRepository.findAll();
	}

	public List<Branch> findByName(String name) {
		return branchRepository.findByName(name);
	}

	public boolean deleteById(int id) {
		Optional<Branch> recBranch = findById(id);
		if (recBranch.isPresent()) {
			branchRepository.delete(recBranch.get());
			return true;
		}
		return false;
	}

	public Optional<Branch> verify(long phone, String email) {
		return branchRepository.verify(phone, email);
	}

}
