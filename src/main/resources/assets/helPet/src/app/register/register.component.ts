import { Component, OnInit } from '@angular/core';
import { BusinessRegister } from '../models/businesRegister.model';
import { Business } from '../models/business.model';
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
  shownForm = 'user';
  businessUser = new BusinessRegister();
  

  registerUser(user) {
    this.registerService.register(user)
    .subscribe(resp => {
      console.log(resp);
      
    });
    console.log('user: ', user);
  }

  registerBusiness(business) {
    this.registerService.registerBusiness(business)
    .subscribe(resp => {
      console.log(resp);
      
    });
    console.log('business: ', business);
  }
  
  ngOnInit(): void {
    this.businessUser.user = new User();
    this.businessUser.business = new Business();
    console.log(this.businessUser);
    
  }


}
