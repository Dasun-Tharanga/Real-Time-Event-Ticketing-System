import { Component, OnDestroy, OnInit, Signal } from '@angular/core';
import { WebsocketService } from '../../Services/websocket/websocket.service';

@Component({
  selector: 'app-ticket-display',
  standalone: false,

  templateUrl: './ticket-display.component.html',
  styleUrl: './ticket-display.component.css'
})
export class TicketDisplayComponent implements OnInit, OnDestroy {

  ticketCount: Signal<number>;


  constructor(private websocketService: WebsocketService) {
    this.ticketCount = this.websocketService.getTicketCount();
  }

  ngOnInit(): void {
    const wsUrl = 'ws://localhost:8181/ws/tickets';
    this.websocketService.connect(wsUrl);
  }

  ngOnDestroy(): void {
    this.websocketService.close();
  }
}
