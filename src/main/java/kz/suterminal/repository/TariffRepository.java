package kz.suterminal.repository;

import kz.suterminal.entity.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;


interface TariffRepository extends JpaRepository<Tariff, Long> {
}
