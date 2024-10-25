package org.jsp.cms.controller;

import org.jsp.cms.entity.User;
import org.jsp.cms.service.UserService;
import org.jsp.cms.util.AuthOTP;
import org.jsp.cms.util.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping(value = "/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	private UserService userService;

	@Operation(summary = "To Save All the Users...", description = "This API will accept a User JSON Object and saves it to the Database Table and Returns the Saved User Entity Object with the Id which is generated automatically...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User saved successfully..."),
			@ApiResponse(responseCode = "400", description = "Bad request... Unable to save User") })
	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody User user) {

		return userService.saveUser(user);
	}

	@Operation(summary = "To Fetch All Users", description = "This API will Fetch All the Users  Available in the Database Table...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "All the Users Found Successfully..."),
			@ApiResponse(responseCode = "404", description = "No User Present in the Database Table...") })
	@GetMapping
	public ResponseEntity<?> findAllUsers() {

		return userService.findAllUsers();
	}

	@Operation(summary = "To Fetch User By Id", description = "This API will Fetch the User By Id  Available in the Database Table...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User Found Successfully..."),
			@ApiResponse(responseCode = "404", description = "No User Present in the Database Table...") })
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findUserById(@PathVariable int id) {

		return userService.findUserById(id);
	}

	@Operation(summary = "To update User in the database table...", description = "This API will accept a User JSON Object and update according to User and saves it to the Database Table and Returns the Saved User Entity Object by using Id ...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User updated successfully..."),
			@ApiResponse(responseCode = "400", description = "Invalid User Id... Unable to update User") })
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody User user) {

		return userService.updateUser(user);
	}

	@Operation(summary = "To Delete User By Id", description = "This API will Fetch the User and Delete By Id  Available in the Database Table...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User Deleted Successfully..."),
			@ApiResponse(responseCode = "404", description = "No User Present in the Database Table...") })
//	@Hidden // to hide
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable int id) {

		return userService.deleteUserById(id);
	}

	@Operation(summary = "To login based on username and password", description = "This API will do login the User with the help of AuthUser class (which is used for utility purpose to send the username and password in secure) based on username and password which is Available in the Database Table...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User logged in Successfully..."),
			@ApiResponse(responseCode = "404", description = "Invalid username or password, please check and try again") })
	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody AuthUser authUser) {

		return userService.login(authUser);
	}

	@Operation(summary = "To set User Status as ACTIVE", description = "This API will accept an User JSON Object, set the User status as ACTIVE and update according to User and saves it to the Database Table and Returns the Saved User Entity Object by using Id ...")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User Status Set to ACTIVE Successfully..."),
			@ApiResponse(responseCode = "404", description = "Invalid User Id... Unable to set User as ACTIVE") })
	@PatchMapping(value = "/active/{id}")
	public ResponseEntity<?> setStatusToActive(@PathVariable int id) {

		return userService.setStatusToActive(id);
	}

	@Operation(summary = "To set User Status as IN_ACTIVE", description = "This API will accept an User JSON Object, set the User status as IN_ACTIVE and update according to User and saves it to the Database Table and Returns the Saved User Entity Object by using Id ...")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User Status Set to IN_ACTIVE Successfully..."),
			@ApiResponse(responseCode = "404", description = "Invalid User Id... Unable to set User as IN_ACTIVE") })
	@PatchMapping(value = "/inactive/{id}")
	public ResponseEntity<?> setStatusToInActive(@PathVariable int id) {

		return userService.setStatusToInActive(id);
	}

	@Operation(summary = "To set User Status as BLOCKED", description = "This API will accept an User JSON Object, set the User status as BLOCKED and update according to User and saves it to the Database Table and Returns the Saved User Entity Object by using Id ...")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User Status Set to BLOCKED Successfully..."),
			@ApiResponse(responseCode = "404", description = "Invalid User Id... Unable to set User as BLOCKED") })
	@PatchMapping(value = "/blocked/{id}")
	public ResponseEntity<?> setStatusToBlocked(@PathVariable int id) {

		return userService.setStatusToBlocked(id);
	}

	@Operation(summary = "To Fetch All ACTIVE Users", description = "This API will Fetch All ACTIVE Users  Available in the Database Table...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "All ACTIVE Users Found Successfully..."),
			@ApiResponse(responseCode = "404", description = "No ACTIVE Users Present in the Database Table...") })
	@GetMapping(value = "/active")
	public ResponseEntity<?> findAllActiveUsers() {
		return userService.findAllActiveUsers();
	}

	@Operation(summary = "To Fetch All IN_ACTIVE Users", description = "This API will Fetch All IN_ACTIVE Users  Available in the Database Table...")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "All IN_ACTIVE Users Found Successfully..."),
			@ApiResponse(responseCode = "404", description = "No IN_ACTIVE Users Present in the Database Table...") })
	@GetMapping(value = "/inactive")
	public ResponseEntity<?> findAllInActiveUsers() {
		return userService.findAllInActiveUsers();
	}

	@Operation(summary = "To Fetch All BLOCKED Users", description = "This API will Fetch All BLOCKED Users  Available in the Database Table...")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "All BLOCKED Users Found Successfully..."),
			@ApiResponse(responseCode = "404", description = "No BLOCKED Users Present in the Database Table...") })
	@GetMapping(value = "/blocked")
	public ResponseEntity<?> findAllBlockedUsers() {
		return userService.findAllBlockedUsers();
	}

	@PostMapping(value = "/otp/verify")
	public ResponseEntity<?> verifyOTP(@RequestBody AuthOTP authOTP) {

		return userService.verifyOTP(authOTP);
	}

}
