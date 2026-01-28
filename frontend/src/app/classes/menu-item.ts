export class MenuItem {
    private menuId?: number;

    getMenuId(): number | undefined {
        return this.menuId;
    }

    setMenuId(menuId: number | undefined): void {
        this.menuId = menuId;
    }
}
