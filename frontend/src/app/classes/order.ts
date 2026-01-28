import { OrderAddress } from "./order-address";

export class Order {
    private id?: number;
    private cartId?: number;
    private price?: number;
    private createTimeStr?: string;
    private address?: OrderAddress;
    private orderedItemsIdes?: number[];

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

    getPrice(): number | undefined {
        return this.price;
    }
    setPrice(price: number | undefined): void {
        this.price = price;
    }

    getCreateTimeStr(): string | undefined {
        return this.createTimeStr;
    }
    setCreateTimeStr(createTimeStr: string | undefined): void {
        this.createTimeStr = createTimeStr;
    }

    getAddress(): OrderAddress | undefined {
        return this.address;
    }
    setAddress(address: OrderAddress | undefined): void {
        this.address = address;
    }

    getOrderedItemsIdes(): number[] | undefined {
        return this.orderedItemsIdes;
    }
    setOrderedItemsIdes(orderedItemsIdes: number[] | undefined): void {
        this.orderedItemsIdes = orderedItemsIdes;
    }
}
