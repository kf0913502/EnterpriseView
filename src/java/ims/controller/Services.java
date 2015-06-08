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
    
    
}
