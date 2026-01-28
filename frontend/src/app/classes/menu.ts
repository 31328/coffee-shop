export class Menu {
    private id?: number;
    private name?: string;
    private price?: number;
    private points?: number;
    private productIdes?: number[];

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

    getPrice(): number | undefined {
        return this.price;
    }
    setPrice(price: number | undefined): void {
        this.price = price;
    }

    getPoints(): number | undefined {
        return this.points;
    }
    setPoints(points: number | undefined): void {
        this.points = points;
    }

    getProductIdes(): number[] | undefined {
        return this.productIdes;
    }
    setProductIdes(productIdes: number[] | undefined): void {
        this.productIdes = productIdes;
    }
}
