import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastService } from 'src/app/theme/shared/components/toast-container/toast-service';
import { updateRequest } from './ms021002.i.';
import { MS021002Service } from './ms021002.service';


@Component({
  selector: 'app-ms021002',
  templateUrl: './ms021002.component.html',
})
export class MS021002Component implements OnInit {
  isUpdate = false;
  dataa = "test";
  req : any;
  formData: FormGroup;
  hourListAM : any[] = [];
  hourListPM : any[] = [];
  minList : any[]=[];
  updRequest : updateRequest;
  toastService: ToastService;
  constructor(private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private ms021002Service : MS021002Service,) {
    if(this.route.snapshot.params.id){
      this.isUpdate = true;
    }

    this.formData = this.fb.group({
      shiftWorkID: [null],
      shiftWorkName: [null],
      shiftWorkDescriptions: [null],
      shiftWorkCode: [null],
      shiftWorkStartHourAM : [null],
      shiftWorkStartMinAM :[null],
      shiftWorkStartHourPM : [null],
      shiftWorkStartMinPM :[null],
      shiftWorkEndHourAM : [null],
      shiftWorkEndHourPM : [null],
      shiftWorkEndMinAM : [null],
      shiftWorkEndMinPM : [null],
    });

  }

  submitForm(){
    console.log(this.formData.dirty);

    this.updRequest = {
      shiftWorkOptionID : this.formData.value.shiftWorkID,
      shiftWorkOptionCode : this.formData.value.shiftWorkCode,
      shiftWorkOptionName : this.formData.value.shiftWorkName,
      shiftWorkOptionDescription : this.formData.value.shiftWorkDescriptions,
      shiftWorkOptionStartTimeAM : this.formData.value.shiftWorkStartHourAM + ":" + this.formData.value.shiftWorkStartMinAM,
      shiftWorkOptionStartTimePM : this.formData.value.shiftWorkStartHourPM + ":" + this.formData.value.shiftWorkStartMinPM,
      shiftWorkOptionEndTimeAM : this.formData.value.shiftWorkEndHourAM + ":" + this.formData.value.shiftWorkEndMinAM,
      shiftWorkOptionEndTimePM : this.formData.value.shiftWorkEndHourPM + ":" + this.formData.value.shiftWorkEndMinPM
    };
    if(this.isUpdate === true  && this.formData.dirty === true){
      this.ms021002Service.updateShiftwork(this.updRequest).subscribe(resp =>{
        if(resp.content > 0 ){
          this.router.navigate(['/vacation-shift-work']);

        }

      })
    }else{
      this.router.navigate(['/vacation-shift-work']);
    }

  }
  initData(){
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

    });
    }

  }
  back(e){
   console.log(e);

  }
  setHour(){
    for(let i = 1;i <= 12; i++){
      this.hourListAM.push(i);
    }

     for(let i = 13;i <= 24; i++){
       this.hourListPM.push(i);
     }
  }
  setMin(){
    for(let i = 0;i<= 55; i +=5){
      if(i < 10){
        this.minList.push("0"+i)
      }else {
        this.minList.push(i);
      }

    }
  }
  ngOnInit(): void {
    this.setHour();
    this.setMin();
    this.initData();


  }

}
