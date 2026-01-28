export class PointTransaction {
    private id?: number;
    private customerId?: number;
    private type?: string;
    private points?: number;

    getId(): number | undefined {
        return this.id;
    }
    setId(id: number | undefined): void {
        this.id = id;
    }

    getCustomerId(): number | undefined {
        return this.customerId;
    }
    setCustomerId(customerId: number | undefined): void {
        this.customerId = customerId;
    }

    getType(): string | undefined {
        return this.type;
    }
    setType(type: string | undefined): void {
        this.type = type;
    }

    getPoints(): number | undefined {
        return this.points;
    }
    setPoints(points: number | undefined): void {
        this.points = points;
    }
}