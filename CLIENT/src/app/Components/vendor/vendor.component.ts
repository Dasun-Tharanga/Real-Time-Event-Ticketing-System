import { Component } from '@angular/core';
import { TicketService } from '../../Services/ticket/ticket.service';

@Component({
  selector: 'app-vendor',
  standalone: false,

  templateUrl: './vendor.component.html',
  styleUrl: './vendor.component.css'
})
export class VendorComponent {

  ticketCout: number = 0;
  vendorID: string = 'v1234';

  constructor(private ticketService: TicketService) { }

  increment(): void {
    this.ticketCout++;
  }

  decrement(): void {
    if (this.ticketCout > 0) {
      this.ticketCout--;
    }
  }

  addTickets(): void {
    alert(`added ${this.ticketCout}`);

    if (this.ticketCout > 0) {
      this.ticketService.addTickets(this.ticketCout, this.vendorID)
        .subscribe({
          next: (response) => { alert(response); this.ticketCout = 0 },
          error: (error) => { alert('Failed to add tickets. Please try again.'); console.log(error); }
        })
    } else {
      alert('Please select at least one ticket.')
    }

  }

  stopReleasingTickets(): void {
    this.ticketService.stopAdding(this.vendorID)
      .subscribe({
        next: (response) => { alert(response) },
        error: (error) => { alert('Fialed to stop  releasing tickets.') }
      })
  }




}
