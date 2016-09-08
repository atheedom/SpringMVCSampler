package com.springmvcsampler.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by atheedom on 07/04/15.
 */
@DynamicInsert
@DynamicUpdate
@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
public class BaseEntity<T> implements Serializable // extends AbstractAuditable<Account, UUID>
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
