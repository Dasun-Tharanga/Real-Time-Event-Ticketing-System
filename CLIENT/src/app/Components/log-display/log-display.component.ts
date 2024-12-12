import { Component, OnInit } from '@angular/core';
import { signal, computed } from '@angular/core';
import { WebsocketService } from '../../Services/websocket/websocket.service';

@Component({
  selector: 'app-log-display',
  standalone: false,

  templateUrl: './log-display.component.html',
  styleUrl: './log-display.component.css'
})
export class LogDisplayComponent implements OnInit {
  // Computed property to safely access log values
  logs = signal<{ timestamp: string; message: string }[]>([]);
  displayLogs = computed(() => this.logs());

  constructor(private websocketService: WebsocketService) { }

  ngOnInit(): void {
    // Connect to WebSocket when component initializes
    this.websocketService.connect('ws://localhost:8181/ws/tickets');

  }

  addSampleLog() {
    // Placeholder method - you'll need to implement this in the WebsocketService
    // This might involve sending a log message through the WebSocket or using a mock method
    this.websocketService.addLog('hellooo')
  }

  clearAllLogs() {
    // Update the signal to an empty array
    this.websocketService.clearLogs();
    this.logs.set([]);
  }
}
