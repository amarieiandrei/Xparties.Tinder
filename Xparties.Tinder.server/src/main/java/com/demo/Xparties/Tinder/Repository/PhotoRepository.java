package com.demo.Xparties.Tinder.Repository;

import com.demo.Xparties.Tinder.Model.Entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    boolean existsByExternalId(String externalId);
    void deleteByExternalId(String externalId);

    Optional<Photo> findByExternalId(String externalId);

    @Query("SELECT p FROM Photo p WHERE p.fileName = ?1")
    Optional<Photo> findByPhotoFileName(String fileName);
}
