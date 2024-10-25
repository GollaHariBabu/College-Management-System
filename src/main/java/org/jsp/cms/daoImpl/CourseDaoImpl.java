package org.jsp.cms.daoImpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cms.dao.CourseDao;
import org.jsp.cms.entity.Course;
import org.jsp.cms.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDaoImpl implements CourseDao {

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public Course saveCourse(Course course) {

		return courseRepository.save(course);
	}

	@Override
	public List<Course> findAllCourses() {

		return courseRepository.findAll();
	}

	@Override
	public Optional<Course> findCourseById(int id) {

		return courseRepository.findById(id);
	}

	@Override
	public Course updateCourse(Course course) {

		return courseRepository.save(course);
	}

	@Override
	public void deleteCourseById(int id) {

		courseRepository.deleteById(id);
	}

}
