package edu.san.system;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/students")
public class StudentsResource {

  private final StudentsRepository studentsRepository;

  public StudentsResource(StudentsRepository studentRepository) {
    this.studentsRepository = studentRepository;
  }

  @Path("count")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response count() {
    final var n = studentsRepository.count();
    return Response.ok(Long.toString(n)).build();
  }
  
  @Path("add")
  @POST
  @Produces(MediaType.TEXT_PLAIN)
  @Transactional
  public Response add() {
    final var student = new Student();
    studentsRepository.persist(student);
    return Response.ok(student.getId()).build();
  }
  
}
