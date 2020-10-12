package be.abis.controller;


import be.abis.model.Address;
import be.abis.model.Company;
import be.abis.model.Course;
import be.abis.model.Login;
import be.abis.model.Person;
import be.abis.service.CourseService;
import be.abis.service.TrainingService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sun.rmi.runtime.Log;

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
    public String submitLogin(Model model, Login login, BindingResult bindingResult) {
        System.out.println("person à logger " + login.getEmail() + " " + login.getPassword());

//        personLoggedIn = trainingService.findPerson(login.getEmail(), login.getPassword());
        if (doesPersonExist(login)) {
            model.addAttribute("person", personLoggedIn.getFirstName());
            return "redirect:/welcome";
        } else {
            System.out.println("this person " + login.getEmail() + " was not found");
            bindingResult.reject("check your email and password");
            return "redirect:/login";

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
        System.out.println("Person logged in " + personLoggedIn.getFirstName());
        Login login = new Login();
        model.addAttribute("person", personLoggedIn);
        model.addAttribute("login", login);
        return "changepassword";
    }

    /**
     * HTTP POST to change the password
     *
     * @param model
     * @param login
     * @return changepassword.html
     */
    @PostMapping("/changepassword")
    public String changePassword(Model model, Login login) {
        String newPassword = login.getPassword();
        System.out.println("new password " + newPassword);
        try {
            trainingService.changePassword(personLoggedIn, newPassword);
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("person", personLoggedIn);
        return "personmenu";
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

    /**
     * HTTP POST to show all persons or one person if ID is given
     *
     * @param model
     * @param person
     * @return showallpersons.html
     */
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

    /**
     * HTTP GET to show the add person page
     *
     * @param model
     * @return addperson.html
     */
    @GetMapping("/addperson")
    public String showAddPerson(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "addperson";
    }

    /**
     * HTTP POST to add a person. The Company is added only with the name
     *
     * @param model
     * @param person
     * @return addperson.html
     */
    @PostMapping("/addperson")
    public String addPerson(Model model, @Valid Person person, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                System.out.println("there are some errors on add person input");
                model.addAttribute("person", person);
                return "addperson";
            } else {
                Address address = new Address();
                address.setNr(person.getCompany().getAddress().getNr());
                address.setStreet(person.getCompany().getAddress().getStreet());
                address.setTown(person.getCompany().getAddress().getTown());
                address.setZipcode(person.getCompany().getAddress().getZipcode());
                Company company = new Company();
                company.setAddress(address);
                company.setName(person.getCompany().getName());
                company.setTelephoneNumber(person.getCompany().getTelephoneNumber());
                company.setVatNr(person.getCompany().getVatNr());

                person.setCompany(company);
                trainingService.addPerson(person);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("person", person);
        return "addperson";
    }
    /**
     * HTTP GET to show the remove person page
     *
     * @param model
     * @return removeperson.html
     */
    @GetMapping("/removeperson")
    public String showRemovePerson(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        model.addAttribute("message", "");
        return "removeperson";
    }

    /**
     * HTTP POST to remove a person.
     *
     * @param model
     * @param person
     * @return removeperson.html
     */
    @PostMapping("/removeperson")
    public String removePerson(Model model, Person person) {
        trainingService.deletePerson(person.getPersonId());
        model.addAttribute("person", person);
        model.addAttribute("message", "person " + person.getPersonId()+ " removed");
        return "removeperson";
    }

    /**
     * HTTP GET to return to the login page when logout is pushed
     *
     * @param model
     * @return
     */
    @GetMapping("/logout")
    public String logout(Model model) {
        Login login = new Login();
        model.addAttribute("login", login);
        return "login";
    }

    private boolean doesPersonExist(Login login) {
        personLoggedIn = trainingService.findPerson(login.getEmail(), login.getPassword());
        if (personLoggedIn==null) return false;
        return true;
    }
}
