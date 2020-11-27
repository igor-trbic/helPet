import { Component, OnInit } from '@angular/core';
// import { FormControl } from '@angular/forms';
import { Sample } from '../models/user.model';
import { SampleService } from '../sample.service';

@Component({
  selector: 'app-register',
  templateUrl: './sample.component.html',
  styleUrls: ['./sample.component.css'],
  providers: [SampleService]
})
export class SampleComponent implements OnInit {

  constructor(private registerService: SampleService) { }
  sample = new Sample();

  register(value: boolean) {
    this.sample.returnMe = value;
    this.registerService.register(this.sample)
    .subscribe(resp => {
      console.log(resp);
      
    });
  }
  
  ngOnInit(): void {
  }


}
