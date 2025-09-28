package hu.zlaval.springcourse.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {

    @Column(length = 50)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private ProductType productType;

    //Do not use double
    @Column(name = "price", nullable = false, precision = 2)
    private Double price;
}
