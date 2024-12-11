import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MaterialModule } from './Modules/material/material.module';
import { CustomerComponent } from './Components/customer/customer.component';
import { VendorComponent } from './Components/vendor/vendor.component';
import { ConfigurationFormComponent } from './Components/configuration-form/configuration-form.component';
import { TicketDisplayComponent } from './Components/ticket-display/ticket-display.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { provideHttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NavBarComponent } from './Components/nav-bar/nav-bar.component';
import { LogDisplayComponent } from './Components/log-display/log-display.component';
import { AdminSingInComponent } from './Components/admin-sing-in/admin-sing-in.component';



@NgModule({
  declarations: [
    AppComponent,
    CustomerComponent,
    VendorComponent,
    ConfigurationFormComponent,
    TicketDisplayComponent,
    SignInComponent,
    SignUpComponent,
    NavBarComponent,
    LogDisplayComponent,
    AdminSingInComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    FormsModule
  ],
  providers: [
    provideAnimationsAsync(),
    provideHttpClient()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
