package mk.com.timas.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;

    @Column(name = "image")
    @Lob
    private String imageBase64;

    private String code;
    private Long barcode;
    private String size;
    private String material;
    private String weight;

    @Lob
    private String description;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Manufacturer manufacturer;

    public Product() {}

    public Product(String name, Double price, Integer quantity,String code,Long barcode,String size, String material,String weight, String description, Category category, Manufacturer manufacturer) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.manufacturer = manufacturer;
        this.code = code;
        this.barcode = barcode;
        this.material = material;
        this.size = size;
        this.weight = weight;
        this.description = description;
    }
}
