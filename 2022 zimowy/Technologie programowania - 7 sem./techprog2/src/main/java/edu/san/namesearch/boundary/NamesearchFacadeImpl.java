// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.namesearch.boundary;

import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;

import edu.san.namesearch.control.AbstractNamesearchFacade;
import edu.san.namesearch.control.DataReader;

@ApplicationScoped
class NamesearchFacadeImpl extends AbstractNamesearchFacade {

  private final DataReader dataReader;

  NamesearchFacadeImpl(DataReader dataReader) {
    this.dataReader = Objects.requireNonNull(dataReader);
  }

  @Override
  protected DataReader dataReader() {
    return dataReader;
  }

}
