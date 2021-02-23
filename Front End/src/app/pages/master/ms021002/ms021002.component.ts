import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';
import * as _ from 'lodash';
import { LengthConstant } from 'src/app/common/constant/length';
import { ModalConfirmComponent } from 'src/app/theme/shared/components/modal-confirm/modal-confirm.component';
import { ToastService } from 'src/app/theme/shared/components/toast-container/toast-service';
import { updateRequest } from './ms021002.i.';
import { MS021002Service } from './ms021002.service';


@Component({
  selector: 'app-ms021002',
  templateUrl: './ms021002.component.html',
})
export class MS021002Component implements OnInit {
  isUpdate = false;
  isDuplicate = false;
  submitted = false;
  isCheckValueDirty = false;
  isCheckHourAM = false;
  isCheckHourPM = false;
  isCheckTimeWork = false;
  HourWorkSystem = 0;
  MinWorkSystem = 0;
  timWorkDefault = {
    timeWorkHourAM :0,
    timeWorkMinAM : 0,
    timeWorkHourPM :0,
    timeWorkMinPM : 0,
  };
  req : any;
  formData: FormGroup;
  hourList : any[] = [];
  minList : any[]=[];
  updRequest : updateRequest;
  maxlenghtName = {value : LengthConstant.MAX_LENGTH_NAME};
  maxlenghtCode = {value : LengthConstant.MAX_LENGTH_CODE};
  maxlenghtDescription = {value : LengthConstant.MAX_LENGTH_NAME};
  valueOld = {
          shiftWorkID : '',
          shiftWorkCode : '',
          shiftWorkName : '',
          shiftWorkDescriptions : '',
          shiftWorkStartHourAM : '',
          shiftWorkStartMinAM : '',
          shiftWorkEndHourAM : '',
          shiftWorkEndMinAM : '',
          shiftWorkStartHourPM : '',
          shiftWorkStartMinPM : '',
          shiftWorkEndHourPM : '',
          shiftWorkEndMinPM : ''
  }
  constructor(private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private ms021002Service : MS021002Service,
    private toastService: ToastService,
    private modalService: NgbModal,
    private translate: TranslateService,) {
    if(this.route.snapshot.params.id){
      this.isUpdate = true;

    }

    this.formData = this.fb.group({
      shiftWorkID: [''],
      shiftWorkName: ['',
        Validators.compose([
          Validators.required,
          Validators.maxLength(LengthConstant.MAX_LENGTH_NAME),
        ]),],
      shiftWorkDescriptions: ['',
        Validators.compose([
          Validators.maxLength(LengthConstant.MAX_LENGTH_CODE),
      ]),],
      shiftWorkCode: ['',
        Validators.compose([
          Validators.required,
          Validators.maxLength(LengthConstant.MAX_LENGTH_CODE),

      ]),],
      shiftWorkStartHourAM : ['',
        Validators.compose([
        Validators.required,
      ]),],
      shiftWorkStartMinAM :['',
        Validators.compose([
          Validators.required,
      ]),],
      shiftWorkStartHourPM : ['',
        Validators.compose([
          Validators.required,
      ]),],
      shiftWorkStartMinPM :['',
        Validators.compose([
          Validators.required,
      ]),],
      shiftWorkEndHourAM : ['',
        Validators.compose([
          Validators.required,
      ]),],
      shiftWorkEndHourPM : ['',
        Validators.compose([
          Validators.required,
      ]),],
      shiftWorkEndMinAM : ['',
        Validators.compose([
          Validators.required,
      ]),],
      shiftWorkEndMinPM : ['',
        Validators.compose([
          Validators.required,
      ]),],
    });

  }
  //Thực hiên event nút Save
  submitForm(){
    console.log(this.HourWorkSystem);
    console.log(this.MinWorkSystem);

    this.submitted = true;
    this.isCheckValueDirty = this.CheckValue(this.valueOld,this.formData.value);
    this.isCheckHourAM = this.checkHourAM(this.formData.value.shiftWorkStartHourAM,this.formData.value.shiftWorkEndHourAM);
    this.isCheckHourPM = this.checkHourPM(this.formData.value.shiftWorkStartHourPM,this.formData.value.shiftWorkEndHourPM);
    this.updRequest = {
      shiftWorkOptionID : this.formData.value.shiftWorkID,
      shiftWorkOptionCode : this.formData.value.shiftWorkCode.toUpperCase(),
      shiftWorkOptionName : this.formData.value.shiftWorkName,
      shiftWorkOptionDescription : this.formData.value.shiftWorkDescriptions,
      shiftWorkOptionStartTimeAM : this.formData.value.shiftWorkStartHourAM + ":" + this.formData.value.shiftWorkStartMinAM,
      shiftWorkOptionStartTimePM : this.formData.value.shiftWorkStartHourPM + ":" + this.formData.value.shiftWorkStartMinPM,
      shiftWorkOptionEndTimeAM : this.formData.value.shiftWorkEndHourAM + ":" + this.formData.value.shiftWorkEndMinAM,
      shiftWorkOptionEndTimePM : this.formData.value.shiftWorkEndHourPM + ":" + this.formData.value.shiftWorkEndMinPM
    };
    if(!this.formData.invalid){
      this.isCheckTimeWork = this.checkTimeInput(this.formData.value.shiftWorkStartHourAM,this.formData.value.shiftWorkStartMinAM,
                      this.formData.value.shiftWorkEndHourAM,this.formData.value.shiftWorkEndMinAM,
                      this.formData.value.shiftWorkStartHourPM,this.formData.value.shiftWorkStartMinPM,
                      this.formData.value.shiftWorkEndHourPM,this.formData.value.shiftWorkEndMinPM,this.HourWorkSystem,this.MinWorkSystem);
      if(this.isUpdate === true  && this.isCheckValueDirty === false ){
        if(!this.isCheckHourAM){
          this.formData.get('shiftWorkStartHourAM').setErrors({
            Err : true
          })
        }
        if(!this.isCheckHourPM ){
          this.formData.get('shiftWorkStartHourPM').setErrors({
            Err : true
          })
        }
        if(this.isCheckHourAM && this.isCheckHourPM) {
          if(this.isCheckTimeWork === true){
            this.ms021002Service.updateShiftwork(this.updRequest).subscribe((resp) =>{
              if(resp.error === null){
                this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
                this.router.navigate(['/vacation-shift-work']);
              }
              else{
                this.formData.get(resp.error.itemName).setErrors({
                  serverError: resp.error
                });
              }
            })
          }else if(this.isCheckTimeWork === false){
            this.toastService.show('Not enough time '+this.HourWorkSystem +' hours', { classname: 'bg-warning text-light', delay: 3000 });
          }
        }
      }else if(this.isUpdate === false && this.isCheckValueDirty === false){
        if(!this.isCheckHourAM){
          this.formData.get('shiftWorkStartHourAM').setErrors({
            Err : true
          })
        }
        if(!this.isCheckHourPM ){
          this.formData.get('shiftWorkStartHourPM').setErrors({
            Err : true
          })
        }
        if(this.isCheckHourAM && this.isCheckHourPM){
          if(this.isCheckTimeWork === true ){
            this.ms021002Service.createShiftwork(this.updRequest).subscribe(resp => {
              if(resp.error === null){
                this.toastService.show('notification-message.save-success', { classname: 'bg-success text-light', delay: 3000 });
                this.router.navigate(['/vacation-shift-work']);
              }
              else{
                this.formData.get(resp.error.itemName).setErrors({
                  serverError: resp.error
                });
              }
            });
          }else if(this.isCheckTimeWork === false){
            this.toastService.show('Not enough time '+this.HourWorkSystem +' hours', { classname: 'bg-warning text-light', delay: 3000 });
          }
        }
      }else if(this.isCheckValueDirty === true){
        this.router.navigate(['/vacation-shift-work']);
      }
    }
  }

