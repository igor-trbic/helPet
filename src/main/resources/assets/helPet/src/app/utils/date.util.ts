import { NgbDateStruct, NgbTimeStruct } from '@ng-bootstrap/ng-bootstrap';

export default class DateUtils {
  static convertToISODate(date: NgbDateStruct, time: NgbTimeStruct) {
    let month = date.month.toString();
    let day = date.day.toString();

    if (date.month.valueOf() < 10) {
      month = '0' + date.month;
    }

    if (date.day.valueOf() < 10) {
      day = '0' + date.day;
    }
    return new Date(date.year + '-' + month + '-' + day + 'T' + time.hour + ':' + time.minute + ':00');
  }
}
