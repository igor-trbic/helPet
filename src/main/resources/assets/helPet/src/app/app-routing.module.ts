import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppointmantsComponent } from './appointmants/appointmants.component';
import { DiagnosesComponent } from './diagnoses/diagnoses.component';
import { LoginComponent } from './login/login.component';
import { MedicationsComponent } from './medications/medications.component';
import { PetComponent } from './pet/pet.component';
import { PetsComponent } from './pets/pets.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { StaffComponent } from './staff/staff.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'pets', component: PetsComponent },
  { path: 'pet/:id', component: PetComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'appointmants', component: AppointmantsComponent },
  { path: 'staff', component: StaffComponent },
  { path: 'medications', component: MedicationsComponent },
  { path: 'diagnoses', component: DiagnosesComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
