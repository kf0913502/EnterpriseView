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



    
    
    public String comment;
    @Id
    @JoinColumn(name="CID")
    public Criteria criteria;
    @JoinColumn(name="RATING_ID")
    public Rating rating;
    
    @Id
    @ManyToOne
    @JoinColumn(name="IID")
    public Internship internship;       



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
