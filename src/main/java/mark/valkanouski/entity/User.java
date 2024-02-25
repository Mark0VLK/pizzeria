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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String surname;
    @Column(name = "phone_number", length = 20, nullable = false, unique = true)
    private String phoneNumber;
    @Column(length = 50, nullable = false, unique = true)
    private String email;
    @Column(length = 100, nullable = false)
    private String password;
    @Column(name = "birth_date")
    private Timestamp birthDate;
    @Column(nullable = false)
    private Timestamp created;
    @Column(nullable = false)
    private Timestamp changed;
    @Column(name = "is_deleted", nullable = false, columnDefinition = "boolean default false")
    private Boolean isDeleted = false;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Order> orders = Collections.emptySet();
    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("users")
    private Set<Location> locations = Collections.emptySet();
}
