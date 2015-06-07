package ims.repository;

import ims.entity.Criteria;
import com.google.gson.Gson;
import ims.entity.Rating;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Singleton
public class GradingRepository {
@PersistenceContext
    private EntityManager em;
    private List<Criteria> criteria;
    private final String criteriaUrl = "http://erradi.github.io/json/criteria.json";

    private List<Rating> ratings;
    private final String ratingUrl = "http://erradi.github.io/json/rating.json";

    public List<Criteria> getCriteria() {
        if (criteria == null) {
            loadCriteria();
        }
         Query q = em.createQuery("select c from Criteria c ORDER BY c.id");
         return q.getResultList();
    }

    public Criteria getCriteria(int id) {
        if (criteria != null) {
            loadCriteria();
        }
        return em.find(Criteria.class, id);
    }

    public void loadCriteria() {
        if (criteria != null && criteria.size() > 0) {
            return;
        }
        Gson gson = new Gson();
        String criteriasStr = Utils.readUrl(criteriaUrl);
        System.out.println(criteriasStr);

        Criteria[] criteriaArray = gson.fromJson(criteriasStr, Criteria[].class);
        
        for (Criteria c : Arrays.asList(criteriaArray))
            em.persist(c);
        
        criteria = new ArrayList<>(Arrays.asList(criteriaArray));
    }

    public List<Rating> getRatings() {
        if (ratings == null) {
            loadRating();
        }
         Query q = em.createQuery("select r from Rating r ORDER BY r.id");
         return q.getResultList();
    }

    public Rating getRating(int id) {
        if (ratings == null) {
            loadRating();
        }
        return em.find(Rating.class,id);
    }

    public void loadRating() {
        if (ratings != null && ratings.size() > 0) {
            return;
        }
        Gson gson = new Gson();
        String ratingStr = Utils.readUrl(ratingUrl);
        System.out.println(ratingStr);

        Rating[] criteriaArray = gson.fromJson(ratingStr, Rating[].class);
        
        for (Rating r : Arrays.asList(criteriaArray))
            em.persist(r);
        ratings = new ArrayList<>(Arrays.asList(criteriaArray));
    }
}
