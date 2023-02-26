package su.dedvano.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "included_products", schema = "goods")
public class IncludedProduct {

    @Id
    @Generated
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "folder_id")
    private Folder folder;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product includedProduct;

    private Integer orderInFolder;
}
