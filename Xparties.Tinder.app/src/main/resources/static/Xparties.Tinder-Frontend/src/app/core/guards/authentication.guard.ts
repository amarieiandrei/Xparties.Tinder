import { inject } from "@angular/core";
import { Router } from "@angular/router";
import { AuthenticationService } from "../services/authentication.service";

export const AuthenticationGuard = () => {
    const authenticationService = inject(AuthenticationService);
    const router = inject(Router);

    // TODO: May be a good ideea to inform user that needs to be authenticated before accessing a resource if the guards dont let user inside
    // TODO: Needs a loading spinner into dashboard until all the request are done, or for example if error send user back to login
    return authenticationService.checkAuthentication().subscribe({
        next: (authenticated) => {
            if (!authenticated) {
                router.navigate(['']);
                return false;
            }
            return true;
        },
        error: (err) => {
            // TODO: Refactor the errors in UI
            console.error('Error checking authentication:', err);
            router.navigate(['']);
            return false;
        }
    })
}