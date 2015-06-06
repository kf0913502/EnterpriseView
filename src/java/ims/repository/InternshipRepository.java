package ims.repository;

import ims.entity.Company;
import ims.entity.Internship;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class InternshipRepository {
    @PersistenceContext
    private EntityManager em;
    @Inject
    CompanyRepository companyRepository;
    @Inject
    UserRepository userRepository;

    private List<Internship> internships = new ArrayList<Internship>();
    private int lastInternshipId = 0;

    public Internship getInternship(int studentId) {
        Optional<Internship> internship = internships.stream().filter(i -> i.getStudent().getStudentId() == studentId).findFirst();
        return internship.isPresent() ? internship.get() : null;
    }

    public Internship getInternshipById(int id) {
        Optional<Internship> internship = internships.stream().filter(i -> i.getId() == id).findFirst();
        return internship.isPresent() ? internship.get() : null;
    }

    public List<Internship> getInternships() {
        if (internships == null || internships.isEmpty()) {
            insertTestData();
        }
        return internships;
    }

    public int addInternship(Internship internship) {
        
        em.persist(internship);
        /*internship.setId(++lastInternshipId);
        internships.add(internship);
        return lastInternshipId;*/
        return 2;
    }

    public void removeInternship(Internship internship) {
        internships.remove(internship);
    }

    public List<Internship> getInternships(int examinerId) {
        if (internships == null || internships.isEmpty()) {
            insertTestData();
        }
        List<Internship> examinerInternships = internships.stream().filter(i -> i.getExaminer() != null && i.getExaminer().getStaffNo() == examinerId).collect(Collectors.toList());
        return examinerInternships;
    }

    public List<Internship> getInternships(String state) {
        if (internships == null || internships.isEmpty()) {
            insertTestData();
        }

        //if state is "all" , just return all intenrships
        if (state.equals("all")) {
            return getInternships();
        }

        List<Internship> internshipList = 
                internships.stream().filter(i -> i.getStatus().equals(state)).collect(Collectors.toList());
                
        return internshipList;
    }

    public List<String> getInternshipStates() {
        List<String> states = new ArrayList<>();
        states.add("pending");
        states.add("confirmed");
        //states.add("rejected");
        states.add("all");
        //states.add("canceled");
        return states;
    }

    public void updateInternship(Internship internship) {
        for (int i = 0; i < internships.size(); i++) {
            if (internships.get(i).getId() == internship.getId()) {
                internships.set(i, internship);
                break;
            }
        }
    }

    public void confirmInternship(int internshipId, int companyId) {
        Company company = companyRepository.getCompany(companyId);
        Internship internship = getInternshipById(internshipId);
        internship.setHostCompany(company);
        internship.setStatus("confirmed");
        updateInternship(internship);
    }

    private void insertTestData() {
        internships = new ArrayList<>();
        Internship internship = new Internship();
        internship.setStudent(userRepository.getStudent("student1"));
        internship.setStatus("confirmed");
        internship.setYear(2015);
        internship.setHostCompany(companyRepository.getCompany("Mada"));
        internship.setExaminer(userRepository.getFaculty(501));
        addInternship(internship);

        internship = new Internship();
        internship.setStudent(userRepository.getStudent("student2"));
        internship.setStatus("pending");
        internship.setYear(2015);
        addInternship(internship);

        internship = new Internship();
        internship.setStudent(userRepository.getStudent("student3"));
        internship.setStatus("confirmed");
        internship.setYear(2015);
        internship.setHostCompany(companyRepository.getCompany("AlJazeera"));
        internship.setExaminer(userRepository.getFaculty(501));
        addInternship(internship);
    }
}
