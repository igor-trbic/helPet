import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgSelectModule } from '@ng-select/ng-select';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { PetsComponent } from './pets/pets.component';
import { AuthInterceptor } from './http-interceptors/auth-interceptor';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ModalComponent } from './components/modal/modal.component';
import { ProfileComponent } from './profile/profile.component';
import { AppointmantsComponent } from './appointmants/appointmants.component';
import { PhonesComponent } from './components/phones/phones.component';
import { EmailsComponent } from './components/emails/emails.component';
import { AddressesComponent } from './components/addresses/addresses.component';
import { StaffComponent } from './staff/staff.component';
import { MedicationsComponent } from './medications/medications.component';
import { DiagnosesComponent } from './diagnoses/diagnoses.component';
import { PetComponent } from './pet/pet.component';
import { PetAttributesComponent } from './pet-attributes/pet-attributes.component';
import { BusinessRoleTypeComponent } from './business-role-type/business-role-type.component';
import { MomentModule } from 'ngx-moment';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    PetsComponent,
    ModalComponent,
    ProfileComponent,
    AppointmantsComponent,
    PhonesComponent,
    EmailsComponent,
    AddressesComponent,
    StaffComponent,
    MedicationsComponent,
    DiagnosesComponent,
    PetComponent,
    PetAttributesComponent,
    BusinessRoleTypeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    NgSelectModule,
    MomentModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
