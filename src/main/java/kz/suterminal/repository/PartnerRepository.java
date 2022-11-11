package kz.suterminal.repository;

import kz.suterminal.entity.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

interface PartnerRepository extends JpaRepository<PartnerEntity, Long> {
}
