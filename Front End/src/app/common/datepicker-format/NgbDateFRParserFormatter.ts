import { Injectable } from '@angular/core';
import { NgbDateParserFormatter, NgbDateStruct, NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';

@Injectable()
export class CustomAdapter extends NgbDateAdapter<string> {
  readonly DELIMITER = '-';

  toModel(date: NgbDateStruct): string {
    return date ? date.day + this.DELIMITER + date.month + this.DELIMITER + date.year : null;
  }
  fromModel(value: string): NgbDateStruct | null {

    if (value) {
      const date = value.split(this.DELIMITER);

      return {
        day: parseInt(date[0], 10),
        month: parseInt(date[1], 10),
        year: parseInt(date[2], 10)
      };
    }
    return null;
  }

}
@Injectable()
export class CustomDateParserFormatter extends NgbDateParserFormatter {

  readonly DELIMITER = '-';

  parse(value: string): NgbDateStruct | null {
    if (value) {
      const date = value.split(this.DELIMITER);
      if (date.length > 1) {
        return {
          day: parseInt(date[0], 10),
          month: parseInt(date[1], 10),
          year: parseInt(date[2], 10)
        };
      } else {
        const date2 = value.split('/');
        return {
          day: parseInt(date2[0], 10),
          month: parseInt(date2[1], 10),
          year: parseInt(date2[2], 10)
        };
      }
    }
    return null;
  }

  format(date: NgbDateStruct | null): string {
    // tslint:disable-next-line:max-line-length
    return date ? (date.day < 10 ? '0' + date.day : date.day) + this.DELIMITER + (date.month < 10 ? '0' + date.month : date.month) + this.DELIMITER + date.year : '';
  }
}

