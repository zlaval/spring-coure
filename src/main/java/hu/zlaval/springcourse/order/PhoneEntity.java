package hu.zlaval.springcourse.order;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "phone")
public class PhoneEntity extends BaseEntity {

    private String phoneNumber;
}
