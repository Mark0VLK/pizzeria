package mark.valkanouski.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
        "pizzas", "snacks", "drinks"
})
@ToString(exclude = {
        "pizzas", "snacks", "drinks"
})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "information", uniqueConstraints = {
        @UniqueConstraint(name = "information_name_key", columnNames = "name"),
        @UniqueConstraint(name = "information_image_key", columnNames = "image")
})
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50, unique = true)
    private String name;
    @Column(length = 200)
    private String ingredients;
    @Column(name = "energy_value")
    private Double energyValue;
    @Column
    private Double proteins;
    @Column
    private Double fat;
    @Column
    private Double carb;
    @Column(nullable = false, length = 20)
    private String category;
    @Column(nullable = false, length = 100, unique = true)
    private String image;
    @Column(nullable = false)
    private Timestamp created;
    @Column(nullable = false)
    private Timestamp changed;
    @Column(name = "is_deleted", nullable = false, columnDefinition = "boolean default false")
    private Boolean isDeleted = false;
    @OneToMany(mappedBy = "information", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Pizza> pizzas = Collections.emptySet();
    @OneToMany(mappedBy = "information", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Snack> snacks = Collections.emptySet();
    @OneToMany(mappedBy = "information", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Drink> drinks = Collections.emptySet();
}
