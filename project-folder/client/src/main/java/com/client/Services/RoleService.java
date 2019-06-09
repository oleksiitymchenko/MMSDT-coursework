package com.client.Services;

import com.client.Model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleService extends JpaRepository<Authorities,Integer> {
}

