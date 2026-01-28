export class Role {
    private id?: number;
    private role?: string;
    private usersIds?: number[];

    getId(): number | undefined {
        return this.id;
    }
    setId(id: number | undefined): void {
        this.id = id;
    }

    getRole(): string | undefined {
        return this.role;
    }
    setRole(role: string | undefined): void {
        this.role = role;
    }

    getUsersIds(): number[] | undefined {
        return this.usersIds;
    }
    setUsersIds(usersIds: number[] | undefined): void {
        this.usersIds = usersIds;
    }
}