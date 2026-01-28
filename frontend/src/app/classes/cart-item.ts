export class CartItem {
    private id?: number;
    private cartId?: number;
    private quantity?: number;
    private price?: number;

    getId(): number | undefined {
        return this.id;
    }
    setId(id: number | undefined): void {
        this.id = id;
    }

    getCartId(): number | undefined {
        return this.cartId;
    }
    setCartId(cartId: number | undefined): void {
        this.cartId = cartId;
    }

    getQuantity(): number | undefined {
        return this.quantity;
    }
    setQuantity(quantity: number | undefined): void {
        this.quantity = quantity;
    }

    getPrice(): number | undefined {
        return this.price;
    }
    setPrice(price: number | undefined): void {
        this.price = price;
    }
}
