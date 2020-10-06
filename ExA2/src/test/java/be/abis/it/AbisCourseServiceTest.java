package be.abis.it;

import be.abis.model.Course;
import be.abis.service.AbisCourseService;
import be.abis.service.CourseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AbisCourseServiceTest {

    @Autowired
    CourseService courseService;

    @Before
    public void setUp() {
        courseService = new AbisCourseService();
    }

    @Test
    public void course7900ShouldBeFound() {
        //arrange
        Course course = new Course();

        //act
        course = courseService.findCourse(7900);

        //assert
        assertNotNull(course);
    }

    }
