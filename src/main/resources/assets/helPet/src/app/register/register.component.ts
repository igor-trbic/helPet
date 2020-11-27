import { Component, OnInit } from '@angular/core';
// import { FormControl } from '@angular/forms';
import { User } from '../models/user.model';
import { RegisterService } from '../register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers: [RegisterService]
})
export class RegisterComponent implements OnInit {

  constructor(private registerService: RegisterService) { }
  user = new User();

  register(form) {
    this.registerService.register(form)
    .subscribe(resp => {
      console.log(resp);
      
    });
    console.log('FORM: ', form);
    
  }
  
  ngOnInit(): void {
  }


}
