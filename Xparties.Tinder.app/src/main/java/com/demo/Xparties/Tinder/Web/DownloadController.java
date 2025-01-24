package com.demo.Xparties.Tinder.Web;

import com.demo.Xparties.Tinder.Service.Download.DownloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/download")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DownloadController {

    private final DownloadService downloadService;

    @GetMapping(path = "/{externalId}")
    public ResponseEntity<byte[]> downloadPhoto(@PathVariable String externalId) {
        return downloadService.downloadPhoto(externalId);
    }

}