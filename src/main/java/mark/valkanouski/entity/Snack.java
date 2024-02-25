package mark.valkanouski.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
        "information", "orderSnacks"
})
@ToString(exclude = {
        "information", "orderSnacks"
})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "snacks", uniqueConstraints = {
        @UniqueConstraint(name = "snack_pk", columnNames = {"information_id", "number_of_pieces"})
}, indexes = {
        @Index(name = "snack_price_index", columnList = "price")
}
)
public class Snack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number_of_pieces", nullable = false, columnDefinition = "integer default 1")
    private Integer numberOfPieces = 1;
    @Column(nullable = false)
    private Double weight;
    @Column(nullable = false)
    private Double price;
    @ManyToOne
    @JoinColumn(name = "information_id", nullable = false, foreignKey = @ForeignKey(name = "snack_product_id_fk"))
    @JsonBackReference
    private Information information;
    @Column(nullable = false)
    private Timestamp created;
    @Column(nullable = false)
    private Timestamp changed;
    @Column(name = "is_deleted", nullable = false, columnDefinition = "boolean default false")
    private Boolean isDeleted = false;
    @OneToMany(mappedBy = "snack", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<OrderSnack> orderSnacks = Collections.emptySet();
}
