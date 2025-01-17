package com.demo.Xparties.Tinder.Service.Photo;

import com.demo.Xparties.Tinder.Converter.PhotoConverter;
import com.demo.Xparties.Tinder.Dto.PhotoDto.PhotoResponseDto;
import com.demo.Xparties.Tinder.Exception.PhotoException.*;
import com.demo.Xparties.Tinder.Model.Photo;
import com.demo.Xparties.Tinder.Repository.PhotoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PhotoService implements IPhotoService {

    private final PhotoRepository photoRepository;
    private final PhotoConverter photoConverter;

    @Override
    public PhotoResponseDto createPhoto(MultipartFile file) {
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
            return photoConverter.fromEntityToResponseDto(photoRepository.save(photo));

        } catch (Exception e) {
            throw new PhotoNotCreated("photo could not be created.");
        }
    }

    @Override
    public List<PhotoResponseDto> getAllPhotos() {
        return photoRepository
                .findAll()
                .stream()
                .map(photoConverter::fromEntityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public PhotoResponseDto getPhotoByExternalId(String externalId) {
        return photoConverter.fromEntityToResponseDto(
                photoRepository.findByExternalId(externalId)
                        .orElseThrow(() -> new PhotoNotFound("photo with external id " + externalId + " could not be found."))
        );
    }

    @Override
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

    @Override
    public void deleteAllPhotos() {
        photoRepository.deleteAll();
    }
}