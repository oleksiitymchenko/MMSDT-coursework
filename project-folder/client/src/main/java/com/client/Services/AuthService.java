package com.client.Services;

import com.client.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthService extends JpaRepository<Users,Integer> {
}
