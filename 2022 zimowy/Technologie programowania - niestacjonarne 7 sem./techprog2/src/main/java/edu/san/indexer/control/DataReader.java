package edu.san.indexer.control;

import java.io.IOException;
import java.nio.file.Path;
import org.eclipse.collections.api.list.ImmutableList;

import edu.san.hexagonal.Port;
import edu.san.hexagonal.PortOrAdapterType;

@Port(PortOrAdapterType.OUTBOUND)
public interface DataReader {

  ImmutableList<FirstName> readFirstNames(Path file) throws IOException;
  
}