  initData(){
    this.getTimeWorkSystem();
    if(this.isUpdate){
      this.req = {"shiftWorkOptionID":this.route.snapshot.params.id}
      this.ms021002Service.getDetailShifwork(this.req).subscribe(resp => {
        this.formData.patchValue({
          shiftWorkID : resp.content.shiftWorkOptionID,
          shiftWorkCode : resp.content.shiftWorkOptionCode,
          shiftWorkName : resp.content.shiftWorkOptionName,
          shiftWorkDescriptions : resp.content.shiftWorkOptionDescription,
          shiftWorkStartHourAM : resp.content.shiftWorkOptionTimeStartAM.split(":", 1),
          shiftWorkStartMinAM : resp.content.shiftWorkOptionTimeStartAM.slice(-2),
          shiftWorkEndHourAM : resp.content.shiftWorkOptionTimeEndAM.split(":", 1),
          shiftWorkEndMinAM : resp.content.shiftWorkOptionTimeEndAM.slice(-2),
          shiftWorkStartHourPM : resp.content.shiftWorkOptionTimeStartPM.split(":", 1),
          shiftWorkStartMinPM : resp.content.shiftWorkOptionTimeStartPM.slice(-2),
          shiftWorkEndHourPM : resp.content.shiftWorkOptionTimeEndPM.split(":", 1),
          shiftWorkEndMinPM : resp.content.shiftWorkOptionTimeEndPM.slice(-2)
        })
        this.valueOld.shiftWorkID = resp.content.shiftWorkOptionID
        this.valueOld.shiftWorkCode = resp.content.shiftWorkOptionCode
        this.valueOld.shiftWorkName = resp.content.shiftWorkOptionName
        this.valueOld.shiftWorkDescriptions = resp.content.shiftWorkOptionDescription
        this.valueOld.shiftWorkStartHourAM = resp.content.shiftWorkOptionTimeStartAM.split(":", 1)
        this.valueOld.shiftWorkStartMinAM = resp.content.shiftWorkOptionTimeStartAM.slice(-2)
        this.valueOld.shiftWorkEndHourAM = resp.content.shiftWorkOptionTimeEndAM.split(":", 1)
        this.valueOld.shiftWorkEndMinAM = resp.content.shiftWorkOptionTimeEndAM.slice(-2)
        this.valueOld.shiftWorkStartHourPM = resp.content.shiftWorkOptionTimeStartPM.split(":", 1)
        this.valueOld.shiftWorkStartMinPM = resp.content.shiftWorkOptionTimeStartPM.slice(-2)
        this.valueOld.shiftWorkEndHourPM = resp.content.shiftWorkOptionTimeEndPM.split(":", 1)
        this.valueOld.shiftWorkEndMinPM = resp.content.shiftWorkOptionTimeEndPM.slice(-2)
    });

    }

  }
  CheckValue(valueOld: any, valueNew: any): boolean {
    return _.isEqual(valueOld, valueNew);
  }
  back(){
    if(this.CheckValue(this.valueOld,this.formData.value)===true){
      this.router.navigate(['/vacation-shift-work'])
    }
    else{
      this.openPopup();
    }

  }
  openPopup() {
    const modalRef = this.modalService.open(ModalConfirmComponent, {centered: true});
    modalRef.componentInstance.isDelete = false;
    modalRef.componentInstance.isBack = true;
    this.translate.get('confirm-message.update').subscribe(
      (text: string) => {
        modalRef.componentInstance.title = text;
      }
    );
    modalRef.result.then(result => {
      if (result === 'save tab') {
        this.submitForm();
      } else {
        if (result === 'Unsave') {
          this.router.navigate(['/vacation-shift-work']);
        }
      }
    });
  }
  setHour(){
    for(let i = 1;i <= 24; i++){
      this.hourList.push(i);
    }
  }
  setMin(){
    this.ms021002Service.initStepBreakTime('').subscribe(resp =>{
      for(let i = 0;i < 60 ; i += resp.content[0].stepBreakTime){
        if(i == 0){
          this.minList.push("0"+i)
        }else {
          this.minList.push(i);
        }
      }
    })
  }
  getTimeWorkSystem(){
    this.ms021002Service.getTimeWorkSystem('').subscribe(resp => {
      this.timWorkDefault.timeWorkHourAM = parseInt(resp.content[0].timeEndWorkAM.split(":", 1)) - parseInt(resp.content[0].timeStartWorkAM.split(":", 1))
      this.timWorkDefault.timeWorkMinAM =  parseInt(resp.content[0].timeEndWorkAM.slice(-2)) - parseInt(resp.content[0].timeStartWorkAM.slice(-2))
        if(this.timWorkDefault.timeWorkMinAM < 0 ){
          this.timWorkDefault.timeWorkHourAM --;
          this.timWorkDefault.timeWorkMinAM = Math.abs(this.timWorkDefault.timeWorkMinAM);
        }
      this.timWorkDefault.timeWorkHourPM = parseInt(resp.content[0].timeEndWorkPM.split(":", 1)) - parseInt(resp.content[0].timeStartWorkPM.split(":", 1))
      this.timWorkDefault.timeWorkMinPM = parseInt(resp.content[0].timeEndWorkPM.slice(-2)) - parseInt(resp.content[0].timeStartWorkPM.slice(-2))
        if(this.timWorkDefault.timeWorkMinPM < 0 ){
          this.timWorkDefault.timeWorkHourPM --;
          this.timWorkDefault.timeWorkMinPM = Math.abs(this.timWorkDefault.timeWorkMinPM);
        }
      this.HourWorkSystem =  this.timWorkDefault.timeWorkHourAM + this.timWorkDefault.timeWorkHourPM;
      this.MinWorkSystem = this.timWorkDefault.timeWorkMinAM + this.timWorkDefault.timeWorkMinPM;
        if(this.MinWorkSystem === 60){
          this.HourWorkSystem ++;
          this.MinWorkSystem = 0;
        }
    })
  }
  checkHourAM(hourStartAM : any,hourEndAM : any):boolean{
    if(parseInt(hourStartAM) < parseInt(hourEndAM)){
      return true;
      }else {
      return false;
    }
  }
  checkHourPM(hourStartPM : any, hourEndPM : any ):boolean{
    if(parseInt(hourEndPM) > parseInt(hourStartPM)){
      return true;
      }else {
      return false;
    }
  }

