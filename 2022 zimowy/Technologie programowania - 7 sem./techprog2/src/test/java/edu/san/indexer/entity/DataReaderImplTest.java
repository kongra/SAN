// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.indexer.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telsos.Stopwatch;

class DataReaderImplTest {

  DataReaderImpl dataReader;

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

    System.out.println(timer);
    System.out.println(firstNames.size());
  }

}
