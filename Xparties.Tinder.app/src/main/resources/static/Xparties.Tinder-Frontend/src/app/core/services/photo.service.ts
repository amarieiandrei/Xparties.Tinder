import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpService } from './http.service';

@Injectable({
    providedIn: 'root',
})
export class PhotoService {
    private http = inject(HttpService);

    constructor() { }

    uploadPhoto(file: File): Observable<string> {
        const formData = new FormData();
        formData.append("data", file);

        return this.http.post<any>('/photo', formData);
    }

    getAllPhotos(): Observable<any> {
        return this.http.get<any>('/photo/photos');
    }
}