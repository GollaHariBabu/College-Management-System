package org.jsp.cms.controller;

import org.jsp.cms.entity.Course;
import org.jsp.cms.service.CourseService;
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
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@Operation(summary = "To Save All the Courses...", description = "This API will accept a Course JSON Object and saves it to the Database Table and Returns the Saved Course Entity Object with the Id which is generated automatically...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Course saved successfully..."),
			@ApiResponse(responseCode = "400", description = "Bad request... Unable to save Course") })
	@PostMapping
	public ResponseEntity<?> saveCourse(@RequestBody Course course) {

		return courseService.saveCourse(course);
	}

	@Operation(summary = "To Fetch All Courses", description = "This API will Fetch All the Courses  Available in the Database Table...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "All the Courses Found Successfully..."),
			@ApiResponse(responseCode = "404", description = "No Course Present in the Database Table...") })
	@GetMapping
	public ResponseEntity<?> findAllCourses() {
		return courseService.findAllCourses();
	}

	@Operation(summary = "To Fetch Course By Id", description = "This API will Fetch the Course By Id  Available in the Database Table...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Course Found Successfully..."),
			@ApiResponse(responseCode = "404", description = "Course Id is not Present in the Database Table...") })
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findCourseById(@PathVariable int id) {
		return courseService.findCourseById(id);
	}

	@Operation(summary = "To update Course using Id...", description = "This API will accept an Course JSON Object and update according to Course and saves it to the Database Table and Returns the Saved Course Entity Object by using Id ...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Course updated successfully..."),
			@ApiResponse(responseCode = "400", description = "Invalid Course Id... Unable to update Course") })
	@PutMapping
	public ResponseEntity<?> updateCourse(@RequestBody Course course) {

		return courseService.updateCourse(course);
	}

	@Operation(summary = "To Delete Course By Id", description = "This API will Fetch the Course and Delete By Id  Available in the Database Table...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Course Deleted Successfully..."),
			@ApiResponse(responseCode = "404", description = "Course is not Present in the Database Table...") })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteCourseById(@PathVariable int id) {

		return courseService.deleteCourseById(id);
	}

	@Operation(summary = "To set Faculty to Course by Id", description = "This API will Fetch the Faculty and Course By Id  Available in the Database Table and set the Faculty to Course...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Faculty set to Course successfully..."),
			@ApiResponse(responseCode = "404", description = "Invalid Faculty or Course Id... Unable to set Faculty to Course...") })
	@PatchMapping(value = "/faculty/{cid}/{fid}")
	public ResponseEntity<?> setFacultyToCourse(@PathVariable int cid, @PathVariable int fid) {

		return courseService.setFacultyToCourse(cid, fid);
	}

	@Operation(summary = "To set Department to Course by Id", description = "This API will Fetch the Department and Course By Id  Available in the Database Table and set the Department to Course...")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Department set to Course successfully..."),
			@ApiResponse(responseCode = "404", description = "Invalid Department or Course Id... Unable to set Department to Course...") })
	@PatchMapping(value = "/department/{cid}/{did}")
	public ResponseEntity<?> setDepartmentToCourse(@PathVariable int cid, @PathVariable int did) {

		return courseService.setDepartmentToCourse(cid, did);
	}

}
