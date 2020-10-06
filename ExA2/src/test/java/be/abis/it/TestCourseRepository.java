package be.abis.it;

import static org.junit.Assert.assertEquals;

import be.abis.repository.CourseRepository;
import be.abis.repository.MemoryCourseRepository;
import org.junit.Before;
import org.junit.Test;

public class TestCourseRepository {
	
	CourseRepository cr;
	
	@Before
	public void setUp() {
		cr = new MemoryCourseRepository();
	}
	
	@Test
	public void numberOfCoursesInMemoryIs5() {
		int size = cr.findAllCourses().size();
		assertEquals(5,size);
	}
	
	@Test
	public void courseWithId8050isMaven() {
		String title = cr.findCourse(8050).getShortTitle();
		assertEquals("Maven",title);
	}
	
	

}
