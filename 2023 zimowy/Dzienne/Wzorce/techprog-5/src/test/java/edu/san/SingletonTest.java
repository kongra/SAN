package edu.san;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class SingletonTest {

    @Test
    void test1() {
        Singleton.foo();
        assertThat(1).isEqualTo(1);
    }

}
