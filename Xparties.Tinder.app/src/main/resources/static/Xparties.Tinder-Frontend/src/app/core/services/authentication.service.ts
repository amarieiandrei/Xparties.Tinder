import { computed, inject, Injectable, signal } from "@angular/core";
import { Observable, tap } from "rxjs";
import { HttpService } from "./http.service";
import { User } from "../models/user.interface";

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {
    private http = inject(HttpService);

    private _isAuthenticated = signal<boolean>(false);
    isAuthenticated = computed(() => this._isAuthenticated());

    private _currentUser = signal<User | null>(null);
    currentUser = computed(() => this._currentUser());

    constructor() { }

    checkAuthentication(): Observable<boolean> {
        return this.http.get<boolean>('/authentication/check').pipe(
            tap(authenticated => this._isAuthenticated.set(authenticated))
        );
    }

    getCurrentUser(): Observable<User> {
        return this.http.get<User>('/authentication/getCurrentUser').pipe(
            tap(currentUser => this._currentUser.set(currentUser))
        );
    }
}