import { inject } from "@angular/core";
import { Router } from "@angular/router";
import { AuthenticationService } from "../services/authentication.service";

export const AuthenticationGuard = () => {
    const authenticationService = inject(AuthenticationService);
    const router = inject(Router);

    // TODO: May be a good ideea to inform user that needs to be authenticated before accessing a resource if the guards dont let user inside
    return authenticationService.checkAuthentication().subscribe(authenticated => {
        if (!authenticated) {
            router.navigate(['']);
            return false;
        }
        return true;
    })
}