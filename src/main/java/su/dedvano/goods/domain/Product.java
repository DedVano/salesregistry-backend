package su.dedvano.goods.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "products", schema = "goods")
public class Product {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private UUID id;

    @NotEmpty
    private String name;

    private int price;

    private boolean variablePrice;

    @ToString.Exclude
    @ManyToOne/*(fetch = FetchType.LAZY, optional = false)*/
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    @ToString.Exclude
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<IncludedProduct> existsInFolders = new HashSet<>();

    private int color;

    private boolean deleted;

}
