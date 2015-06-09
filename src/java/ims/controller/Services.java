/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ims.controller;

import com.google.gson.Gson;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ims.entity.*;
import ims.repository.*;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
/**
 *
 * @author karAdmin
 */

@Path("/")
@Stateless
public class Services {
    
    @Inject UserRepository userRepository;
    @Inject CompanyRepository companyRepository; 
    @Inject InternshipRepository internshipRepository;
    @Inject GradingRepository gradingRepository;
    
    @POST
    @Path("/login")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User credentials)
    {
        Gson gson = new Gson();
        User user = userRepository.getUser(credentials.getUsername(), credentials.getPassword());
        if (user != null)
            return Response.ok(gson.toJson(user)).header("type", user.getClass().toString()).build();
        else
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        


    }
    
    @GET
    @Path("/studentInternship/{studentID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response studentInternship(@PathParam("studentID") int studentID)
    {
        Gson gson = new Gson();
        Internship studentInternship = internshipRepository.getInternship(studentID);

        if (studentInternship != null)
            return Response.ok(gson.toJson(studentInternship)).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();

    }
    
    @GET
    @Path("/examinerInternships/{examinerID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response examinerInternship(@PathParam("examinerID") int examinerID)
    {
        Gson gson = new Gson();
        List<Internship> examinerInternships = internshipRepository.getInternships(examinerID);


        return Response.ok(gson.toJson(examinerInternships)).build();
   

    }
    
    @GET
    @Path("/pendingInternships")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pendingInternships()
    {
        Gson gson = new Gson();
        List<Internship> pendingInternships = internshipRepository.getInternships("pending");


        return Response.ok(gson.toJson(pendingInternships)).build();
   

    }
    
    
        @GET
    @Path("/confirmedInternships")
    @Produces(MediaType.APPLICATION_JSON)
    public Response confirmedInternships()
    {
        Gson gson = new Gson();
        List<Internship> confirmedInternships = internshipRepository.getInternships("confirmed");


        return Response.ok(gson.toJson(confirmedInternships)).build();
   

    }
    
    @GET
    @Path("/internship/{internshipID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response internship(@PathParam("internshipID") int internshipID)
    {
        Gson gson = new Gson();
        Internship internship = internshipRepository.getInternshipById(internshipID);

        if (internship != null)
            return Response.ok(gson.toJson(internship)).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();

    }
    
    @POST
    @Path("/registerInternship")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response registerInternship(Internship internship)
    {
        
        internshipRepository.addInternship(internship);
        return Response.ok().build();
    }
    
    @PUT
    @Path("/confirmInternship")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response confirmInternship(Internship internship)
    {
        
        internshipRepository.confirmInternship(internship.getId(), internship.getHostCompany().getId());
        return Response.ok().build();
    }
    
    
    @PUT
    @Path("/assignExaminer")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response assignExaminer(Internship internship)
    {
        
        internshipRepository.updateInternship(internship);
        return Response.ok().build();
    }
    
    @PUT
    @Path("/gradeInternship")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response gradeInternship(Internship internship)
    {
        
        internshipRepository.updateInternship(internship);
        return Response.ok().build();
    }
    
    @GET
    @Path("/criterias")
    @Produces(MediaType.APPLICATION_JSON)
    public Response criteria()
    {
        Gson gson = new Gson();
        List<Criteria> criterias = gradingRepository.getCriteria();


        return Response.ok(gson.toJson(criterias)).build();
    }
    
    
    @GET
    @Path("/rating")
    @Produces(MediaType.APPLICATION_JSON)
    public Response rating()
    {
        Gson gson = new Gson();
        List<Rating> ratings = gradingRepository.getRatings();


        return Response.ok(gson.toJson(ratings)).build();
    }
    
    @GET
    @Path("/companies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response companies()
    {
        Gson gson = new Gson();
        List<Company> companies = companyRepository.getCompanies();


        return Response.ok(gson.toJson(companies)).build();
    }
}
