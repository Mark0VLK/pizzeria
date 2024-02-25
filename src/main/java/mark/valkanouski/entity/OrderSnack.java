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
        "order", "snack"
})
@ToString(exclude = {
        "order", "snack"
})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_snacks", uniqueConstraints = {
        @UniqueConstraint(name = "ordersnacks_pk", columnNames = {"order_id", "snack_id"})
})
public class OrderSnack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false, foreignKey = @ForeignKey(name = "ordersnacks_order_id_fk"))
    @JsonBackReference
    private Order order;
    @ManyToOne
    @JoinColumn(name = "snack_id", nullable = false, foreignKey = @ForeignKey(name = "ordersnacks_snack_id_fk"))
    @JsonBackReference
    private Snack snack;
    @Column(nullable = false, columnDefinition = "integer default 1")
    private Integer quantity = 1;
}
