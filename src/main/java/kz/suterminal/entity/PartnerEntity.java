package kz.suterminal.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "partners")
public class PartnerEntity {
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
