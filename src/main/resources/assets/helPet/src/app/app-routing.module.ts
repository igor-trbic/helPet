import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppointmantsComponent } from './appointmants/appointmants.component';
import { LoginComponent } from './login/login.component';
import { PetsComponent } from './pets/pets.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'pets', component: PetsComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'appointmants', component: AppointmantsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
