package com.springmvcsampler.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by atheedom on 07/04/15.
 */
@DynamicInsert
@DynamicUpdate
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity<T> // extends AbstractAuditable<Account, UUID>
 {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    //@Override
    public UUID getId() {
        return id;
    }

    //@Override
    public void setId(UUID id) {
        this.id = id;
    }


    // TODO - add other methods common to all entities
}
