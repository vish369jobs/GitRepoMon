package com.servrock.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;


@Entity
@Data
@NoArgsConstructor
public class CommentEntity {
    @Id
    Integer id;
    String user;
    @Lob
    String body;
}
