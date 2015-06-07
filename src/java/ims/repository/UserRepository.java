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
import javax.persistence.Query;

@Singleton
public class UserRepository {
    @PersistenceContext
    private EntityManager em;
    
    private List<User> users;
    private final String facultyUrl = "http://erradi.github.io/json/faculty.json";
    private final String studentUrl = "http://erradi.github.io/json/student.json";

    public List<Faculty> getFaculty() {
        Query q = em.createQuery("select f from Faculty f ORDER BY f.staffNo");
        return q.getResultList();
    }

    public Faculty getFaculty(int staffNo) {
        
        Query q = em.createQuery("select f from Faculty f where f.staffNo = :staffNo");
        q.setParameter("staffNo",staffNo);
        List<Faculty> results = q.getResultList();
        
        if (results == null || results.isEmpty()) return null;
        else return results.get(0);
        
    }

    public User getUser(String username, String password) {
        if (users == null) {
            loadUsers();
        }
        Query q = em.createQuery("select u from User u where u.username = :UN and u.password = :PW");
        q.setParameter("UN",username);
        q.setParameter("PW", password);
        List<Faculty> results = q.getResultList();
        
        if (results == null || results.isEmpty()) return null;
        else return results.get(0);
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
