
export const pageOpiton = [5, 10, 15, 20, 30, 50];

export class Listprops  {
    pageSize: number;
    pageNumDefault = 0;
    pageNum: number;
    totalRecord: number;
    // Số lượng trang tối đa để hiển thị.
    maxSize = 3;
    page = 1;
    pageOpiton = pageOpiton;
    setOfCheckedId = new Set<number>();
    ids: number[] = [];
    isCollapsed = false;
    loading = true;
    constructor() {
    }
}
