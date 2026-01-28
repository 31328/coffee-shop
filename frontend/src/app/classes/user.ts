export class User {
    private id?: number;
    private firstName?: string;
    private lastName?: string;
    private email?: string;
    private password?: string;
    private enabled?: number;
    private rolesIdes?: number[];
    private authToken?: string;

    getId(): number | undefined {
        return this.id;
    }
    setId(id: number | undefined): void {
        this.id = id;
    }

    getFirstName(): string | undefined {
        return this.firstName;
    }
    setFirstName(firstName: string | undefined): void {
        this.firstName = firstName;
    }

    getLastName(): string | undefined {
        return this.lastName;
    }
    setLastName(lastName: string | undefined): void {
        this.lastName = lastName;
    }

    getEmail(): string | undefined {
        return this.email;
    }
    setEmail(email: string | undefined): void {
        this.email = email;
    }

    getPassword(): string | undefined {
        return this.password;
    }
    setPassword(password: string | undefined): void {
        this.password = password;
    }

    getEnabled(): number | undefined {
        return this.enabled;
    }
    setEnabled(enabled: number | undefined): void {
        this.enabled = enabled;
    }

    getRolesIdes(): number[] | undefined {
        return this.rolesIdes;
    }
    setRolesIdes(rolesIdes: number[] | undefined): void {
        this.rolesIdes = rolesIdes;
    }

    getAuthToken(): string | undefined {
        return this.authToken;
    }
    setAuthToken(authToken: string | undefined): void {
        this.authToken = authToken;
    }
}