package springbackend.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents a User.
 */

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "is_registration_confirmed")
    private Boolean isRegistrationConfirmed;

    @Column(name = "key_for_registration_confirm")
    private String keyForRegistrationConfirmUrl;

    @Transient
    private String confirmPassword;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),                              //TODO: add toString
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRegistrationConfirmed() {
        return isRegistrationConfirmed;
    }

    public void setRegistrationConfirmed(Boolean registrationConfirmed) {
        isRegistrationConfirmed = registrationConfirmed;
    }

    public String getKeyForRegistrationConfirmUrl() {
        return keyForRegistrationConfirmUrl;
    }

    public void setKeyForRegistrationConfirmUrl(String keyForRegistrationConfirmUrl) {
        this.keyForRegistrationConfirmUrl = keyForRegistrationConfirmUrl;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
