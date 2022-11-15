package kz.suterminal.repository;

import kz.suterminal.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

interface PartnerRepository extends JpaRepository<Partner, Long> {
}
