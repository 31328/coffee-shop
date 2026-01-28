export class ProductItem {
    private productId?: number;

    getProductId(): number | undefined {
        return this.productId;
    }

    setProductId(productId: number | undefined): void {
        this.productId = productId;
    }
}