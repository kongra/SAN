package edu.san.indexer.control;

import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;

import telsos.architecture.hexagonal.annotations.Port;
import telsos.architecture.hexagonal.annotations.PortType;

@Port(PortType.INPUT)
@ApplicationScoped
public class IndexerFacade {

  private final DataReader dataReader;

  public IndexerFacade(DataReader dataReader) {
    this.dataReader = Objects.requireNonNull(dataReader);
  }

}
