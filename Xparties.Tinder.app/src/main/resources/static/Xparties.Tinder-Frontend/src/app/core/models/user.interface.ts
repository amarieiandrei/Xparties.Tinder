import { OAuth2Provider } from "./oauth2-provider.enum";

export interface User {
    name: string;
    email: string;
    provider: OAuth2Provider;
}