export class Product {
    private id?: number;
    private name?: string;
    private price?: number;
    private points?: number;
    private menusIdes?: number[];
    private imageUrl?: string;
    private productDescription?: string;
    private productCategoryId?: number;

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

    getMenusIdes(): number[] | undefined {
        return this.menusIdes;
    }
    setMenusIdes(menusIdes: number[] | undefined): void {
        this.menusIdes = menusIdes;
    }

    getImageUrl(): string | undefined {
        return this.imageUrl;
    }
    setImageUrl(imageUrl: string | undefined): void {
        this.imageUrl = imageUrl;
    }

    getProductDescription(): string | undefined {
        return this.productDescription;
    }
    setProductDescription(productDescription: string | undefined): void {
        this.productDescription = productDescription;
    }

    getProductCategoryId(): number | undefined {
        return this.productCategoryId;
    }
    setProductCategoryId(productCategoryId: number | undefined): void {
        this.productCategoryId = productCategoryId;
    }
}


