import { Component, OnInit, OnDestroy, Signal } from '@angular/core';
import { WebsocketService } from '../../Services/websocket/websocket.service';

@Component({
  selector: 'app-log-display',
  standalone: false,

  templateUrl: './log-display.component.html',
  styleUrl: './log-display.component.css'
})
export class LogDisplayComponent implements OnDestroy {

  Logs: Array<string> = [];
  Log: Signal<string>;


  constructor(private webSocketService: WebsocketService) {
    this.Log = this.webSocketService.getLogMessage();

  }

  // ngOnInit(): void {
  //   const wsUrl = 'ws://localhost:8181/ws/tickets';
  //   this.webSocketService.connect(wsUrl);
  // }

  ngOnDestroy(): void {
    this.webSocketService.close();
  }

}
