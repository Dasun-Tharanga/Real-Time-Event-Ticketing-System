import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignInComponent } from './sign-in/sign-in.component';
import { CustomerComponent } from './Components/customer/customer.component';
import { SignUpComponent } from './sign-up/sign-up.component';

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
    path: 'customer',
    component: CustomerComponent,
    title: 'Customer'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
