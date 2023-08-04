package com.example.ideas.admission.repository;

import com.example.ideas.admission.model.Admission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {

}
