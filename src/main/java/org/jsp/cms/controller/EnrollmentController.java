package org.jsp.cms.controller;

import org.jsp.cms.entity.Enrollment;
import org.jsp.cms.service.EnrollmentService;
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
@RequestMapping(value = "/enrollments")
public class EnrollmentController {

	@Autowired
	private EnrollmentService enrollmentService;

	@Operation(summary = "To Save All the Enrollments...", description = "This API will accept an Enrollment JSON Object and saves it to the Database Table and Returns the Saved Enrollment Entity Object with the Id which is generated automatically...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Enrollment saved successfully..."),
			@ApiResponse(responseCode = "400", description = "Bad request... Unable to save Enrollment") })
	@PostMapping(value = "/{cid}/{sid}")
	public ResponseEntity<?> saveEnrollment(@PathVariable int cid, @PathVariable int sid) {

		return enrollmentService.saveEnrollment(cid, sid);
	}

	@Operation(summary = "To Fetch All Enrollments", description = "This API will Fetch All the Enrollments  Available in the Database Table...")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "All the Enrollments Found Successfully..."),
			@ApiResponse(responseCode = "404", description = "No Enrollment Present in the Database Table...") })
	@GetMapping
	public ResponseEntity<?> findAllEnrollments() {

		return enrollmentService.findAllEnrollments();
	}

	@Operation(summary = "To Fetch Enrollment By Id", description = "This API will Fetch the Enrollment By Id  Available in the Database Table...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Enrollment Found Successfully..."),
			@ApiResponse(responseCode = "404", description = "No Enrollment Present in the Database Table...") })
	@GetMapping(value = "{id}")
	public ResponseEntity<?> findEnrollmentById(@PathVariable int id) {

		return enrollmentService.findEnrollmentById(id);
	}

	@Operation(summary = "To update Enrollment in the database table...", description = "This API will accept an Enrollment JSON Object and update according to Enrollment and saves it to the Database Table and Returns the Saved Enrollment Entity Object by using Id ...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Enrollment updated successfully..."),
			@ApiResponse(responseCode = "400", description = "Invalid Enrollment Id... Unable to update Enrollment") })
	@PutMapping
	public ResponseEntity<?> updateEnrollment(@RequestBody Enrollment enrollment) {

		return enrollmentService.updateEnrollment(enrollment);
	}

	@Operation(summary = "To Delete Enrollment By Id", description = "This API will Fetch the Enrollment and Delete By Id  Available in the Database Table...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Enrollment Deleted Successfully..."),
			@ApiResponse(responseCode = "404", description = "No Enrollment Present in the Database Table...") })
	@DeleteMapping(value = "{id}")
	public ResponseEntity<?> deleteEnrollmentById(@PathVariable int id) {

		return enrollmentService.deleteEnrollmentById(id);
	}
}
