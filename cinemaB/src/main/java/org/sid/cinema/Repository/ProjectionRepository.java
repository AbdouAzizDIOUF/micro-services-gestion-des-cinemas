package org.sid.cinema.Repository;

import org.sid.cinema.entity.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@RepositoryRestResource
public interface ProjectionRepository extends JpaRepository<Projection, Long> {
}
