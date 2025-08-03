package hu.zlaval.springcourse.book.calculators;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class FixBookPriceProvider implements BookPriceProvider {

    @Override
    public Integer providePrice() {
        return 777;
    }
}
