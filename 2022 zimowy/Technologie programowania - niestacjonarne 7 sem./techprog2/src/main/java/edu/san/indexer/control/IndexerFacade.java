package edu.san.indexer.control;

import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;

import edu.san.hexagonal.Port;
import edu.san.hexagonal.PortOrAdapterType;

@ApplicationScoped
@Port(PortOrAdapterType.INBOUND)
public class IndexerFacade {

  private final DataReader csvReader;

  public IndexerFacade(DataReader csvReader) {
    this.csvReader = Objects.requireNonNull(csvReader);
  }
  
}
