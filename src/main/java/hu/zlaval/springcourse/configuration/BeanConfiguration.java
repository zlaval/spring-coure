package hu.zlaval.springcourse.configuration;

import hu.zlaval.springcourse.book.calculators.BookDiscountedPriceCalculator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    @Qualifier("discount")
    public BookDiscountedPriceCalculator bookDiscountedPriceCalculator() {
        return new BookDiscountedPriceCalculator(15);
    }

}
