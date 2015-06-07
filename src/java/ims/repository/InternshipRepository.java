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
import javax.persistence.Query;

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
        Query q = em.createQuery("select i from Internship i where i.student.id = :StudentID");
        q.setParameter("StudentID",studentId);
        List<Internship> results = q.getResultList();
        
        if (results == null || results.isEmpty()) return null;
        else return results.get(0);
    }

    public Internship getInternshipById(int id) {
        return em.find(Internship.class, id);

    }

    public List<Internship> getInternships() {
        
        Query q = em.createQuery("select i from Internship i");
        return q.getResultList();
    }

    public int addInternship(Internship internship) {
        
        em.persist(internship);
        /*internship.setId(++lastInternshipId);
        internships.add(internship);
        return lastInternshipId;*/
        return 2;
    }

    public void removeInternship(Internship internship) {
        em.remove(internship);
    }

    public List<Internship> getInternships(int examinerId) {
        Query q = em.createQuery("select i from Internship i where i.examiner.id = :EID");
        q.setParameter("EID",examinerId);
        return q.getResultList();
        
    }

    public List<Internship> getInternships(String state) {
        
        if (state == "all") return this.getInternships();
        Query q = em.createQuery("select i from Internship i where i.status = :status");
        q.setParameter("status",state);
        return q.getResultList();
       
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
        em.merge(internship);
    }

    public void confirmInternship(int internshipId, int companyId) {
        
        Internship i = this.getInternshipById(internshipId);
        i.setStatus("confirmed");
        i.setHostCompany(companyRepository.getCompany(companyId));
        em.merge(i);
    }


}
