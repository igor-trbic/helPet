import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Pet } from '../models/pet.model';
import { PetsService } from '../pets.service';

@Component({
  selector: 'app-pet',
  templateUrl: './pet.component.html',
  styleUrls: ['./pet.component.css']
})
export class PetComponent implements OnInit {

  constructor(private route: ActivatedRoute,
              private petsService: PetsService) { }
  
  pet: Pet;

  editPet() {
    
  }

  ngOnInit(): void {
    console.log(this.route.snapshot.paramMap.get('id'));
    this.petsService.getPet(this.route.snapshot.paramMap.get('id')).subscribe(resp => {
      console.log(resp);
      this.pet = resp;
    })
  }

}
