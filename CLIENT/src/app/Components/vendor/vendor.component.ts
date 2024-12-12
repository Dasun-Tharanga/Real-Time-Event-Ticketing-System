import { Component } from '@angular/core';
import { TicketService } from '../../Services/ticket/ticket.service';
import { LogService } from '../../Services/log/log.service';

@Component({
  selector: 'app-vendor',
  standalone: false,

  templateUrl: './vendor.component.html',
  styleUrl: './vendor.component.css'
})
export class VendorComponent {

  ticketCout: number = 0;
  vendorID: string = 'v1234';

  constructor(private ticketService: TicketService, private logService: LogService) { }

  increment(): void {
    this.ticketCout++;
  }

  decrement(): void {
    if (this.ticketCout > 0) {
      this.ticketCout--;
    }
  }

  addTickets(): void {

    if (this.ticketCout > 0) {
      this.ticketService.addTickets(this.ticketCout, this.vendorID)
        .subscribe({
          next: (response: any) => {
            if (response.status === 200) {
              console.log(response.body);
              this.ticketCout = 0;
              this.logService.addLog(`Vendor ${this.vendorID} added ${this.ticketCout} tickets to the TicketPool`);
            }
          },
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


  appLogs = [
    { timestamp: new Date().toISOString(), message: 'Application started' },
    { timestamp: new Date().toISOString(), message: 'User logged in' },
  ];


  // Method to add a log messaage
  addLog(message: string) {
    this.appLogs.push({ timestamp: new Date().toISOString(), message });
  }


}
