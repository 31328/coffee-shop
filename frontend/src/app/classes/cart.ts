export class Cart {
    private id?: number;
    private cartItemsIds?: number[];
    private cartPrice?: number;
    private customerId?: number;

    getId(): number | undefined {
        return this.id;
    }
    setId(id: number | undefined): void {
        this.id = id;
    }

    getCartItemsIds(): number[] | undefined {
        return this.cartItemsIds;
    }
    setCartItemsIds(cartItemsIds: number[] | undefined): void {
        this.cartItemsIds = cartItemsIds;
    }

    getCartPrice(): number | undefined {
        return this.cartPrice;
    }
    setCartPrice(cartPrice: number | undefined): void {
        this.cartPrice = cartPrice;
    }

    getCustomerId(): number | undefined {
        return this.customerId;
    }
    setCustomerId(customerId: number | undefined): void {
        this.customerId = customerId;
    }
}