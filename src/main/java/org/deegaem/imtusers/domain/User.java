package org.deegaem.imtusers.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private Long id;
    //@Size(min = 2, max = 20)
    @Column
    private String name;
    @Column
    //@Email
    private String email;
    @Embedded
    private Credential credential;
}
