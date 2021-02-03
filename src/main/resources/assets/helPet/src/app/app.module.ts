import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { PetsComponent } from './pets/pets.component';
import { AuthInterceptor } from './http-interceptors/auth-interceptor';
import { NgbDateParserFormatter, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ModalComponent } from './components/modal/modal.component';
import { NgbDateCustomParserFormatter } from './utils/datepicker.formatter';
import { ProfileComponent } from './profile/profile.component';
import { AppointmantsComponent } from './appointmants/appointmants.component';
import { PhonesComponent } from './components/phones/phones.component';
import { EmailsComponent } from './components/emails/emails.component';
import { AddressesComponent } from './components/addresses/addresses.component';

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
    AddressesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    {provide: NgbDateParserFormatter, useClass: NgbDateCustomParserFormatter},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
