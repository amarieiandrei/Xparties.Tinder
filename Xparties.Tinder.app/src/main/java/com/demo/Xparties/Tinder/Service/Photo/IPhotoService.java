package com.demo.Xparties.Tinder.Service.Photo;

import com.demo.Xparties.Tinder.Dto.PhotoDto.PhotoResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPhotoService {

    PhotoResponseDto createPhoto(MultipartFile file);
    List<PhotoResponseDto> getAllPhotos();
    PhotoResponseDto getPhotoByExternalId(String externalId);
    void deletePhoto(String externalId);
    void deleteAllPhotos();
}
