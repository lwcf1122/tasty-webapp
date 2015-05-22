package com.tasty.rest.entity;

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
@Table(name = "menu", schema = "tasty")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MenuDO {

    @Column(name = "item_id", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Integer itemId;

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
    @NotNull
    @Getter
    @Setter
    private Double price;

    @Column(name = "status", nullable = false)
    @NotNull
    @Getter
    @Setter
    private Integer status;

    @Column(name = "type", nullable = false)
    @NotNull
    @Getter
    @Setter
    private Integer type;

    @Column(name = "note", nullable = true, length = 1024)
    @Getter
    @Setter
    private String note;

    @Column(name = "created_at", nullable = false)
    @NotNull
    @Getter
    @Setter
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @Getter
    @Setter
    private Date updatedAt;

}
