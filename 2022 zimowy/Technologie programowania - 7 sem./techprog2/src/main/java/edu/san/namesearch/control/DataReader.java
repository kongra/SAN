// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.namesearch.control;

import java.io.IOException;
import java.nio.file.Path;

import org.eclipse.collections.api.list.ImmutableList;

import telsos.architecture.hexagonal.annotations.Port;
import telsos.architecture.hexagonal.annotations.PortType;

@Port(PortType.OUTPUT)
public interface DataReader {

  ImmutableList<FirstNameData> readFirstNames(Path file) throws IOException;

}
