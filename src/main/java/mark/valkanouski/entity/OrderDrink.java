package mark.valkanouski.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@EqualsAndHashCode(exclude = {
        "order", "drink"
})
@ToString(exclude = {
        "order", "drink"
})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_drinks", uniqueConstraints = {
        @UniqueConstraint(name = "orderdrinks_pk", columnNames = {"order_id", "drink_id"})
})
public class OrderDrink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false, foreignKey = @ForeignKey(name = "orderdrinks_order_id_fk"))
    @JsonBackReference
    private Order order;
    @ManyToOne
    @JoinColumn(name = "drink_id", nullable = false, foreignKey = @ForeignKey(name = "orderdrinks_drinks_id_fk"))
    @JsonBackReference
    private Drink drink;
    @Column(nullable = false, columnDefinition = "integer default 1")
    private Integer quantity = 1;
}
