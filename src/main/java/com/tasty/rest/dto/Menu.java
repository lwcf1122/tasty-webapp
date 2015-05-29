package com.tasty.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.tasty.rest.common.View;

import org.springframework.stereotype.Component;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by flao on 5/20/15.
 */
@ToString
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("menu")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Menu {

    @JsonView({View.GetResponse.class, View.PostResponse.class})
    private Integer id;

//    @Size
    @JsonView(View.GetResponse.class)
    private String name;

    @JsonView(View.GetResponse.class)
    private String description;

    @JsonView(View.GetResponse.class)
    private Double price;

    @JsonView(View.GetResponse.class)
    private Integer status;

    @NotNull
    @JsonView(View.GetResponse.class)
    private Integer type;

    @JsonView(View.GetResponse.class)
    private String note;
}
