import { Component, OnInit, ViewChild } from '@angular/core';
import { AddressesComponent } from '../components/addresses/addresses.component';
import { EmailsComponent } from '../components/emails/emails.component';
import { PhonesComponent } from '../components/phones/phones.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  @ViewChild('phones') private phonesComponent: PhonesComponent
  @ViewChild('addresses') private addressesComponent: AddressesComponent
  @ViewChild('emails') private emailsComponent: EmailsComponent

  constructor() { }

  businessId: number = null;
  
  ngOnInit(): void {
    let bId = localStorage.getItem("businessId");
    if (bId) {
      this.businessId = Number.parseInt(bId);
    }
    console.log(this.businessId);
    
  }

}
