package kpfu.itis.repository;

import kpfu.itis.model.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, UUID> {
    Optional<AuthorEntity> findByName(String name);

    Boolean existsByName(String name);
}
