package hu.zlaval.springcourse.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing //Enable @CreatedDate... annotations
public class JpaConfiguration {
}
