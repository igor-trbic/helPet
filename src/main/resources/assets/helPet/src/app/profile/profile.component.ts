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

  createEmail() {

  }

  createAddress() {

  }

  createPhone() {

  }
  
  ngOnInit(): void {
  }

}
