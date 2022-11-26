// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.namesearch.control;

import telsos.architecture.hexagonal.annotations.Port;
import telsos.architecture.hexagonal.annotations.PortType;

@Port(PortType.INPUT)
public abstract class AbstractNamesearchFacade {

  protected abstract DataReader dataReader();

}
