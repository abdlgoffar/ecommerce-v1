package ecommerce.v1.models.entities.auditing;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.Date;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Audits<USERNAME> {
    @Getter @Setter
    @CreatedBy
    protected USERNAME madeBy;

    @Getter @Setter
    @CreatedDate
    @CreationTimestamp
    protected Date madeOn;

    @Getter @Setter
    @LastModifiedBy
    protected USERNAME updatedBy;

    @Getter @Setter
    @LastModifiedDate
    @CreationTimestamp
    protected Date updateOn;
}
