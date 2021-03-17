import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { UserDTO } from '../models/user.dto.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private loginService: LoginService,
              private router: Router) { }

  authReq = new UserDTO();
  login(form) {
    this.loginService.login(form)
    .subscribe(resp => {
      localStorage.setItem('token', resp.token);
      localStorage.setItem('username', resp.username);
      if (resp.businessId) {
        localStorage.setItem('businessId', resp.businessId.toString());
        localStorage.setItem('businessName', resp.businessName);
      }
      console.log('RESPO:', resp);
      this.router.navigate(['/pets']);
    });
    
  }

  ngOnInit(): void {
  }

}
