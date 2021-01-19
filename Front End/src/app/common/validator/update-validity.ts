import { FormArray, FormGroup } from '@angular/forms';

export class ValidatorForm {
  updateTreeValidity(group: FormGroup | FormArray): void {
    Object.keys(group.controls).forEach((key: string) => {
      const abstractControl = group.controls[key];
      if (abstractControl instanceof FormGroup || abstractControl instanceof FormArray) {
        this.updateTreeValidity(abstractControl);
      } else {
        abstractControl.updateValueAndValidity({ onlySelf: false, emitEvent: false });
      }
    });
  }
}
