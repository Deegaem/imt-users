package org.deegaem.imtusers.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Credential {
    private String username;
    private String password;
}
