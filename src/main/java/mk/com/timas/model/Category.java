package mk.com.timas.model;

import lombok.Data;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

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


    public Category() {
    }

    public Category(String name, String description,String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }
}
