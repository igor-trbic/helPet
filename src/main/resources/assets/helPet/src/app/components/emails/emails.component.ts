import { Component, OnInit, ViewChild } from '@angular/core';
import { EmailsService } from 'src/app/emails.service';
import { Email } from 'src/app/models/email.model';
import { ModalComponent } from '../modal/modal.component';
import { ModalConfig } from '../modal/modal.config';

@Component({
  selector: 'emails',
  templateUrl: './emails.component.html',
  styleUrls: ['./emails.component.css']
})
export class EmailsComponent implements OnInit {

  @ViewChild('modal') private modalComponent: ModalComponent

  constructor(private emailService: EmailsService) { }

  emails: Email[];
  email = new Email();
  date: {year: number, month: number};
  modalConfig: ModalConfig = {
    modalTitle: 'Create new email'
  };

  createEmail() {
    this.email = new Email();
    return this.modalComponent.open().then((result) => {
      if (result === 'Save') {
        this.emailService.createEmail(this.email).subscribe(resp => {
          console.log(resp);
          this.getAllEmails();
        })
      }
    }, (reason) => {
      console.log("Error happened: ", reason);
    });
  }

  updateEmail(email: Email) {
    this.email = email;
    return this.modalComponent.open().then((result) => {
      if (result === 'Save') {
        this.emailService.updateEmail(this.email).subscribe(resp => {
          console.log(resp);
          this.getAllEmails();
        })
      }
    }, (reason) => {
      console.log("Error happened: ", reason);
    });
  }

  removeEmail(emailId: number) {
    this.emailService.removeEmail(emailId).subscribe(resp => {
      console.log(resp);
      this.getAllEmails();
    })
  }

  getAllEmails() {
    this.emailService.getEmails().subscribe(resp => {
      this.emails = resp;
    });
  }

  ngOnInit(): void {
    this.getAllEmails();
  }

}
