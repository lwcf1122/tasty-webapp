package com.tasty.rest.entity;

import org.hibernate.annotations.Where;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by flao on 5/19/15.
 */
@Entity
@Table(name = "menu", catalog = "tasty")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Where(clause = "status <> '0'")
public class MenuDO {

    @Column(name = "item_id", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Integer id;

    @Column(name = "name", nullable = false, length = 255)
    @NotNull
    @Getter
    @Setter
    private String name;

    @Column(name = "description", nullable = true, length = 1024)
    @Getter
    @Setter
    private String description;

    @Column(name = "price", nullable = false)
    @Getter
    @Setter
    private Double price;

    @Column(name = "status", nullable = false)
    @Getter
    @Setter
    private Integer status;

    @Column(name = "type", nullable = false)
    @Getter
    @Setter
    private Integer type;

    @Column(name = "note", nullable = true, length = 1024)
    @Getter
    @Setter
    private String note;

    @Column(name = "created_at", nullable = false, updatable=false)
    @Getter
    @Setter
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @Getter
    @Setter
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        this.status = 1;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
