export class OrderItem {
    private id?: number;
    private quantity?: number;
    private price?: number;
    private orderId?: number;

    getId(): number | undefined {
        return this.id;
    }
    setId(id: number | undefined): void {
        this.id = id;
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

    getOrderId(): number | undefined {
        return this.orderId;
    }
    setOrderId(orderId: number | undefined): void {
        this.orderId = orderId;
    }
}