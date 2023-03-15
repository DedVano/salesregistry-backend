package su.dedvano.goods.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "folders", schema = "goods")
public class Folder {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private UUID id;

    @NotEmpty
    private String name;

    private int sizeRows = 1;

    private int sizeColumns = 1;

    @ToString.Exclude
    @OneToMany(mappedBy = "containerFolder")
    private Set<IncludedFolder> includedFolders = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "containerFolder", cascade = CascadeType.ALL)
    private Set<IncludedProduct> includedProducts = new HashSet<>();

    private int color;

    private boolean deleted;

}
