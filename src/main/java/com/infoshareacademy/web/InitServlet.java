package com.infoshareacademy.web;

import com.infoshareacademy.dao.*;
import com.infoshareacademy.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;

@WebServlet("/init")
public class InitServlet extends HttpServlet {

    private Logger LOG = LoggerFactory.getLogger(StudentServlet.class);

    @Inject
    private StudentDao studentDao;

    @Inject
    private ComputerDao computerDao;

    @Inject
    private AddressDao addressDao;

    @Inject
    private CourseDao courseDao;

    @Inject
    private TeacherDao teacherDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        init();
        resp.getWriter().println("Database initialized");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        // Test data

        // Courses
        Course course1 = new Course("JJDD6");
        courseDao.save(course1);

        Course course2 = new Course("JJDZ6");
        courseDao.save(course2);

        Course course3 = new Course("JJFD9");
        courseDao.save(course3);

        // Addresses
        Address a1 = new Address("Grunwaldzka 472B", "Gdansk");
        addressDao.save(a1);

        Address a2 = new Address("Luzycka 12d", "Gdynia");
        addressDao.save(a2);

        // Computers
        Computer c1 = new Computer("Xiaomi Lepsze", "Windows XP SP2");
        computerDao.save(c1);

        Computer c2 = new Computer("Zenbook", "Fedora");
        computerDao.save(c2);

        //Teachers
        Teacher t1 = new Teacher("12345678910", "Jakistam", "Edward", Arrays.asList(course2));
        teacherDao.save(t1);

        Teacher t2 = new Teacher("10987654321", "Abacki", "Marek", Arrays.asList(course1, course3));
        teacherDao.save(t2);

        // Students
        Student s1 = new Student("Michal",
                "Graczyk",
                LocalDate.of(1980, 11, 12),
                c1,
                a1,
                Arrays.asList(course1, course2, course3));
        studentDao.save(s1);

        Student s2 = new Student("Marek",
                "Malinovsky",
                LocalDate.of(1960, 5, 13),
                c2,
                a1,
                Arrays.asList(course2, course3));
        studentDao.save(s2);

        LOG.info("System time zone is: {}", ZoneId.systemDefault());
    }
}
