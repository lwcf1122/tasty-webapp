/*************************************************************************
 * Copyright (c) Metabiota Incorporated - All Rights Reserved
 * ------------------------------------------------------------------------
 * This material is proprietary to Metabiota Incorporated. The
 * intellectual and technical concepts contained herein are proprietary
 * to Metabiota Incorporated. Reproduction or distribution of this
 * material, in whole or in part, is strictly forbidden unless prior
 * written permission is obtained from Metabiota Incorporated.
 * ***********************************************************************
 * <p/>
 * Created by WLao on 10/29/15.
 */
package com.recipe.rest.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "user", catalog = "npudb")
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Where(clause = "status <> '0'")
public class UserDO {
    @Column(name = "id", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Integer id;

    @Column(name = "username", nullable = false, length = 45)
    @NotNull
    @Getter
    @Setter
    private String username;

    @Column(name = "password", nullable = false, length = 45)
    @NotNull
    @Getter
    @Setter
    private String password;

    @Column(name = "email", nullable = false, length = 45)
    @NotNull
    @Getter
    @Setter
    private String email;

    @Column(name = "nickname", nullable = false, length = 45)
    @NotNull
    @Getter
    @Setter
    private String nickname;

    @Column(name = "created_by", nullable = false, length = 100)
    @NotNull
    @Getter
    @Setter
    private String createdBy;

    @Column(name = "created_date", nullable = false, updatable=false)
    @Getter
    @Setter
    private Date createdDate;

    @Column(name = "updated_by", nullable = false, length = 100)
    @NotNull
    @Getter
    @Setter
    private String updatedBy;

    @Column(name = "updated_date", nullable = false)
    @Getter
    @Setter
    private Date updatedDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
        this.updatedDate = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedDate = new Date();
    }
}
