export class OrderAddress {
    private city?: string;
    private address?: string;
    private postalCode?: string;

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
}