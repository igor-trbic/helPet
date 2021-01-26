import { Component, OnInit } from '@angular/core';
import { PetsService } from '../pets.service';

@Component({
  selector: 'app-pets',
  templateUrl: './pets.component.html',
  styleUrls: ['./pets.component.css']
})
export class PetsComponent implements OnInit {

  constructor(private petsService: PetsService) { }

  getPets() {
    this.petsService.getPets().subscribe(resp => {
      console.log('RESPO:', resp);
      
    });
  }
  ngOnInit(): void {
    
  }

}
