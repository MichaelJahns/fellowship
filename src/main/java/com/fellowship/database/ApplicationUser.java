package com.fellowship.database;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;

@Entity
public class ApplicationUser implements UserDetails {
    //Parameters
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String firstName;
    private String statementOfPurpose;

    //private date dateJoined;

    //Getters
    public long getId() {
        return this.id;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getStatementOfPurpose() {
        return statementOfPurpose;
    }

    //Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(PasswordEncoder encoder, String password) {
        this.password = encoder.encode(password);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setStatementOfPurpose(String purpose) {
        this.statementOfPurpose = purpose;
    }

    //Security Settings
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

