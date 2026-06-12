package com.skillhub.skillhub.repository;

import com.skillhub.skillhub.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
}