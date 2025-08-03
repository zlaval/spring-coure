package hu.zlaval.springcourse.book.calculators;

//@Component - fail to create instance, no discount percentage
public class BookDiscountedPriceCalculator implements BookPriceProvider {

    private final Integer discountPercentage;

    public BookDiscountedPriceCalculator(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
        System.out.println("Discount is " + discountPercentage);
    }

    @Override
    public Integer providePrice() {
        return 100 - discountPercentage;
    }
}
