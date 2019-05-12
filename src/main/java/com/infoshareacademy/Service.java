package com.infoshareacademy;

import com.infoshareacademy.dao.AddressDao;
import com.infoshareacademy.dao.ComputerDao;
import com.infoshareacademy.dao.StudentDao;
import com.infoshareacademy.model.Computer;
import com.infoshareacademy.model.Student;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Transactional
public class Service {

    @Inject
    StudentDao studentDao;

    @Inject
    ComputerDao computerDao;

    public Service(){

    }

    @GET
    @Path("/students")
    @Produces(MediaType.APPLICATION_JSON)
    public Response showAllStudents(){

        List<Student> studentList = new ArrayList<>(studentDao.findAll());

        if(studentList.isEmpty()){
            return Response.noContent().build();
        }
        else {
            return Response.ok(studentList).build();
        }
    }

    @GET
    @Path("/students/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response showStudentByName(@PathParam("name") String name){

        List<Student> studentList = new ArrayList<>(studentDao.findByName(name));

        return Response.ok(studentList).build();
    }

    @POST
    @Path("/computer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addComputer(Computer computer) {

        computerDao.save(new Computer(computer.getName(), computer.getOperatingSystem()));

        return Response.ok(computerDao.findAll()).build();
    }

    @DELETE
    @Path("/computer/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response removeComputer(@PathParam("id") Long id){
        if(null != computerDao.findById(id)){
            computerDao.delete(id);
            return Response.ok().build();
        }
        else{
            return Response.status(404).build();
        }
    }

}
