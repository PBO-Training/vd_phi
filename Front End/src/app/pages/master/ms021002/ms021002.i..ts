export interface updateRequest {
  shiftWorkOptionID: number | string;
  shiftWorkOptionName: string;
  shiftWorkOptionCode: number | string;
  shiftWorkOptionStartTimeAM: string;
  shiftWorkOptionEndTimeAM : string;
  shiftWorkOptionStartTimePM : string;
  shiftWorkOptionEndTimePM : string;
  shiftWorkOptionDescription? : string;
}
