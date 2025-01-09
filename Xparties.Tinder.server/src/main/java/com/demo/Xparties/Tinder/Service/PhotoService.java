package com.demo.Xparties.Tinder.Service;

import com.demo.Xparties.Tinder.Exception.PhotoException.*;
import com.demo.Xparties.Tinder.Model.Photo;
import com.demo.Xparties.Tinder.Repository.PhotoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PhotoService {

    private final PhotoRepository photoRepository;

    public Photo createPhoto(MultipartFile file) {
        try {

            Optional<Photo> photoOptional = photoRepository.findByPhotoFileName(file.getOriginalFilename());
            if (photoOptional.isPresent()) {
                throw new PhotoAlreadyExists("photo with the same file name already exists.");
            }
            Photo photo = new Photo();
            photo.setExternalId(UUID.randomUUID().toString());
            photo.setContentType(file.getContentType());
            photo.setFileName(file.getOriginalFilename());
            photo.setData(file.getBytes());
            return photoRepository.save(photo);

        } catch (Exception e) {
            throw new PhotoNotCreated("photo could not be created.");
        }
    }

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public Photo getPhotoByExternalId(String externalId) {
        return photoRepository.findByExternalId(externalId)
                .orElseThrow(() -> new PhotoNotFound("photo with external id " + externalId + " could not be found."));
    }

    public void updatePhoto(String externalId, @Valid Photo updatedPhoto) {
        try {

            if (photoRepository.existsByExternalId(externalId)) {
                Optional<Photo> photoOptional = photoRepository.findByExternalId(externalId);

                if (photoOptional.isPresent()) {
                    Photo photo = photoOptional.get();

                    if (updatedPhoto.getFileName() != null) {
                        photo.setFileName(updatedPhoto.getFileName());
                    }
                    photoRepository.save(photo);
                }
            } else {
                throw new PhotoNotFound("photo with external id " + externalId + " could not be found.");
            }

        } catch (Exception e) {
            throw new PhotoNotUpdated("photo with external id " + externalId + " could not be updated.");
        }
    }

    @Transactional
    public void deletePhoto(String externalId) {
        try {

            if (photoRepository.existsByExternalId(externalId)) {
                photoRepository.deleteByExternalId((externalId));
            } else {
                throw new PhotoNotFound("photo with external id " + externalId + " could not be found.");
            }

        } catch (Exception e) {
            throw new PhotoNotDeleted("photo with external id " + externalId + " could not be deleted.");
        }
    }

    public void deleteAllPhotos() {
        photoRepository.deleteAll();
    }
}