import { Component, OnInit, ViewChild } from '@angular/core';
import { BusinessRoleTypeService } from '../business-role-type.service';
import { BusinessStaffService } from '../business-staff.service';
import { ModalComponent } from '../components/modal/modal.component';
import { ModalConfig } from '../components/modal/modal.config';
import { BusinessRoleType } from '../models/businessRoleType.model';
import { BusinessStaffDTO } from '../models/businessStaff.dto.model';

@Component({
  selector: 'app-staff',
  templateUrl: './staff.component.html',
  styleUrls: ['./staff.component.css']
})
export class StaffComponent implements OnInit {

  @ViewChild('modal') private modalComponent: ModalComponent

  constructor(private businessRoleTypeService: BusinessRoleTypeService,
              private businessStaffService: BusinessStaffService) { }

  businessId: number;
  businessRoleTypes: BusinessRoleType[];
  businessRoleType = new BusinessRoleType();
  selectedRoleType: number;
  businessStaffDTO = new BusinessStaffDTO();
  businessStaffs: BusinessStaffDTO[];
  modalConfig: ModalConfig = {
    modalTitle: 'Invite staff'
  };
  isUpdate: boolean = false;

  add() {
    this.businessStaffDTO.businessId = this.businessId;
    this.businessStaffService.invite(this.businessId, this.businessStaffDTO).subscribe(resp => {
      console.log(resp);
      this.getAllBusinessRoles();
    })
  }

  update(bsDTO: BusinessStaffDTO) {
    this.isUpdate = true;
    this.businessStaffDTO = bsDTO;
    this.modalConfig.modalTitle = "Update role";
    return this.modalComponent.open().then((result) => {
      if (result === 'Save') {
        this.businessStaffService.update(this.businessStaffDTO).subscribe(resp => {
          console.log(resp);
          this.getAllBusinessRoles();
        })
      }
    }, (reason) => {
      console.log("Error happened: ", reason);
    });
  }

  // remove(businessStaffId) {
  //   this.businessStaffService.remove(businessStaffId).subscribe(resp => {
  //     console.log(resp);
  //     this.getAllBusinessRoles();
  //   })
  // }

  create() {
    this.isUpdate = false;
    this.modalConfig.modalTitle = "Invite staff";
    this.businessStaffDTO = new BusinessStaffDTO();
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

  getAllBusinessStaffs() {
    this.businessStaffService.getAll(this.businessId).subscribe(resp => {
      this.businessStaffs = resp;
    });
  }

  ngOnInit(): void {
    this.businessId = Number.parseFloat(localStorage.getItem('businessId'));
    this.getAllBusinessRoles();
    this.getAllBusinessStaffs();
  }

}