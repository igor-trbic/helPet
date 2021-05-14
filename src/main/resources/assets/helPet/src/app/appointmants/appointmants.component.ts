import { Component, OnInit, ViewChild } from '@angular/core';
import { NgbDateStruct, NgbTimeStruct } from '@ng-bootstrap/ng-bootstrap';
import { AppointmentService } from '../appointment.service';
import { BusinessService } from '../business.service';
import { ModalComponent } from '../components/modal/modal.component';
import { ModalConfig } from '../components/modal/modal.config';
import { Appointment } from '../models/appointment.model';
import { Business } from '../models/business.model';
import { Pet } from '../models/pet.model';
import { PetsService } from '../pets.service';
import DateUtils from '../utils/date.util';

@Component({
  selector: 'app-appointmants',
  templateUrl: './appointmants.component.html',
  styleUrls: ['./appointmants.component.css']
})
export class AppointmantsComponent implements OnInit {

  @ViewChild('modal') private modalComponent: ModalComponent

  constructor(private appointmentService: AppointmentService,
              private businessService: BusinessService,
              private petsService: PetsService) { }

  appointments: Appointment[];
  appointment = new Appointment();
  pets: Pet[];
  businesses: Business[];
  modalConfig: ModalConfig = {
    modalTitle: 'Create new appointment'
  };
  time: NgbTimeStruct = {hour: 13, minute: 30, second: 0};
  date: NgbDateStruct;
  hourStep = 1;
  minuteStep = 15;
  businessId: number = null;

  add() {
    console.log(this.appointment);
    this.appointment.date = DateUtils.convertToISODate(this.date, this.time);
    this.appointmentService.create(this.appointment).subscribe(resp => {
      console.log(resp);
      this.getAll();
    })
  }

  reject(appointment: Appointment) {
    appointment.status = 'REJECTED';
    this.update(appointment);
  }

  approve(appointment: Appointment) {
    appointment.status = 'APPROVED';
    this.update(appointment);
  }

  update(appointment: Appointment) {
    this.appointmentService.update(appointment).subscribe(resp => {
      console.log(resp);
      this.getAll();
    });
  }

  remove(petId) {
    this.appointmentService.remove(petId).subscribe(resp => {
      console.log(resp);
      this.getAll();
    })
  }

  create() {
    this.appointment = new Appointment();
    return this.modalComponent.open().then((result) => {
      if (result === 'Save') {
        this.add();
      }
    }, (reason) => {
      console.log("Error happened: ", reason);
    });
  }

  getAll() {
    if (this.businessId) {
      this.appointmentService.getForBusiness(this.businessId).subscribe(resp => {
        this.appointments = resp;
      });
    } else {
      this.appointmentService.get().subscribe(resp => {
        this.appointments = resp;
      });
    }
    this.petsService.getPets().subscribe(resp => {
        this.pets = resp;
    });
    this.businessService.get().subscribe(resp => {
        this.businesses = resp;
    });
  }

  ngOnInit(): void {
    this.businessId = Number.parseInt(localStorage.getItem("businessId"));
    this.getAll();
  }

}
