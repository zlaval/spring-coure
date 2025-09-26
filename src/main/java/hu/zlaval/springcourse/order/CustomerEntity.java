package hu.zlaval.springcourse.order;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.YesNoConverter;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity {

    @Basic(optional = false)
    private String name;

    @Convert(converter = YesNoConverter.class)
    private Boolean reseller;

    //Explain: Uni/Bidirectional
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_id")
    private PhoneEntity phone;

}
