export class DeliveryAddress {
    private id?: number;
    private city?: string;
    private address?: string;
    private postalCode?: string;
    private customerId?: number;

    getId(): number | undefined {
        return this.id;
    }
    setId(id: number | undefined): void {
        this.id = id;
    }

    getCity(): string | undefined {
        return this.city;
    }
    setCity(city: string | undefined): void {
        this.city = city;
    }

    getAddress(): string | undefined {
        return this.address;
    }
    setAddress(address: string | undefined): void {
        this.address = address;
    }

    getPostalCode(): string | undefined {
        return this.postalCode;
    }
    setPostalCode(postalCode: string | undefined): void {
        this.postalCode = postalCode;
    }

    getCustomerId(): number | undefined {
        return this.customerId;
    }
    setCustomerId(customerId: number | undefined): void {
        this.customerId = customerId;
    }
}
