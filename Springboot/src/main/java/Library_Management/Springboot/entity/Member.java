package Library_Management.Springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate membershipDate;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Borrow> borrows;


    @OneToMany(mappedBy = "member")
    private List<Search> searches;

    @OneToMany(mappedBy = "member")
    private List<Report> reports;

}