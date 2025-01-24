package com.demo.Xparties.Tinder.Service.Download;

import com.demo.Xparties.Tinder.Exception.DownloadException.DownloadNotComplete;
import com.demo.Xparties.Tinder.Exception.PhotoException.PhotoNotFound;
import com.demo.Xparties.Tinder.Model.Entity.Photo;
import com.demo.Xparties.Tinder.Repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DownloadService implements IDownloadService {

    private final PhotoRepository photoRepository;

    @Override
    public ResponseEntity<byte[]> downloadPhoto(String externalId) {
        try {

            Optional<Photo> photoOptional = photoRepository.findByExternalId(externalId);
            if (photoOptional.isPresent()) {
                Photo photo = photoOptional.get();

                byte[] data = photo.getData();
                HttpHeaders headers = createDownloadHeaders(photo);

                return new ResponseEntity<>(data, headers, HttpStatus.OK);
            } else {
                throw new PhotoNotFound("photo with external id " + externalId + " could not be found.");
            }

        } catch (Exception e) {
            throw new DownloadNotComplete("photo could not be downloaded.");
        }
    }

    private HttpHeaders createDownloadHeaders(Photo photo) {
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(photo.getContentType()));
            ContentDisposition build = ContentDisposition
                    .builder("attachment")
                    .filename(photo.getFileName())
                    .build();
            headers.setContentDisposition(build);
            return headers;

        } catch (Exception e) {
            throw new DownloadNotComplete("download headers could not be created.");
        }
    }
}
