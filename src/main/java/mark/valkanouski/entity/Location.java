package mark.valkanouski.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@Entity
@Setter
@Getter
@EqualsAndHashCode(exclude = {
        "orders"
})
@ToString(exclude = {
        "orders"
})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "locations", indexes = {
        @Index(name = "location_street_index", columnList = "street"),
        @Index(name = "location_city_index", columnList = "city")
})
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 30)
    private String country;
    @Column(nullable = false, length = 40)
    private String city;
    @Column(nullable = false, length = 30)
    private String street;
    @Column(nullable = false, length = 10)
    private String house;
    @Column
    private Integer floor;
    @Column(length = 10)
    private String flat;
    @Column(nullable = false)
    private Timestamp created;
    @Column(nullable = false)
    private Timestamp changed;
    @Column(name = "is_deleted", nullable = false, columnDefinition = "boolean default false")
    private Boolean isDeleted = false;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Order> orders = Collections.emptySet();
    @ManyToMany
    @JoinTable(name = "users_locations",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnoreProperties("locations")
    private Set<User> users = Collections.emptySet();
}
