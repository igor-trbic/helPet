import { Component, OnInit, ViewChild } from '@angular/core';
import { Phone } from 'src/app/models/phone.model';
import { PhonesService } from 'src/app/phones.service';
import { ModalComponent } from '../modal/modal.component';
import { ModalConfig } from '../modal/modal.config';

@Component({
  selector: 'phones',
  templateUrl: './phones.component.html',
  styleUrls: ['./phones.component.css']
})
export class PhonesComponent implements OnInit {

  @ViewChild('modal') private modalComponent: ModalComponent

  constructor(private phoneService: PhonesService) { }

  phones: Phone[];
  phone = new Phone();
  date: {year: number, month: number};
  modalConfig: ModalConfig = {
    modalTitle: 'Create new phone'
  };
  businessId: number = null;

  createPhone() {
    this.phone = new Phone();
    return this.modalComponent.open().then((result) => {
      if (result === 'Save') {
        if (this.businessId) {
          this.phoneService.createBusinessPhone(this.businessId, this.phone).subscribe(resp => {
            console.log(resp);
            this.getAllPhones();
          });
        } else {
          this.phoneService.createPhone(this.phone).subscribe(resp => {
            console.log(resp);
            this.getAllPhones();
          });
        }
      }
    }, (reason) => {
      console.log("Error happened: ", reason);
    });
  }

  updatePhone(phone: Phone) {
    this.phone = phone;
    return this.modalComponent.open().then((result) => {
      if (result === 'Save') {
        this.phoneService.updatePhone(this.phone).subscribe(resp => {
          console.log(resp);
          this.getAllPhones();
        });
      }
    }, (reason) => {
      console.log("Error happened: ", reason);
    });
  }

  removePhone(phoneId: number) {
    this.phoneService.removePhone(phoneId).subscribe(resp => {
      console.log(resp);
      this.getAllPhones();
    });
  }

  getAllPhones() {
    if (this.businessId) {
      this.phoneService.getBusinessPhones(this.businessId).subscribe(resp => {
        this.phones = resp;
      });
    } else {
      this.phoneService.getPhones().subscribe(resp => {
        this.phones = resp;
      });
    }
  }

  ngOnInit(): void {
    this.businessId = Number.parseInt(localStorage.getItem("businessId"));
    console.log(this.businessId);
    this.getAllPhones();
  }

}
