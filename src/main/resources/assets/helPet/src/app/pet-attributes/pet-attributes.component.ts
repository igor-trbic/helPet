import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ModalComponent } from '../components/modal/modal.component';
import { ModalConfig } from '../components/modal/modal.config';
import { PetAttribute } from '../models/petAttribute.model';
import { PetAttributeService } from '../pet-attribute.service';

@Component({
  selector: 'pet-attributes',
  templateUrl: './pet-attributes.component.html',
  styleUrls: ['./pet-attributes.component.css']
})
export class PetAttributesComponent implements OnInit {

  @ViewChild('modal') private modalComponent: ModalComponent

  constructor(private petAttributeService: PetAttributeService,
              private route: ActivatedRoute) { }

  attributes: PetAttribute[];
  attribute = new PetAttribute();
  modalConfig: ModalConfig = {
    modalTitle: 'Create new attribute'
  };
  petId;

  createAttribute() {
    this.attribute = new PetAttribute();
    return this.modalComponent.open().then((result) => {
      if (result === 'Save') {
        this.petAttributeService.createPetAttribute(this.petId, this.attribute).subscribe(resp => {
          console.log(resp);
          this.getAllAttributes();
        })
      }
    }, (reason) => {
      console.log("Error happened: ", reason);
    });
  }

  updateAttribute(attr: PetAttribute) {
    this.attribute = attr;
    return this.modalComponent.open().then((result) => {
      if (result === 'Save') {
        this.petAttributeService.updatePetAttribute(this.petId, this.attribute).subscribe(resp => {
          console.log(resp);
          this.getAllAttributes();
        })
      }
    }, (reason) => {
      console.log("Error happened: ", reason);
    });
  }

  removeAttribute(attributeId: number) {
    this.petAttributeService.removePetAttribute(this.petId, attributeId).subscribe(resp => {
      console.log(resp);
      this.getAllAttributes();
    })
  }

  getAllAttributes() {
    this.petAttributeService.getPetAttributes(this.petId).subscribe(resp => {
      this.attributes = resp;
    });
  }

  ngOnInit(): void {
    this.petId = this.route.snapshot.paramMap.get('id');
    this.getAllAttributes();
  }

}
