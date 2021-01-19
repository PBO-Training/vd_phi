import { FormGroup, FormControl } from '@angular/forms';
import * as type from '../../common/constant/type';

export class DateValidator {
  coupDate: Array<{
    from: string,
    to: string
  }>;
  // static dateLessThan(from: string, to: string, validatorField: {[key: string]: boolean}) {
  static dateLessThan(from: string, to: string) {
    return (group: FormGroup): { [key: string]: any } => {
      const f = group.controls[from];
      const t = group.controls[to];
      if (f.value === null || t.value === null) {
        return null;
      }
      // debugger;
      const df = new Date(f.value.split('-').reverse().join('-'));
      const dt = new Date(t.value.split('-').reverse().join('-'));
      // const checkDate = isNaN(df.getTime()) || isNaN(dt.getTime());
      if ((f.value !== null && t.value !== null) && df.getTime() > dt.getTime()) {
        return {
          dates: true,
        };
      }
      return null;
    };
  }

  static listDateLessThan(listDate) {
    return (group: FormGroup): { [key: string]: any } => {
      const objDate = {};
      listDate.forEach(element => {
        const f = group.controls[element.from];
        const t = group.controls[element.to];

        if (f.value === null ||  t.value === null) {
          return null;
        }

        const df = new Date(f.value.split('-').reverse().join('-'));
        const dt = new Date(t.value.split('-').reverse().join('-'));

        if ((f.value !== null && t.value !== null) && df.getTime() > dt.getTime()) {
          objDate[element.to] = element.to;
        }
      });
      return objDate;
    };
  }

  static startDate(control: FormControl): { [key: string]: any } {
    const startDate = control.value;
    if (!startDate) { return null; }
    const ds = new Date(startDate.split('-').reverse().join('-'));
    const now = new Date();
    if ((startDate !== null) && ds.getTime() > now.getTime()) {
      return {
        greater: true
      };
    }
    return null;
  }
  static checkYear(control: FormControl): { [key: string]: any } {
    const Year = control.value;
    const ds = Year;
    const now = new Date();
    if ((Year !== null) && ds > now.getFullYear()) {
      return {
        checkYear: true
      };
    }
    return null;
  }


  static compareToTime(hourAM: string, minAM: string, hourPM: string, minPM: string) {
    return (formgroup: FormGroup): { [key: string]: any } => {
      const houram = formgroup.controls[hourAM];
      const minam = formgroup.controls[minAM];
      const hourpm = formgroup.controls[hourPM];
      const minpm = formgroup.controls[minPM];
      if (houram.value === null || minam.value === null || hourpm.value === null || minpm.value === null) {
        return null;
      }
      if ((houram.value !== null && minam.value !== null) && houram.value == type.TimeDefault.endHourAM && minam.value >= type.TimeDefault.endMinAM) {
        if ((hourpm.value !== null && minpm.value !== null) && hourpm.value == type.TimeDefault.startHourPM && minpm.value == type.TimeDefault.startMinPM) {
          return {
            breaktime: true,
            elementError: hourAM
          };
        }
        if ((hourpm.value !== null && minpm.value !== null) && hourpm.value == type.TimeDefault.endHourAM && minpm.value >= type.TimeDefault.endMinAM) {
          return {
            breaktime: true,
            elementError: hourAM
          };
        }        
      }
      if (houram.value !== null && minam.value !== null && hourpm.value !== null && minpm.value !== null) {
        if (Number(houram.value) > Number(hourpm.value)) {
          return {
            times: true,
            elementError: hourAM
          }          
        }
        if(Number(houram.value) === Number(hourpm.value)){
          if(Number(minam.value) >= Number(minpm.value)){
            return {
              times: true,
              elementError: hourAM
            }
          }         
        }        
      }
      if(Number(minpm.value) > type.TimeDefault.endMinPM && Number(hourpm.value) >= type.TimeDefault.endHourPM){
        return {
          worktimePM: true,
          elementError: hourPM
        }
      }
      if(Number(houram.value) < type.TimeDefault.startHourPM){
        if(Number(minam.value) > type.TimeDefault.endMinAM && Number(houram.value) >= type.TimeDefault.endHourAM){
          return {
            worktimeAM: true,
            elementError: hourAM
          }
        }
        if(Number(minpm.value) > type.TimeDefault.endMinAM && Number(hourpm.value) >= type.TimeDefault.endHourAM && Number(hourpm.value) < type.TimeDefault.startHourPM){
          return {
            worktimeAMPM: true,
            elementError: hourPM
          }
        }
      }
      if(Number(houram.value) <= type.TimeDefault.startHourAM){
        if(Number(houram.value) < type.TimeDefault.startHourAM){
          return {
            startWorktimeAM: true,
            elementError: hourAM
          }
        }   
        if(Number(houram.value) <= type.TimeDefault.startHourAM && Number(minam.value) < type.TimeDefault.startMinAM){
          return {
            startWorktimeAM: true,
            elementError: hourAM
          }
        }      
      }
      return null;
    };
  }
}
