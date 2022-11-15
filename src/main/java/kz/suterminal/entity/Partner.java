package kz.suterminal.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "partners")
@Getter
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String name;
    private String bin;
    private String phone;
    @OneToMany(mappedBy = "partner")
    private Set<Device> devices;
}
