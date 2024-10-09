package com.example.demo.Controller;

  import com.example.demo.model.Course;
  import org.springframework.web.bind.annotation.*;
  import java.util.ArrayList;
  import java.util.List;
  import java.util.Optional;

	    @RestController
	    @RequestMapping("/courses")
	    public class CourseController<courses> {

	        private List<Course> courses = new ArrayList<>();

	        public CourseController() {
	            courses.add(new Course(1L, "Mathematics", "Basic math course", "10 weeks"));
	            courses.add(new Course(2L, "Physics", "Fundamentals of physics", "8 weeks"));
	        }

	        // Get all courses
	        @GetMapping
	        public List<Course> getAllCourses() {
	            return courses;
	        }

	        // Add a new course
	        @PostMapping
	        public Course addCourse(@RequestBody Course newCourse) {
	            newCourse.setId((long) (courses.size() + 1));
	            courses.add(newCourse);
	            return newCourse;
	        }

	        // Get a specific course by ID
	        @GetMapping("/{id}")
	        public Course getCourseById(@PathVariable Long id) {
	            Optional<Course> course = courses.stream().filter(c -> c.getId().equals(id)).findFirst();
	            return course.orElse(null); // Return null if not found (can be replaced with exception handling)
	        }

	        // Update a course by ID
	        @PutMapping("/{id}")
	        public Course updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
	            for (Course course : courses) {
	                if (course.getId().equals(id)) {
	                    course.setTitle(updatedCourse.getTitle());
	                    course.setDescription(updatedCourse.getDescription());
	                    course.setDuration(updatedCourse.getDuration());
	                    return course;
	                }
	            }
	            return null; // Can return error if not found
	        }

	        // Delete a course by ID
	        @DeleteMapping("/{id}")
	        public String deleteCourse(@PathVariable Long id) {
	            boolean removed = courses.removeIf(course -> course.getId().equals(id));
	            if (removed) {
	                return "Course deleted successfully";
	            } else {
	                return "Course not found";
	            }
	        }
	    }
	    


