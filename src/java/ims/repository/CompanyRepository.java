package ims.repository;

import ims.entity.Company;
import com.google.gson.Gson;
import ims.entity.Internship;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Singleton
public class CompanyRepository {
@PersistenceContext
    private EntityManager em;
    private List<Company> companies = new ArrayList<>();
    private int lastCompanyId;
    private final String companiesUrl = "http://erradi.github.io/json/company.json";

    public Company getCompany(int id) {
        
        return em.find(Company.class, id);

    }

    public Company getCompany(String name) {
               if (companies.isEmpty()) {
            this.initialize();
        }
               
               
        Query q = em.createQuery("select c from Company c where c.name :cname");
        q.setParameter("cname",name);
        List<Company> results = q.getResultList();
        
        if (results == null || results.isEmpty()) return null;
        else return results.get(0);
    
 
    }

    public void initialize() {
        if (companies != null && !companies.isEmpty()) {
            return;
        }

        Gson gson = new Gson();
        String companiesStr = Utils.readUrl(companiesUrl);

        Company[] companyArray = gson.fromJson(companiesStr, Company[].class);
        //Convert the array to a editable list 
        
        for (Company c : Arrays.asList(companyArray))
            em.persist(c);
            
        companies = new ArrayList<>(Arrays.asList(companyArray));
        
        

        lastCompanyId = companies.size() + 2;
    }

    public List<Company> getCompanies() {
        if (companies.isEmpty()) {
            initialize();
        }
        
        Query q = em.createQuery("select c from Company c");
        return q.getResultList();
   
    }

    public int addCompany(Company company) {
        
        em.persist(company);

        return 2;
    }

    public boolean exists(String companyName) {
        return (getCompany(companyName) != null);
    }
}