import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalComponent } from '../components/modal/modal.component';
import { ModalConfig } from '../components/modal/modal.config';
import { Pet } from '../models/pet.model';
import { PetsService } from '../pets.service';

@Component({
  selector: 'app-pets',
  templateUrl: './pets.component.html',
  styleUrls: ['./pets.component.css']
})
export class PetsComponent implements OnInit {

  @ViewChild('modal') private modalComponent: ModalComponent

  constructor(private petsService: PetsService) { }

  pets: Pet[];
  pet = new Pet();
  date: {year: number, month: number};
  modalConfig: ModalConfig = {
    modalTitle: 'Create new pet'
  };

  addPet() {
    console.log(this.pet);
    this.petsService.createPet(this.pet).subscribe(resp => {
      console.log(resp);
      this.getAllPets();
    })
  }

  updatePet(pet: Pet) {
    this.pet = pet;
    return this.modalComponent.open().then((result) => {
      if (result === 'Save') {
        this.petsService.updatePet(this.pet).subscribe(resp => {
          console.log(resp);
          this.getAllPets();
        })
      }
    }, (reason) => {
      console.log("Error happened: ", reason);
    });
  }

  removePet(petId) {
    this.petsService.removePet(petId).subscribe(resp => {
      console.log(resp);
      this.getAllPets();
    })
  }

  createPet() {
    this.pet = new Pet();
    return this.modalComponent.open().then((result) => {
      if (result === 'Save') {
        this.addPet();
      }
    }, (reason) => {
      console.log("Error happened: ", reason);
    });
  }

  getAllPets() {
    this.petsService.getPets().subscribe(resp => {
      this.pets = resp;
    });
  }

  ngOnInit(): void {
    this.getAllPets();
  }

}
