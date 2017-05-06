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

    /*@OneToOne
    @JoinColumn(name = "user_id")
    private Long user_id;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }*/

    @Column(name = "userid")
    private Long userId;

    @Column(name = "service_name")
    private String nameOfService;

    @Column(name = "username")
    private String usernameOfSeller;           //TODO: delete this

    @Column(name = "cost")
    private Integer serviceCost;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "type_of_service")
    private String typeOfService;

    @Column(name = "currency")
    private String currency;

   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNameOfService() {
        return nameOfService;
    }

    public void setNameOfService(String nameOfService) {
        this.nameOfService = nameOfService;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /*@Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", userId=" + userId +
                ", nameOfService='" + nameOfService + '\'' +
                ", usernameOfSeller='" + usernameOfSeller + '\'' +
                ", serviceCost=" + serviceCost +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", typeOfService='" + typeOfService + '\'' +
                ", currency='" + currency + '\'' +
                ", user=" + user +
                '}';
    }*/
}
