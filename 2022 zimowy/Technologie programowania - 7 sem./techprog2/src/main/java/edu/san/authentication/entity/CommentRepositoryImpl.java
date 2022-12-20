// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.authentication.entity;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import telsos.architecture.hexagonal.annotations.Adapter;
import telsos.architecture.hexagonal.annotations.AdapterType;

@Adapter(AdapterType.SECONDARY)
@ApplicationScoped
class CommentRepositoryImpl implements PanacheRepository<CommentEntity> {
  // More to come later
}
