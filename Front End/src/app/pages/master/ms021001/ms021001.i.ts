
export interface ShiftWorkMaster {
  shiftWorkOptionID: number;
  shiftWorkOptionName: string;
  shiftWorkOptionCode: number | string;
  shiftWorkOptionTime: string;
  shiftWorkOptionDescription : string;
}

export interface ShiftWorkRequest {
  pageNum: number | string;
  pageSize: number | string;
  shiftWorkOptionName?: number | string;
  shiftWorkOptionCode?: number | string;
}
