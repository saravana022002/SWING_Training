package net.saravana.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootTest
@Configuration
@ComponentScan("net.saravana.mapper")
class SpringbootBackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
