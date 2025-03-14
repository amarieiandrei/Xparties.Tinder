import { Injectable, Signal } from '@angular/core';
import { signal } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class ThemeService {
    private _theme = signal<string>('Light');

    constructor() {
        const storedTheme = localStorage.getItem('theme');
        if (storedTheme) {
            this.applyTheme(storedTheme);
        }
    }

    applyTheme(theme: string): void {
        const body = document.body;
        if (theme === 'Dark') {
            body.classList.add('Dark');
            body.classList.remove('Light');
        } else {
            body.classList.add('Light');
            body.classList.remove('Dark');
        }
        localStorage.setItem('theme', theme);
        this._theme.set(theme);
    }

    getCurrentThemeSignal(): Signal<string> {
        return this._theme;
    }

    toggleTheme(): void {
        const newTheme = this._theme() === 'Dark' ? 'Light' : 'Dark';
        this.applyTheme(newTheme);
    }
}