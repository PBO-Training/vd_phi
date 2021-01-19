import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import {
  ApexAxisChartSeries,
  ApexChart,
  ApexDataLabels,
  ApexGrid,
  ApexLegend,
  ApexNonAxisChartSeries,
  ApexResponsive,
  ApexStroke,
  ApexXAxis,
  ChartComponent
} from 'ng-apexcharts';
import { MonthInYear } from './ms999001-entity';
import { MS999001Service } from './ms999001.service';

export interface ChartEmployeeInCompany {
  series: ApexNonAxisChartSeries;
  chart: ApexChart;
  responsive: ApexResponsive[];
  labels: any;
  colors: any[];
  legend: ApexLegend;
}

export interface ChartEmployeeJoinInMonth {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  xaxis: ApexXAxis;
  dataLabels: ApexDataLabels;
  grid: ApexGrid;
  stroke: ApexStroke;
}

@Component({
  selector: 'app-ms999001',
  templateUrl: './ms999001.component.html',
  styleUrls: ['./ms999001.component.scss']
})
export class Ms999001Component implements OnInit, OnDestroy {

  @ViewChild('chart') chart: ChartComponent;
  public chartEmployeeInCompany: Partial<ChartEmployeeInCompany>;
  @ViewChild('chart2') chart2: ChartComponent;
  public chartEmployeeJoinInMonth: Partial<ChartEmployeeJoinInMonth>;

  showBreadcumb: boolean;
  isUpdate = false;
  employeeJoinInMonth: MonthInYear;
  employeeInCompany: number;
  employeeOfDepartment: any;
  isRenderCharts = false;

  constructor(
    private ms999001Service: MS999001Service,
  ) { }

  ngOnInit(): void {
    this.ms999001Service.initDashboard().subscribe(
      data => {
        this.employeeJoinInMonth = data.content.empJoinInMonth;
        this.employeeInCompany = data.content.totalEmployee;
        this.employeeOfDepartment = data.content.department;
        this.initEmployeeInCompany();
        this.initEmployeeJoinInMonth();
        this.isRenderCharts = true;
      },
      err => {
        console.log('Ms999001Component -> ngOnInit -> err', err);
      });
  }
  ngOnDestroy(): void {
  }

  initEmployeeJoinInMonth(): void {
    const currentDate = new Date();
    const data = [
      this.employeeJoinInMonth?.january,
      this.employeeJoinInMonth?.february,
      this.employeeJoinInMonth?.march,
      this.employeeJoinInMonth?.april,
      this.employeeJoinInMonth?.may,
      this.employeeJoinInMonth?.june,
      this.employeeJoinInMonth?.july,
      this.employeeJoinInMonth?.august,
      this.employeeJoinInMonth?.september,
      this.employeeJoinInMonth?.october,
      this.employeeJoinInMonth?.november,
      this.employeeJoinInMonth?.december,
    ];
    const currentMonth = currentDate.getMonth() + 1;
    data.splice(currentMonth);
    this.chartEmployeeJoinInMonth = {
      series: [
        {
          name: 'Employees',
          data
        }
      ],
      chart: {
        id: 'chart',
        type: 'line',
        height: 310,
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
          colors: ['#f3f3f3', 'transparent'],
          opacity: 0.5
        }
      },
      xaxis: {
        categories: [
          'Jan',
          'Feb',
          'Mar',
          'Apr',
          'May',
          'Jun',
          'Jul',
          'Aug',
          'Sep',
          'Oct',
          'Nov',
          'Dec'
        ]
      }
    };
  }

  initEmployeeInCompany(): void {
    const series = [];
    const labels = [];
    const defineColors = ['#2E93fA', '#66DA26', '#FEB019', '#EA3546',
      '#662E9B', '#2E294E', '#5A2A27', '#D7263D', '#5C4742', '#A300D6', '#546E7A'];
    const colors = [];
    this.employeeOfDepartment?.forEach((item, index) => {
      const lengthEmployee = item.employee.length;
      const itemNull = item.employee.filter(element => element.employeeCode === null);
      series.push(lengthEmployee - itemNull.length);
      labels.push(item.departmentName);
      colors.push(defineColors[index]);
    });
    const freeEmployee = this.employeeInCompany - series.reduce((accumulator, currentValue) => accumulator + currentValue);

    this.chartEmployeeInCompany = {
      series: [...series, freeEmployee],
      chart: {
        id: 'chart2',
        type: 'pie',
        width: '100%',
        height: '310px',
      },
      labels: [...labels, 'Free'],
      colors: [...colors, '#F9A3A4'],
      legend: {
        position: 'right',
        width: 130,
      },
      responsive: [
        {
          breakpoint: 1366,
          options: {
            chart: {
              width: '100%'
            },
            legend: {
              position: 'right',
            }
          }
        },
        {
          breakpoint: 450,
          options: {
            chart: {
              width: '100%'
            },
            legend: {
              position: 'right',
              width: 80
            }
          }
        },
      ]
    };
  }
}
