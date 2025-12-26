package com.example.websecurity.repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.websecurity.model.Users;

@Repository
public class UserRepo {

    private final JdbcTemplate jdbcTemplate;

    // Use constructor injection for 2025 bes
    
    t practices
    public UserRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Users> mapper = (rs, rowNum) -> {
        Users u = new Users();
        u.setId(rs.getInt("id"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        return u;
    };

    public Users findByUsername(String username) {
        // Safer than queryForObject; returns null instead of throwing exception if missing
    	
    	System.out.println("method called.......");
        Users user = jdbcTemplate.query("SELECT * FROM users WHERE username = ?", mapper, username)
                .stream()
                .findFirst()
                .orElse(null);
        System.out.println(user.getUsername());
        return user;
    }
}
