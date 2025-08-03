package hu.zlaval.springcourse;

import hu.zlaval.springcourse.book.calculators.BookDiscountedPriceCalculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringCourseApplication {

    public static void main(String[] args) {
        var ctx = SpringApplication.run(SpringCourseApplication.class, args);
        BookDiscountedPriceCalculator bean = ctx.getBean(BookDiscountedPriceCalculator.class);
        System.out.println("Get a price = " + bean.providePrice());

        //String[] beans = ctx.getBeanDefinitionNames();
       // Arrays.stream(beans).forEach(System.out::println);
    }

}
