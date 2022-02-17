package study.datajpa;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import study.datajpa.domain.Team;

import java.util.Optional;

@SpringBootTest
public class OptionalTest {

    @Test
    void null테스트() {

        Team t = null;
        Optional<Team> o = Optional.ofNullable(t);
//        Assertions.assertThat(o).isEqualTo(o.isEmpty());
    }
}
