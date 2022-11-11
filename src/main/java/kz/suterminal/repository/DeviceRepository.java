package kz.suterminal.repository;

import kz.suterminal.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    Device findDeviceByImei(String imei);
}
