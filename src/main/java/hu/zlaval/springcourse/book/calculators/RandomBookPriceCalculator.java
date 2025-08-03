package hu.zlaval.springcourse.book.calculators;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Qualifier("random")
@Component
public class RandomBookPriceCalculator implements InitializingBean, BookPriceProvider, DisposableBean {

    public RandomBookPriceCalculator() {
        System.out.println("Run RandomBookPriceCalculator Constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("Run RandomBookPriceCalculator PostConstruct");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Run RandomBookPriceCalculator PreDestroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Run RandomBookPriceCalculator afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Run RandomBookPriceCalculator destroy");
    }

    public Integer providePrice() {
        var price = ThreadLocalRandom.current().nextInt(100, 1000);
        System.out.printf("Price is %s\n", price);
        return price;
    }


}
