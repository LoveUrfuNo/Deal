package springbackend.model;

import javax.persistence.*;

import javax.persistence.Entity;

/**
 * Simple JavaBean domain object that represents a user Services.
 */

@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String usernameOfSeller;

    @Column(name = "cost")
    private Integer serviceCost;

    @Column(name = "category")
    private String category;

    @Column(name = "discription")
    private String discription;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "type_of_service")
    private String typeOfService;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsernameOfSeller() {
        return usernameOfSeller;
    }

    public void setUsernameOfSeller(String usernameOfSeller) {
        this.usernameOfSeller = usernameOfSeller;
    }

    public Integer getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(Integer serviceCost) {
        this.serviceCost = serviceCost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
    }
}
