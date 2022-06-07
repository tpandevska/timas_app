package mk.com.timas.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(length = 4000)
    private String description;

    private String image;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Product> products;

    public Category() {
    }

    public Category(String name, String description,String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }
}
