package org.deegaem.imtusers.service.dto;

import lombok.Data;
import org.deegaem.imtusers.domain.Credential;

import javax.persistence.Column;
import javax.persistence.Embedded;

@Data
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private Credential credential;
}
