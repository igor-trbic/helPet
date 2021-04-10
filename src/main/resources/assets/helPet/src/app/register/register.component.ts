import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BusinessRegister } from '../models/businesRegister.model';
import { Business } from '../models/business.model';
import { UserDTO } from '../models/user.dto.model';
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

  constructor(private registerService: RegisterService,
              private route: ActivatedRoute) { }
  user = new UserDTO();
  shownForm = 'user';
  businessUser = new BusinessRegister();
  

  registerUser(user) {
    this.registerService.register(user, this.route.snapshot.queryParamMap.get('token'))
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
    console.log(this.route.snapshot.queryParamMap);
    let firstName = this.route.snapshot.queryParamMap.get('firstName');
    if (firstName) {
      this.user.firstName = firstName;
    }
    let lastName = this.route.snapshot.queryParamMap.get('lastName');
    if (lastName) {
      this.user.lastName = lastName;
    }
    let businessId = this.route.snapshot.queryParamMap.get('businessId');
    if (businessId) {
      this.user.businessId = +businessId;
    }
    let email = this.route.snapshot.queryParamMap.get('email');
    if (email) {
      this.user.username = email;
    }
    
  }


}
