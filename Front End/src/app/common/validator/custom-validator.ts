import { FormArray, FormControl, FormGroup, ValidationErrors, ValidatorFn } from '@angular/forms';
export class CustomReactiveFormValidator {
    static updateTreeValidity(group: FormGroup | FormArray) {
        Object.keys(group.controls).forEach((key: string) => {
            const abstractControl = group.controls[key];
            if (abstractControl instanceof FormGroup || abstractControl instanceof FormArray) {
                this.updateTreeValidity(abstractControl);
            } else {
                abstractControl.updateValueAndValidity({ onlySelf: false, emitEvent: false });
                abstractControl.pristine = false;
            }
        });
    }

    static isDuplicate(controlName: string): ValidatorFn {
        return (form: FormGroup): ValidationErrors | null => {
            // Validaton code here
            Object.keys(form.controls).forEach((key: string) => {
                const abstractControl = form.controls[key];
                if (abstractControl instanceof FormArray) {
                    const abstractControlChildren = [];
                    const uniqValues = [];
                    const hasDuplicate = [];
                    // clear errors form controls
                    abstractControl.controls.map(control => {
                        if (control.hasError('duplicate')) {
                            control.setErrors(null);
                        }
                    });
                    // get child control of form arrays
                    Object.keys(abstractControl.controls).map((keyChildren: string) => {
                        abstractControlChildren.push(abstractControl.controls[keyChildren].value);
                    });
                    if (abstractControlChildren.length > 0) {
                        // tslint:disable-next-line:prefer-for-of
                        for (let x = 0; x < abstractControlChildren.length; x++) {
                            for (let k = 1; k < abstractControlChildren.length; k++) {
                                if (abstractControlChildren[x][controlName] === abstractControlChildren[k][controlName]
                                    && abstractControlChildren[x] !== abstractControlChildren[k]) {
                                    uniqValues.push(abstractControlChildren[x][controlName]);

                                }
                            }
                        }
                    }
                    // get index of array control
                    const listControl = abstractControlChildren.map(val => val[controlName]);
                    uniqValues.map(value => {
                        let idx = listControl.indexOf(value);
                        while (idx !== -1) {
                            hasDuplicate.push(idx);
                            idx = listControl.indexOf(value, idx + 1);
                        }
                    });
                    // const idsCanCheck = abstractControl.controls.map(control)
                    if (hasDuplicate.length > 0) {
                        hasDuplicate.map(index => {
                            // set errors control duplicate
                            abstractControl.controls[index].setErrors({
                                duplicate: true
                            });
                        });
                    }
                }
            });
            return null;

        };
    }
    // static dateLessThan(from: string, to: string, validatorField: {[key: string]: boolean}) {
    static dateLessThan(from: string, to: string) {
        return (group: FormGroup): { [key: string]: any } => {
            const f = group.controls[from];
            const t = group.controls[to];
            const df = new Date(f.value);
            const dt = new Date(t.value);
            const checkDate = isNaN(df.getTime()) || isNaN(dt.getTime());
            if ((f.value !== null && t.value !== null) && df.getTime() > dt.getTime()) {
                return {
                    dates: true
                };
            }

            if (checkDate) {
                return {
                    dates: 'inValidFormat',
                };
            }
            return null;
        };
    }
    static startDate(control: FormControl): { [key: string]: any } {
        const startDate = control.value;
        const ds = new Date(startDate);
        const now = new Date();
        if ((startDate !== null) && ds.getTime() > now.getTime()) {
            return {
                greater: true
            };
        }
        return null;
    }


}

