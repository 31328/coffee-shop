export class Customer {
    private id?: number;
    private points?: number;
    private userId?: number;
    private cartId?: number;
    private addressId?: number;
    private phone?: string;
    private registrationStr?: string;

    getId(): number | undefined {
        return this.id;
    }
    setId(id: number | undefined): void {
        this.id = id;
    }

    getPoints(): number | undefined {
        return this.points;
    }
    setPoints(points: number | undefined): void {
        this.points = points;
    }

    getUserId(): number | undefined {
        return this.userId;
    }
    setUserId(userId: number | undefined): void {
        this.userId = userId;
    }

    getCartId(): number | undefined {
        return this.cartId;
    }
    setCartId(cartId: number | undefined): void {
        this.cartId = cartId;
    }

    getAddressId(): number | undefined {
        return this.addressId;
    }
    setAddressId(addressId: number | undefined): void {
        this.addressId = addressId;
    }

    getPhone(): string | undefined {
        return this.phone;
    }
    setPhone(phone: string | undefined): void {
        this.phone = phone;
    }

    getRegistrationStr(): string | undefined {
        return this.registrationStr;
    }
    setRegistrationStr(registrationStr: string | undefined): void {
        this.registrationStr = registrationStr;
    }
}
