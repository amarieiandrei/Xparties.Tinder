package com.demo.Xparties.Tinder.Service.Download;

import org.springframework.http.ResponseEntity;

public interface IDownloadService {

    ResponseEntity<byte[]> downloadPhoto(String externalId);
}
