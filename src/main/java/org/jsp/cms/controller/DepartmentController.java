package org.jsp.cms.controller;

import org.jsp.cms.entity.Department;
import org.jsp.cms.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@Operation(summary = "To Save All the Departments...", description = "This API will accept a Department JSON Object and saves it to the Database Table and Returns the Saved Department Entity Object with the Id which is generated automatically...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Department saved successfully..."),
			@ApiResponse(responseCode = "400", description = "Bad request... Unable to save Department") })
	@PostMapping
	public ResponseEntity<?> saveDepartment(@RequestBody Department department) {

		return departmentService.saveDepartment(department);
	}

	@Operation(summary = "To Fetch All Departments", description = "This API will Fetch All the Departments  Available in the Database Table...")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "All the Departments Found Successfully..."),
			@ApiResponse(responseCode = "404", description = "No Department Present in the Database Table...") })
	@GetMapping
	public ResponseEntity<?> findAllDepartments() {

		return departmentService.findAllDepartments();
	}

	@Operation(summary = "To Fetch Administrator By Id", description = "This API will Fetch the Administrator By Id  Available in the Database Table...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Administrator Found Successfully..."),
			@ApiResponse(responseCode = "404", description = "No Administrator Present in the Database Table...") })
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findDepartmentById(@PathVariable int id) {

		return departmentService.findDepartmentById(id);
	}

	@Operation(summary = "To update Administrator  in the database table...", description = "This API will accept an Administrator JSON Object and update according to Administrator and saves it to the Database Table and Returns the Saved Administrator Entity Object by using Id ...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Administrator updated successfully..."),
			@ApiResponse(responseCode = "400", description = "Invalid Administrator Id... Unable to update Administrator") })
	@PutMapping
	public ResponseEntity<?> updateDepartment(@RequestBody Department department) {

		return departmentService.updateDepartment(department);
	}

	@Operation(summary = "To Delete Administrator By Id", description = "This API will Fetch the Administrator and Delete By Id  Available in the Database Table...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Administrator Deleted Successfully..."),
			@ApiResponse(responseCode = "404", description = "No Administrator Present in the Database Table...") })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteDepartmentById(@PathVariable int id) {

		return departmentService.deleteDepartmentById(id);
	}
}
