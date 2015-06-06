package ims.entity;

import ims.repository.Utils;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GradeItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    
    
    public String comment;
    @JoinColumn(name="CRITEREA_ID")
    public Criteria criteria;
    @JoinColumn(name="RATING_ID")
    public Rating rating;
    
    @ManyToOne
    @JoinColumn(name="INTERNSHIP_ID")
    public Internship internship;       

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Internship getInternship() {
        return internship;
    }

    public void setInternship(Internship internship) {
        this.internship = internship;
    }
            
    public GradeItem() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    
    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
    
    public double getSubTotal() {
        return Utils.round(criteria.getGrade() * rating.getPercentage(), 2);
    }
    
}
