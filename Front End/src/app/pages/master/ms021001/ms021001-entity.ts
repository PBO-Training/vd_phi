import { Hour } from "src/app/common/constant/type";

export interface ShiftWorkMaster {
  shiftWorkOptionID: number | string;
  shiftWorkOptionName: string;
  shiftWorkOptionCode: number | string;
  shiftWorkOptionStartTimeAM: string;
  shiftWorkOptionEndTimeAM : string;
  shiftWorkOptionStartTimePM : string;
  shiftWorkOptionEndTimePM : string;
  shiftWorkOptionDescription : string;
}
