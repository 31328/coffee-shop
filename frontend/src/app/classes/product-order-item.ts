export class ProductOrderItem {
    private productName?: string;
    private productPrice?: number;

    getProductName(): string | undefined {
        return this.productName;
    }
    setProductName(productName: string | undefined): void {
        this.productName = productName;
    }

    getProductPrice(): number | undefined {
        return this.productPrice;
    }
    setProductPrice(productPrice: number | undefined): void {
        this.productPrice = productPrice;
    }
}
