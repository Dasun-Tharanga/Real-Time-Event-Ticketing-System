import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignInComponent } from './sign-in/sign-in.component';
import { CustomerComponent } from './Components/customer/customer.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { VendorComponent } from './Components/vendor/vendor.component';
import { AdminSingInComponent } from './Components/admin-sing-in/admin-sing-in.component';

const routes: Routes = [
  {
    path: '',
    component: SignInComponent,
    title: 'Sing In'
  },
  {
    path: 'signUp',
    component: SignUpComponent,
    title: 'Sign Up'
  },
  {
    path: 'adminSignIn',
    component: AdminSingInComponent,
    title: 'Admin Sign In'
  },
  {
    path: 'customer',
    component: CustomerComponent,
    title: 'Customer'
  },
  {
    path: 'vendor',
    component: VendorComponent,
    title: 'Vendor'
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
