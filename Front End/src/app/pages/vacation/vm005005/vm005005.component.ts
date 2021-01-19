import { OnInit } from '@angular/core';
import * as type from '../../../common/constant/type';
import { VM005005Service } from './vm005005.service';
import { mergeMap, takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { StorageService } from '../../../services/storage/storage.service';
import { TranslateService } from '@ngx-translate/core';

import { Component, ElementRef, ViewChild, ViewEncapsulation } from '@angular/core';
import { Calendar } from '@fullcalendar/core'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import listPlugin from '@fullcalendar/list';
import bootstrapPlugin from '@fullcalendar/bootstrap';
import { Title } from '@angular/platform-browser';
import { TranslationService } from '../../../services/translate/translation.service';

@Component({
  selector: 'app-vm005005',
  templateUrl: './vm005005.component.html',
  styleUrls: ['./vm005005.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class VM005005Component implements OnInit {
  violationData: any[] = [];
  changedDate: Date;
  timeKeepingDetailSearchList: any[];
  isDataAvailable: boolean = false;
  oldYear: any;

  @ViewChild('calendar', { static: true }) calendar: ElementRef<any>;
  //name = 'Angular ' + VERSION.major;  

  constructor(
    private vm005005Service: VM005005Service,
    private localStorage: StorageService,
    private translate: TranslateService,
    private titleService: Title,
    public translationService: TranslationService,

  ) {
    //this.oldYear = new Date().getFullYear();
    //const storage: any = this.localStorage.getUser();
    //this.initDataSearch(this.oldYear, storage.employeeID);
    //this.violationData.map(i => new AxiomSchedulerEvent(i.title, i.from && new Date(i.from), i.to && new Date(i.to), i, i.color, i.locked));  
    const storage: any = this.localStorage.getUser();
    this.oldYear = new Date().getFullYear();
    this.initDataSearch(this.oldYear, storage.employeeID);
  }
  private unsubscribe$ = new Subject();

  ngOnInit(): void {
    /*const storage: any = this.localStorage.getUser();
    this.oldYear = new Date().getFullYear();
    this.initDataSearch(this.oldYear, storage.employeeID);*/
    // change title
    this.translate.get('title.vacation.tracking-timekeeping').subscribe((title: string) => {
      this.titleService.setTitle(title);
    });
    // change title when change language
    this.translationService.changeTranslatetion().pipe(takeUntil(this.unsubscribe$),
      mergeMap(lang => this.translate.get('title.vacation.tracking-timekeeping'))
    ).subscribe((title: string) => {
      this.titleService.setTitle(title);
    });

  }

  renderCalendar(isNull: boolean) {
    var calendarEl = this.calendar.nativeElement
    var calendar = new Calendar(calendarEl,
      {
        initialView: 'dayGridMonth',
        initialDate: new Date(),
        plugins: [
          dayGridPlugin,
          timeGridPlugin,
          listPlugin,
          bootstrapPlugin
        ],
        height: 800,
        dayMaxEventRows: 3,
        headerToolbar: {
          left: 'prev,next today',
          center: 'title',
          right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'          
        },
        selectable: false,
        //themeSystem: 'bootstrap', 
        datesSet: () => {
          if (!isNull) {
            var date = calendar.getDate();
            this.changedDate = date;
            const storage: any = this.localStorage.getUser();
            if (this.oldYear != this.changedDate.getFullYear()) {
              this.oldYear = this.changedDate.getFullYear();
              this.initDataSearch(this.oldYear, storage.employeeID);
            }
          }
        },
        events: this.violationData,
        eventTimeFormat: {
          hour: "numeric",
          minute: "2-digit",
          meridiem: "short",
        },
      });
    calendar.render();
  }

  initDataSearch(year: Number, employeeID: Number) {
    let isNull = false;
    this.vm005005Service.vm005005searchTrackingTimeKeeping(year, employeeID).pipe(takeUntil(this.unsubscribe$)).subscribe(datacheck => {
      if (datacheck.error === null) {
        this.isDataAvailable = true;
        this.timeKeepingDetailSearchList = datacheck.content.data;
        if (this.timeKeepingDetailSearchList.length > 0) {
          for (let item of this.timeKeepingDetailSearchList) {
            let Key = "vacation.violation-error-code." + item.violation;
            let title = this.translateVioloationCode(Key);
            let to = item.date;
            let from = item.date;
            let ViolationTracking = new type.ViolationTracking(title, to, from);
            this.violationData.push(ViolationTracking);
          }
          isNull = true;
        }
        this.renderCalendar(isNull);
      }
    }, err => {
      const httpErr = err.error;
      this.timeKeepingDetailSearchList = [];
      if (!httpErr) {
        return;
      }
    });
    this.isDataAvailable = false;
  }

  translateVioloationCode(key: any) {
    let setText = null;
    this.translate.get(key).subscribe((text: string) => {
      setText = text;
    });
    return setText;
  }
}
