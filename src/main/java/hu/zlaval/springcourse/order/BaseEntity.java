package hu.zlaval.springcourse.order;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(of = "id")
@FilterDef(
        name = "authorFilter",
        defaultCondition = "created_by = :userId",
        parameters = {@ParamDef(name = "userId", type = String.class)}
)
public class BaseEntity {

    @Id
    //@JsonView()
    @Column(nullable = false, unique = true, updatable = false)
    protected String id = IDGenerator.newId();

    @Version //Explain: Optimistic Lock
    protected Long version;

    //@CreatedDate
    //@Column(nullable = false, updatable = false)
    protected Instant createdAt;

    //@LastModifiedDate
    protected Instant modifiedAt;

    //Explain: real audit
    //@CreatedBy
    protected String createdBy;

    //@LastModifiedBy
    protected String modifiedBy;

    //Explain: multitenancy
    //@TenantId
    //@Column(name = "tenantId", nullable = false, updatable = false)
    protected String tenantId;

    @PrePersist
    protected void prePersist() {
        createdAt = Instant.now();
        modifiedAt = createdAt;
        //  createdBy = authInfo()?.id ?: SYSTEM
        //  modifiedBy = createdBy
        // tenantId = authInfo().tenantId
    }

    @PreUpdate
    protected void preUpdate() {
        modifiedAt = Instant.now();
        //modifiedBy = authInfo()?.id ?: SYSTEM
    }


}
