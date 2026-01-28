export class MenuOrderItem {
    private menuName?: string;
    private menuPrice?: number;

    getMenuName(): string | undefined {
        return this.menuName;
    }
    setMenuName(menuName: string | undefined): void {
        this.menuName = menuName;
    }

    getMenuPrice(): number | undefined {
        return this.menuPrice;
    }
    setMenuPrice(menuPrice: number | undefined): void {
        this.menuPrice = menuPrice;
    }
}
