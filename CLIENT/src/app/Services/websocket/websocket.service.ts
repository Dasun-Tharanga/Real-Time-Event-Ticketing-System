import { Injectable, Signal, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private socket: WebSocket | null = null;
  private ticketCout = signal<number>(0);

  constructor() { }

  connect(url: string): void {
    if (!this.socket || this.socket.readyState !== WebSocket.OPEN) {
      this.socket = new WebSocket(url);

      this.socket.onopen = () => {
        console.log('WebSocket connection established');
      };

      this.socket.onmessage = (event: MessageEvent) => {
        const newCount = parseInt(event.data, 10);
        this.ticketCout.set(newCount);
      };

      this.socket.onerror = (error) => {
        console.error('WebSocket error', error);
      };

      this.socket.onclose = () => {
        console.log('Websocket connection closed');
      };

    }
  }

  getTicketCount(): Signal<number> {
    return this.ticketCout;
  }

  close(): void {
    this.socket?.close();
  }

}
