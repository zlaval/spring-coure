package hu.zlaval.springcourse.order;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.YesNoConverter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customers")
@NamedEntityGraphs(
        value = {
                @NamedEntityGraph(
                        name = "Customer.orders",
                        attributeNodes = {
                                @NamedAttributeNode("orders")
                        }
                ),
                @NamedEntityGraph(
                        name = "Customer.noJoin"
                ),
        }
)
//@SQLRestriction("reseller = 'Y'")
public class CustomerEntity extends BaseEntity {

    @Basic(optional = false)
    private String name;

    @Convert(converter = YesNoConverter.class)
    private Boolean reseller;

    //Explain: Uni/Bidirectional
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_id")
    private PhoneEntity phone;

    //Explain: Lazy, Eager, Lazy out of transaction,
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)// fetch = FetchType.EAGER
    //remove record from this list removes it from db
    private List<OrderEntity> orders;

}
