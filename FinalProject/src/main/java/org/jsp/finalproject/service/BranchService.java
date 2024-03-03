package org.jsp.finalproject.service;

import java.util.List;
import java.util.Optional;

import org.jsp.finalproject.dao.BranchDao;
import org.jsp.finalproject.dto.Branch;
import org.jsp.finalproject.dto.ResponseStructure;
import org.jsp.finalproject.exception.IdNotFoundException;
import org.jsp.finalproject.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BranchService {
	@Autowired
	private BranchDao branchDao;
	

	public ResponseStructure<Branch> saveBranch(Branch branch) {
		ResponseStructure<Branch> structure = new ResponseStructure<>();
		structure.setMessage("branch saved");
		structure.setData(branchDao.saveBranch(branch));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return structure;
	}
	
	
	public ResponseEntity<ResponseStructure<Branch>> updateBranch(Branch branch) {
		Optional<Branch> recBranch = branchDao.findById(branch.getId());
		ResponseStructure<Branch> structure = new ResponseStructure<>();
		if (recBranch.isPresent()) {
			Branch dbBranch = recBranch.get();
			dbBranch.setEmail(branch.getEmail());
			dbBranch.setName(branch.getName());
			dbBranch.setPhone(branch.getPhone());
			structure.setMessage("Branch Updated");
			structure.setData(branchDao.saveBranch(branch));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
}
	
	
	
	public ResponseEntity<ResponseStructure<Branch>> findById(int id) {
		Optional<Branch> recBranch = branchDao.findById(id);
		ResponseStructure<Branch> structure = new ResponseStructure<>();
		if (recBranch.isPresent()) {
			structure.setData(recBranch.get());
			structure.setMessage("Branch Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
		
}
	
	public ResponseEntity<ResponseStructure<String>> deleteById(int id) {
		Optional<Branch> recBranch = branchDao.findById(id);
		ResponseStructure<String> structure = new ResponseStructure<>();
		if (recBranch.isPresent()) {
			structure.setMessage("branch found");
			structure.setData("branch deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			branchDao.deleteById(id);
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		structure.setMessage("branch Not found");
		structure.setData("cannot delete branch as Id is Invalid");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	public ResponseStructure<List<Branch>> findAll() {
		ResponseStructure<List<Branch>> structure = new ResponseStructure<>();
		structure.setMessage("List of branchs");
		structure.setData(branchDao.findAll());
		structure.setStatusCode(HttpStatus.OK.value());
		return structure;
	}
	
	public ResponseEntity<ResponseStructure<Branch>> verifyBranch(long phone, String email) {
		Optional<Branch> recBranch = branchDao.verify(phone, email);
		ResponseStructure<Branch> structure = new ResponseStructure<>();
		if (recBranch.isPresent()) {
			structure.setMessage("Verification Succesfull");
			structure.setData(recBranch.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("invalid phone or password");
	}
	
	
	public ResponseEntity<ResponseStructure<List<Branch>>> findByName(String name) {
		ResponseStructure<List<Branch>> structure = new ResponseStructure<>();
		List<Branch> branchs = branchDao.findByName(name);
		structure.setData(branchs);
		if (branchs.size() > 0) {
			structure.setMessage("List of branchs with entered name ");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Branch>>>(structure, HttpStatus.OK);
		}
		structure.setMessage("No Branch found with the entered name ");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<List<Branch>>>(structure, HttpStatus.NOT_FOUND);
	}
	}
