package com.example.websecurity.repo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.websecurity.model.Users;

@Repository
public class UserRepo {
	
	private static final Logger log = LoggerFactory.getLogger(UserRepo.class);

    private final JdbcTemplate jdbcTemplate;
    
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
    	
    	log.info("method called.......");
        Users user = jdbcTemplate.query("SELECT * FROM users WHERE username = ?", mapper, username)
                .stream()
                .findFirst()
                .orElse(null);
        return user;
    }
}
