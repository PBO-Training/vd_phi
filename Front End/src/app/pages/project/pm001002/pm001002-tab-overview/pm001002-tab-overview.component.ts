import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { ApexAxisChartSeries, ApexChart, ApexDataLabels, ApexGrid, ApexStroke, ApexXAxis, ChartComponent } from 'ng-apexcharts';
import { DropDownDataProjectInfo } from '../pm001002-entity';
export interface ChartOptions {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  xaxis: ApexXAxis;
  dataLabels: ApexDataLabels;
  grid: ApexGrid;
  stroke: ApexStroke;
}
@Component({
  selector: 'app-pm001002-tab-overview',
  templateUrl: './pm001002-tab-overview.component.html',
  styleUrls: ['./pm001002-tab-overview.component.scss']
})
export class Pm001002TabOverviewComponent implements OnInit, OnChanges {
  @Input() projectResponse: any;
  @Input() dropdownData: DropDownDataProjectInfo;
  @Input() listPosition: any;
  @Output() changeTab = new EventEmitter<number>();
  @Output() detailMember = new EventEmitter<any>();
  @ViewChild('chart') chart: ChartComponent;
  public chartOptions: Partial<ChartOptions>;
  issueOpen: number;
  issueClosed: number;
  issueTotal: number;
  showPosition: any = [];
  percent: number;
  isRenderCharts = false;
  timeline: any = [0];
  totalMember: any = [0];

  constructor() {
  }
  ngOnChanges(changes: SimpleChanges): void {
    this.checkStatusIssue();
    this.checkRoleMember();
    this.percent = this.projectResponse?.issuePercentDoneAverage;
    // this.timeline = [0];
    // this.totalMember = [0];
    // this.projectResponse?.listFollowMonth?.forEach(element => {
    //   this.timeline.push(element?.timeline);
    //   this.totalMember.push(element?.totalMember);
    // });
    // this.listMemberFollowMonth();
  }
  listMemberFollowMonth() {
    this.chartOptions = {
      series: [
        {
          name: 'Members',
          data: this.totalMember
        }
      ],
      chart: {
        height: 350,
        type: 'line',
        zoom: {
          enabled: false
        },
        toolbar: {
          show: false
        }
      },
      dataLabels: {
        enabled: false
      },
      stroke: {
        show: true,
        curve: 'straight'
      },
      grid: {
        row: {
          colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
          opacity: 0.5
        }
      },
      xaxis: {
        categories: this.timeline
      }
    };
  }

  ngOnInit(): void {
  }

  navigateOrtherTab(activeID: number): void {
    this.changeTab.emit(activeID);
  }

  /**
   * Issue
   */
  checkStatusIssue(): void {
    const issue = this.projectResponse?.listIssue;
    const issueOpen = issue?.filter(item => item.statusIssueCode !== '60CLOSED');
    const issueClosed = issue?.filter(item => item.statusIssueCode === '60CLOSED');
    const issueTotal = issue?.filter(item => item.objectTracker.trackerCode === 'TARGET');
    this.issueOpen = issueOpen?.length;
    this.issueClosed = issueClosed?.length;
    this.issueTotal = issueTotal?.length;
    }

  /**
   * Member
   */
  checkRoleMember(): void {
    this.showPosition = [];
    this.listPosition?.forEach(element => {
      this.showPosition.push({
        positionProjectID: element.positionProjectID,
        positionProjectCode: element.positionProjectCode,
        positionProjectName: element.positionProjectName,
        listMember: []
      });
    });
    this.showPosition?.forEach(
      item => {
        this.projectResponse?.listEmployee?.forEach(element => {
          if (Number(element.positionProjectID) === item.positionProjectID) {
            item.listMember.push(element);
          }
        });
      }
    );
  }

  navigateMemberDetail(employeeID: number) : void {
    this.detailMember.emit(employeeID);
  }
}
