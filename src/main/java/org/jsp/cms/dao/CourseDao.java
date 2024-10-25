package org.jsp.cms.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.cms.entity.Course;

public interface CourseDao {

	Course saveCourse(Course course);

	List<Course> findAllCourses();

	Optional<Course> findCourseById(int id);

	Course updateCourse(Course course);

	void deleteCourseById(int id);

}
