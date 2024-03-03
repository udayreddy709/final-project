package org.jsp.finalproject.controller;

import java.util.List;

import org.jsp.finalproject.dto.Branch;
import org.jsp.finalproject.dto.ResponseStructure;
import org.jsp.finalproject.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/branches")
public class BranchController {
  @Autowired
	private BranchService branchService;
  
  
  @PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseStructure<Branch> saveMerchant(@RequestBody Branch branch) {
		return branchService.saveBranch(branch);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Branch>> updateBranch(@RequestBody Branch branch) {
		return branchService.updateBranch(branch);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<Branch>> findById(@PathVariable(name = "id") int id) {
		return branchService.findById(id);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable(name = "id") int id) {
		return branchService.deleteById(id);
	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseStructure<List<Branch>> findAll() {
		return branchService.findAll();
	}

	@PostMapping("/verify-by-phone")
	public ResponseEntity<ResponseStructure<Branch>> verifyMerchant(@RequestParam long phone,
			@RequestParam String email) {
		return branchService.verifyBranch(phone, email);
	}

	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<ResponseStructure<List<Branch>>> findByName(@PathVariable String name) {
		return branchService.findByName(name);
	}
}
