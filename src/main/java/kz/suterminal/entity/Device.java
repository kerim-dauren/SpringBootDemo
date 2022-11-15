package kz.suterminal.entity;

import kz.suterminal.model.DeviceStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "devices")
@Getter
@Setter
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String imei;
    private String address;
    private DeviceStatus status;
    @ManyToOne
    @JoinColumn(name = "partner_id", nullable = false)
    private Partner partner;
    @ManyToMany
    @JoinTable(
            name = "device_tariffs",
            joinColumns = @JoinColumn(name = "device_id"),
            inverseJoinColumns = @JoinColumn(name = "tariff_id"))
    private Set<Tariff> tariffs;
}
