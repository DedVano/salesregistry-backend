package su.dedvano.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "goods_categories", schema = "goods")
public class GoodsCategory {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    private String name;

    private int orderInReport;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    Set<GoodEntity> goods;

    private boolean deleted;
}
