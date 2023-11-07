package com.br.parkingcontrol.models;

import com.br.parkingcontrol.enums.RoleName;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_role")
public class RolesModel implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID roleId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName roleName;

    public RolesModel(UUID roleId) {
        this.roleId = roleId;
    }

    @Override
    public String getAuthority() {
        return this.roleName.toString();
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
}
