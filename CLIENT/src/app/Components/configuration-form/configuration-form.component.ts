import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-configuration-form',
  standalone: false,

  templateUrl: './configuration-form.component.html',
  styleUrl: './configuration-form.component.css'
})
export class ConfigurationFormComponent {

  // form: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient) { }

  formData = {
    totalTickets: ['0', Validators.required],
    ticketReleaseRate: ['0', Validators.required],
    customerRetrievalRate: ['0', Validators.required],
    maximumTickets: ['0', Validators.required]
  }

  submitForm() {
    console.log('Sending form data:', this.formData);

    //POST request to server endpoint
    this.http.post('http://localhost:8181/api/config', this.formData).subscribe({
      next: (response) => { console.log('Server response:', response); },
      error: (error) => { console.error('Error in submitting the form:', error); }
    });

    // if (this.formData.valid) {

    //   console.log('Sending form data:', formData);

    //   //POST request to server endpoint
    //   this.http.post('http://localhost:8080/api/config', formData).subscribe({
    //     next: (response) => { console.log('Server response:', response); },
    //     error: (error) => { console.error('Error in submitting the form:', error); }
    //   });
    // } else {
    //   console.log("Form is invalid!");
    //   console.log(this.form.value)
    // }
  }





}
