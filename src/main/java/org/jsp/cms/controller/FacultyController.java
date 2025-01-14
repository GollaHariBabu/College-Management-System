package org.jsp.cms.controller;

import java.time.LocalTime;

import org.jsp.cms.entity.Faculty;
import org.jsp.cms.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/faculties")
public class FacultyController {

	@Autowired
	private FacultyService facultyService;

	@Operation(summary = "To Save All the faculties according to User Id...", description = "This API will fetch the User Id and according to that User Id it will accept the  Faculty JSON Object and saves it to the Database Table and Returns the Saved Faculty Entity Object with the Id which is generated by User Id...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Faculty saved successfully..."),
			@ApiResponse(responseCode = "400", description = "Invalid User Id... Unable to save Faculty") })
	@PostMapping(value = "/{uid}")
	public ResponseEntity<?> saveFaculty(@RequestBody Faculty faculty, @PathVariable int uid) {

		return facultyService.saveFaculty(faculty, uid);
	}

	@Operation(summary = "To set Department to Faculty by Id", description = "This API will Fetch the Department and Faculty By Id  Available in the Database Table and set the Department to Faculty...")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Department set to Faculty successfully..."),
			@ApiResponse(responseCode = "404", description = "Invalid Department or Faculty Id... Unable to set Department to Faculty...") })
	@PatchMapping(value = "/department/{fid}/{did}")
	public ResponseEntity<?> setDepartmentToFaculty(@PathVariable int fid, @PathVariable int did) {

		return facultyService.setDepartmentToFaculty(fid, did);
	}

	@Operation(summary = "To Fetch All faculties", description = "This API will Fetch All the faculties  Available in the Database Table...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "All the faculties Found Successfully..."),
			@ApiResponse(responseCode = "404", description = "No Faculty Present in the Database Table...") })
	@GetMapping
	public ResponseEntity<?> findAllFaculties() {

		return facultyService.findAllFaculties();
	}

	@Operation(summary = "To Fetch Faculty By Id", description = "This API will Fetch the Faculty By Id  Available in the Database Table...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Faculty Found Successfully..."),
			@ApiResponse(responseCode = "404", description = "No Faculty Present in the Database Table...") })
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findFacultyById(@PathVariable int id) {

		return facultyService.findFacultyById(id);
	}

	@Operation(summary = "To Delete Faculty By Id", description = "This API will Fetch the Faculty and Delete By Id  Available in the Database Table...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Faculty Deleted Successfully..."),
			@ApiResponse(responseCode = "404", description = "No Faculty Present in the Database Table...") })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteFacultyById(@PathVariable int id) {

		return facultyService.deleteFacultyById(id);
	}

	@Operation(summary = "To update the Faculty ...", description = "This API will accept an Faculty JSON Object and update according to Faculty and saves it to the Database Table and Returns the Saved Faculty Entity Object ...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Faculty updated successfully..."),
			@ApiResponse(responseCode = "400", description = "Invalid Faculty Id... Unable to update Faculty") })
	@PutMapping
	public ResponseEntity<?> updateFaculty(@RequestBody Faculty faculty) {

		return facultyService.updateFaculty(faculty);
	}

	@Operation(summary = "To set officeHours to Faculty by Id", description = "This API will Fetch the Faculty By Id  Available in the Database Table and set the officeHours to Faculty...")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "officeHours set to Faculty successfully..."),
			@ApiResponse(responseCode = "404", description = "Invalid Faculty Id... Unable to set officeHours to Faculty...") })
	@PatchMapping(value = "/officeHours/{fid}")
	public ResponseEntity<?> setOfficeHoursToFaculty(@RequestParam LocalTime officeHours, @PathVariable int fid) {

		return facultyService.setOfficeHoursToFaculty(officeHours, fid);
	}

	@Operation(summary = "To upload photo to  Faculty By Id", description = "This API will Fetch the Faculty and upload profile photo based on Id  Available in the Database Table...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "profile photo uploaded Successfully..."),
			@ApiResponse(responseCode = "404", description = "No Faculty Present in the Database Table...") })
	@PostMapping("/upload/{fid}")
	public ResponseEntity<?> uploadPhoto(@PathVariable int fid, @RequestParam MultipartFile file) {

		return facultyService.uploadPhoto(fid, file);
	}
}