  checkTimeInput(hourStartAM : any, minuteStartAM:any,
            hourEndAM : any, minuteEndAM:any,
            hourStartPM : any, minuteStartPM:any,
            hourEndPM : any, minuteEndPM:any, fullHourSystem : any, fullMinSystem : any ): boolean{
    let hourAM;
    let minuteAM;
    let hourPM;
    let mimutePM;

   if(this.checkHourAM(hourStartAM,hourEndAM)&& this.checkHourPM(hourStartPM,hourEndPM)){
     hourAM = parseInt(hourEndAM) - parseInt(hourStartAM)
     minuteAM = parseInt(minuteEndAM) - parseInt(minuteStartAM)
       if( minuteAM < 0){
         hourAM --;
         minuteAM = Math.abs(parseInt(minuteEndAM) - parseInt(minuteStartAM));
      }
    hourPM = parseInt(hourEndPM) - parseInt(hourStartPM)
    mimutePM =  parseInt(minuteEndPM) - parseInt(minuteStartPM)
       if(mimutePM < 0){
         hourPM--;
         mimutePM = Math.abs(parseInt(minuteEndPM) - parseInt(minuteStartPM));
       }
   }
   let hourFull = hourAM + hourPM;
   let minuteFull = mimutePM + minuteAM;
    if(minuteFull === 60){
     hourFull ++;
     minuteFull = 0;
    }
   if(hourFull === fullHourSystem && minuteFull === fullMinSystem){
    return true;

   }else{
    return false;

   }
  }
  ngOnInit(): void {
    this.setHour();
    this.setMin();
    this.initData();


  }

}
