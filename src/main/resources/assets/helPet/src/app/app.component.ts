import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  
  constructor(private router: Router) { }

  title = 'helPet';
  user = null;
  business = null;
  localSStorage = localStorage;

  logout() {
    // TODO: send logout to server as well eventually

    localStorage.removeItem('token');
    localStorage.removeItem('username');
    localStorage.removeItem('businessId');
    localStorage.removeItem('businessName');

    this.router.navigate(['/login']);
  }

  ngOnInit() {
    this.user = localStorage.getItem('username');
    this.business = localStorage.getItem('businessName');
  }
}
