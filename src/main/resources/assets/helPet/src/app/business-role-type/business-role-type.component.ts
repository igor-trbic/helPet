import { Component, OnInit, ViewChild } from '@angular/core';
import { BusinessRoleTypeService } from '../business-role-type.service';
import { ModalComponent } from '../components/modal/modal.component';
import { ModalConfig } from '../components/modal/modal.config';
import { BusinessRoleType } from '../models/businessRoleType.model';

@Component({
  selector: 'app-business-role-type',
  templateUrl: './business-role-type.component.html',
  styleUrls: ['./business-role-type.component.css']
})
export class BusinessRoleTypeComponent implements OnInit {

  @ViewChild('modal') private modalComponent: ModalComponent

  constructor(private businessRoleTypeService: BusinessRoleTypeService) { }

  businessId;
  businessRoleTypes: BusinessRoleType[];
  businessRoleType = new BusinessRoleType();
  modalConfig: ModalConfig = {
    modalTitle: 'Create new business role'
  };

  add() {
    console.log(this.businessRoleType);
    this.businessRoleTypeService.create(this.businessId, this.businessRoleType).subscribe(resp => {
      console.log(resp);
      this.getAllBusinessRoles();
    })
  }

  update(businessRoleType: BusinessRoleType) {
    this.businessRoleType = businessRoleType;
    return this.modalComponent.open().then((result) => {
      if (result === 'Save') {
        this.businessRoleTypeService.update(this.businessId, this.businessRoleType).subscribe(resp => {
          console.log(resp);
          this.getAllBusinessRoles();
        })
      }
    }, (reason) => {
      console.log("Error happened: ", reason);
    });
  }

  remove(petId) {
    this.businessRoleTypeService.remove(this.businessId, petId).subscribe(resp => {
      console.log(resp);
      this.getAllBusinessRoles();
    })
  }

  create() {
    this.businessRoleType = new BusinessRoleType();
    return this.modalComponent.open().then((result) => {
      if (result === 'Save') {
        this.add();
      }
    }, (reason) => {
      console.log("Error happened: ", reason);
    });
  }

  getAllBusinessRoles() {
    this.businessRoleTypeService.getBusinessRoles(this.businessId).subscribe(resp => {
      this.businessRoleTypes = resp;
    });
  }

  ngOnInit(): void {
    this.businessId = localStorage.getItem('businessId');
    this.getAllBusinessRoles();
  }
}
