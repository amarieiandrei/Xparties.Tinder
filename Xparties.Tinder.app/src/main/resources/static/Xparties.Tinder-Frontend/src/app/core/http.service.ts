import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";


@Injectable({
    providedIn: 'root'
})
export class HttpService {
    private baseUrl = 'http://localhost:8080/api';

    constructor(private http: HttpClient) { }

    get<T>(url: string, params?: HttpParams): Observable<T> {
        return this.http.get<T>(`${this.baseUrl}${url}`, {
            withCredentials: true,
            headers: this.getHeaders(),
            params
        })
    }

    post<T>(url: string, body: any): Observable<T> {
        return this.http.post<T>(`${this.baseUrl}${url}`, body, {
            withCredentials: true,
            headers: this.getHeaders(),
        });
    }

    put<T>(url: string, body: any): Observable<T> {
        return this.http.put<T>(`${this.baseUrl}${url}`, body, {
            withCredentials: true,
            headers: this.getHeaders(),
        });
    }

    delete<T>(url: string): Observable<T> {
        return this.http.delete<T>(`${this.baseUrl}${url}`, {
            withCredentials: true,
            headers: this.getHeaders(),
        });
    }

    private getHeaders(): HttpHeaders {
        return new HttpHeaders({
            'Content-Type': 'application/json'
        })
    }
}