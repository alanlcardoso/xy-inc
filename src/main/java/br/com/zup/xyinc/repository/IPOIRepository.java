package br.com.zup.xyinc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.xyinc.model.POI;

public interface IPOIRepository extends JpaRepository<POI, Long> {
}