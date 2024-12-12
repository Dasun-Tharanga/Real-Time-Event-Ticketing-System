import { Component, Signal, signal } from '@angular/core';
import { TicketService } from '../../Services/ticket/ticket.service';

@Component({
  selector: 'app-customer',
  standalone: false,

  templateUrl: './customer.component.html',
  styleUrl: './customer.component.css'
})
export class CustomerComponent {
  //use signals here
  ticketCout: number = 0;
  customerId: string = '1234';


  constructor(private ticketService: TicketService) { }


  increment(): void {
    this.ticketCout++;
  }

  decrement(): void {
    if (this.ticketCout > 0) {
      this.ticketCout--;
    }
  }

  buyTickets(): void {

    if (this.ticketCout > 0) {
      this.ticketService.buyTickets(this.ticketCout, this.customerId)
        .subscribe({
          next: (response: string) => {
            console.log(response);
            this.ticketCout = 0
          },
          error: (error) => { alert('Failed to buy tickets. Please try again.'); console.log(error); }
        })
    } else {
      alert('Please select at least one ticket.')
    }

  }



}

