import {Component, OnInit} from '@angular/core';
import {Report} from '../model/Report';
import {ReportProduct} from '../model/ReportProduct';
import {ShopService} from '../service/shop.service';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.scss']
})
export class ReportComponent implements OnInit {

  constructor(private shopService: ShopService) {
  }

  mostSold: Array<ReportProduct> = [];
  mostProfitable: Array<ReportProduct> = [];

  ngOnInit(): void {
    this.shopService.report().subscribe(
      response => {
        this.mostSold = this.sortByUnits(response);
        this.mostProfitable = this.sortByProfit(response);
      }
    );
  }

  sortByUnits(report: Report): Array<ReportProduct> {
    const newArray = new Array<ReportProduct>();
    newArray.push(...report.productResponse);
    return newArray.sort((a, b) => b.unitsSold - a.unitsSold);
  }

  sortByProfit(report: Report): Array<ReportProduct> {
    const newArray = new Array<ReportProduct>();
    newArray.push(...report.productResponse);
    return report.productResponse.sort((a, b) => b.profit - a.profit);
  }

}
