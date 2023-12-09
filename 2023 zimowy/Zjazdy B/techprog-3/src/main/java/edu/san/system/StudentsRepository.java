// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.system;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentsRepository implements PanacheRepository<Student> {

}
