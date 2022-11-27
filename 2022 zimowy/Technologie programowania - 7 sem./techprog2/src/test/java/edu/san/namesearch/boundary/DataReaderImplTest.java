// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.namesearch.boundary;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.san.namesearch.control.DataReader;
import lombok.extern.slf4j.Slf4j;
import telsos.Stopwatch;

@Slf4j
class DataReaderImplTest {

  DataReader dataReader;

  @BeforeEach
  void setUp() {
    dataReader = new DataReaderImpl();
  }

  @Test
  void testReadFirstNames() throws IOException {
    final var file = Paths.get(
        "/media/kongra/Devel/Projects/Present/SAN/",
        "2022 zimowy/Warstwy integracji i Big Data - 7 sem./",
        "IMIONA_NADANE_DZIECIOM_W_POLSCE_W_I_POŁOWIE_2022_R._-_IMIĘ_PIERWSZE.csv");

    final var timer = Stopwatch.start();
    final var firstNames = dataReader.readFirstNames(file);
    assertThat(firstNames).isNotEmpty();

    log.info("testReadFirstNames took " + timer.toString());
    // log.debug("" + firstNames.size());
  }

}
