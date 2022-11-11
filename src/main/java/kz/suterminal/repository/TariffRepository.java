package kz.suterminal.repository;

import kz.suterminal.entity.TariffEntity;
import org.springframework.data.jpa.repository.JpaRepository;


interface TariffRepository extends JpaRepository<TariffEntity, Long> {
}
