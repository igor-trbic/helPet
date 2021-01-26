import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { AuthReq } from '../models/authReq.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private loginService: LoginService,
              private router: Router) { }

  authReq = new AuthReq();
  login(form) {
    this.loginService.login(form)
    .subscribe(resp => {
      localStorage.setItem('token', resp.token);
      localStorage.setItem('username', resp.username);
      console.log('RESPO:', resp);
      
      this.router.navigate(['/pets']);
    });
    
  }

  ngOnInit(): void {
  }

}
