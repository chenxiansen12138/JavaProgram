package com.chen.xiansen.r2dbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("t_author")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TAuthor {
    @Id
    Long id;
    String name;
}
