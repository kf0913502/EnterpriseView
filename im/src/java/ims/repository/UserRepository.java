package ims.repository;

import ims.entity.Faculty;
import ims.entity.Student;
import com.google.gson.Gson;
import java.util.ArrayList;
import ims.entity.User;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Singleton;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class UserRepository {
    @PersistenceContext
    private EntityManager em;
    
    private List<User> users;
    private final String facultyUrl = "http://erradi.github.io/json/faculty.json";
    private final String studentUrl = "http://erradi.github.io/json/student.json";

    public List<Faculty> getFaculty() {
        return users.stream().filter(u -> u instanceof Faculty).map(user -> (Faculty) user).collect(Collectors.toList());
    }

    public Student getStudent(String username) {
        Optional<Student> student = users.stream().filter(u -> u instanceof Student && u.getUsername().equals(username)).map(user -> (Student) user).findFirst();
        return student.isPresent() ? student.get() : null;
    }

    public Faculty getFaculty(int staffNo) {
        Optional<Faculty> faculty = users.stream().filter(u -> u instanceof Faculty && ((Faculty) u).getStaffNo() == staffNo).map(user -> (Faculty) user).findFirst();
        return faculty.isPresent() ? faculty.get() : null;
    }

    public User getUser(String username, String password) {
        if (users == null) {
            loadUsers();
        }
        System.out.println("users.size: " + users.size());
        Optional<User> user = users.stream().filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password)).findFirst();
        return user.isPresent() ? user.get() : null;
    }

    public void loadUsers() {
        if (users != null && !users.isEmpty()) {
            return;
        }
        Gson gson = new Gson();
        String response = Utils.readUrl(studentUrl);
        System.out.println(response);

        Student[] studentArray = gson.fromJson(response, Student[].class);
        users = new ArrayList<>(Arrays.asList(studentArray));
        for (Student s : Arrays.asList(studentArray))
            em.persist(s);
        
        response = Utils.readUrl(facultyUrl);
        System.out.println(response);

        Faculty[] facultyArray = gson.fromJson(response, Faculty[].class);
        for (Faculty f : Arrays.asList(facultyArray))
            em.persist(f);
        users.addAll(Arrays.asList(facultyArray));
          
                    
    }
}
