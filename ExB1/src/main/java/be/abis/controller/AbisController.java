package be.abis.controller;


import be.abis.model.Course;
import be.abis.model.Login;
import be.abis.model.Person;
import be.abis.service.CourseService;
import be.abis.service.TrainingService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AbisController {

    @Autowired
    TrainingService trainingService;

    @Autowired
    CourseService courseService;

    Person personLoggedIn;

    List<Course> courseList;

    Course searchedCourse;

    /**
     * HTTP GET for initial login page
     *
     * @param model
     * @return
     */
    @GetMapping("/")
    public String showLogin(Model model) {
        Login login = new Login();
        model.addAttribute("login", login);
        return "login";
    }

    /**
     * HTTP GET showing login page when coming from other pages
     *
     * @param model
     * @return login.html
     */
    @GetMapping("/login")
    public String showLoginAgain(Model model) {
        Login login = new Login();
        model.addAttribute("login", login);
        return "login";
    }

    /**
     * HTTP POST when submit button is pressed on login page
     *
     * @param model
     * @param login
     * @return redirect to login or welcome HTML page
     */
    @PostMapping("/")
    public String submitLogin(Model model, Login login) {
        System.out.println("person à logger " + login.getEmail() + " " + login.getPassword());
        personLoggedIn = trainingService.findPerson(login.getEmail(), login.getPassword());
        if (personLoggedIn ==null) {
            System.out.println("this person " + login.getEmail() + " was not found");
            return "redirect:/login";
        } else {
            model.addAttribute("person", personLoggedIn.getFirstName());
            return "redirect:/welcome";
        }
    }

    /**
     * HTTP GET showing Welcome when a person is found on the login page
     *
     * @param model
     * @return welcome.html
     */
    @GetMapping("/welcome")
    public String showWelcome(Model model) {
        model.addAttribute("person", personLoggedIn);
        return "welcome";
    }

    /**
     * HTTP GET showing the course menu
     *
     * @param model
     * @return coursemenu.html
     */
    @GetMapping("/coursemenu")
    public String showCourseMenu(Model model) {
        model.addAttribute("person", personLoggedIn);
        return "coursemenu";
    }

    /**
     * HTTP GET showing the person menu
     *
     * @param model
     * @return personmenu.html
     */
    @GetMapping("/personmenu")
    public String showPersonMenu(Model model) {
        model.addAttribute("person", personLoggedIn);
        return "personmenu";
    }

    /**
     * HTTP GET showing all courses
     *
     * @param model
     * @return showallcourses.html or coursemenu.html if no courses are found
     */
    @GetMapping("/showallcourses")
    public String showAllCourses(Model model) {
        courseList = trainingService.getAllCourses();
        if (courseList == null) {
            System.out.println("There are no course");
            model.addAttribute("person", personLoggedIn);
            return "coursemenu";
        } else {
            System.out.println("There are some courses");
            model.addAttribute("courses", courseList);
            return "showallcourses";
        }
    }

    /**
     * HTTP GET to let the user input the course id
     *
     * @param model
     * @return findcoursebyid.html
     */
    @GetMapping("/findacoursebyid")
    public String findCourseById(Model model) {
        searchedCourse = new Course();
        model.addAttribute("course", searchedCourse);
        return "findacoursebyid";
    }

    /**
     * HTTP GET to let the user input the course title
     *
     * @param model
     * @return
     */
    @GetMapping("/findacoursebytitle")
    public String findCourseByTitle(Model model) {
        searchedCourse = new Course();
        model.addAttribute("course", searchedCourse);
        return "findacoursebytitle";
    }

    /**
     * HTTP POST to search a course by id or by short title
     *
     * @param model
     * @param course
     * @return showallcourses.html with only one course
     */
    @PostMapping("/coursefound")
    public String courseFound(Model model, Course course) {
        courseList = new ArrayList<>();
        String courseId = course.getCourseId();
        if (courseId == null || courseId.isEmpty()) {
            searchedCourse = trainingService.getCourseByShortName(course.getShortTitle());
            if (searchedCourse != null) {
                System.out.println("Course found with short title " + course.getShortTitle());
                courseList.add(searchedCourse);
            } else {
                System.out.println("Course not found with short title " + course.getShortTitle());
                return "findacoursebytitle";
            }
        } else {
            searchedCourse = trainingService.getCourseById(Integer.parseInt(courseId));
            if (searchedCourse != null) {
                System.out.println("Course found with ID " + courseId);
                courseList.add(searchedCourse);
            } else {
                System.out.println("Course not found with ID " + courseId);
                return "findacoursebyid";
            }
        }
        model.addAttribute("courses", courseList);
        return "showallcourses";
    }

    /**
     * HTTP GET showing the changepassword.html page to change the password
     *
     * @param model
     * @param person
     * @return changepassword.html
     */
    @GetMapping("/changepassword")
    public String getPassword(Model model, Person person) {
        personLoggedIn = trainingService.findPerson(person.getEmailAddress(), person.getPassword());
        model.addAttribute("person", personLoggedIn);
        model.addAttribute("newPassword", "");
        return "changepassword";
    }

    /**
     * HTTP POST to change the password
     *
     * @param model
     * @param person
     * @param newPassword
     * @return changepassword.html
     */
    @PostMapping("/changepassword")
    public String changePassword(Model model, Person person, String newPassword) {
        try {
            trainingService.changePassword(person, newPassword);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "changepassword";
    }

    /**
     * HTTP GET showing the search person page
     *
     * @param model
     * @return person.html
     */
    @GetMapping("/searchforperson")
    public String showPerson(Model model) {
        model.addAttribute("person", personLoggedIn);
        return "searchforaperson";
    }

    @PostMapping("/showallpersons")
    public String showAllPersons(Model model, Person person) {
        List<Person> persons = new ArrayList<>();
        personLoggedIn = trainingService.findPerson(person.getPersonId());
        if (personLoggedIn != null) {
            persons.add(personLoggedIn);

        } else {
            persons = trainingService.getAllPersons();
        }
        model.addAttribute("persons", persons);
        return "showallpersons";
    }
}
