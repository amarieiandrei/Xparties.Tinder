package com.demo.Xparties.Tinder.Web;

import com.demo.Xparties.Tinder.Dto.PhotoDto.PhotoResponseDto;
import com.demo.Xparties.Tinder.Service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RequestMapping("/photo")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PhotoController {

    private final PhotoService photoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PhotoResponseDto createPhoto(@RequestPart("data") MultipartFile file) {
        return photoService.createPhoto(file);
    }

    @GetMapping(path = "/photos")
    public List<PhotoResponseDto> getAllPhotos() {
        return photoService.getAllPhotos();
    }

    @GetMapping(path = "/{externalId}")
    public PhotoResponseDto getPhotoByExternalId(@PathVariable String externalId) {
        return photoService.getPhotoByExternalId(externalId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{externalId}")
    public void deletePhoto(@PathVariable String externalId) {
        photoService.deletePhoto(externalId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping()
    public void deleteAllPhotos() {
        photoService.deleteAllPhotos();
    }
}
