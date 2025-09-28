package hu.zlaval.springcourse.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<PhoneEntity,String> {
}
