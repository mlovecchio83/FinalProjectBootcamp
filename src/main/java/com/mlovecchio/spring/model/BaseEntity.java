package com.mlovecchio.spring.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Date;

    @MappedSuperclass
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    @EntityListeners(AuditingEntityListener.class)
    public abstract class BaseEntity extends Auditable implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @NotNull
        long id;

        @Column(name="active")
        boolean active;



        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }


        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }


    }
