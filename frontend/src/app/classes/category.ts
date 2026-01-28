export class CartItem {
    private id?: number;
    private name?: string;
    private productsIdes?: number[];

    getId(): number | undefined {
        return this.id;
    }
    setId(id: number | undefined): void {
        this.id = id;
    }

    getName(): string | undefined {
        return this.name;
    }
    setName(name: string | undefined): void {
        this.name = name;
    }

    getProductsIdes(): number[] | undefined {
        return this.productsIdes;
    }
    setProductsIdes(productsIdes: number[] | undefined): void {
        this.productsIdes = productsIdes;
    }
}
