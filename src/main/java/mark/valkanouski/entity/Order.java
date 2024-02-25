package mark.valkanouski.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
        "user", "location", "orderPizzas", "orderSnacks", "orderDrinks"
})
@ToString(exclude = {
        "user", "location", "orderPizzas", "orderSnacks", "orderDrinks"
})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean delivered = false;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    @JsonBackReference
    private Location location;
    @Column(nullable = false)
    private Timestamp created;
    @Column(nullable = false)
    private Timestamp changed;
    @Column(name = "is_deleted", nullable = false, columnDefinition = "boolean default false")
    private Boolean isDeleted = false;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<OrderPizza> orderPizzas = Collections.emptySet();
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<OrderSnack> orderSnacks = Collections.emptySet();
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<OrderDrink> orderDrinks = Collections.emptySet();
}
