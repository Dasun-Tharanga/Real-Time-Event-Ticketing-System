import { Injectable, Signal, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private socket: WebSocket | null = null;
  private ticketCout = signal<number>(0);
  private logMessage = signal<string>('');

  constructor() {
    if (!this.socket || this.socket.readyState !== WebSocket.OPEN) {
      this.socket = new WebSocket('ws://localhost:8181/ws/tickets');

      this.socket.onopen = () => {
        console.log('WebSocket connection established');
      };

      this.socket.onmessage = (event: MessageEvent) => {
        const message = JSON.parse(event.data);
        switch (message.type) {
          case 'ticketCount':
            this.ticketCout.set(message.count);
            break;
          case 'log':
            this.logMessage.set(message.message);
            break;
          default:
            console.log('Unknown message type', message.type);
        }
      };

      this.socket.onerror = (error) => {
        console.error('WebSocket error', error);
      };

      this.socket.onclose = () => {
        console.log('Websocket connection closed');
      };

    }
  }

  // connect(url: string): void {
  //   if (!this.socket || this.socket.readyState !== WebSocket.OPEN) {
  //     this.socket = new WebSocket(url);

  //     this.socket.onopen = () => {
  //       console.log('WebSocket connection established');
  //     };

  //     this.socket.onmessage = (event: MessageEvent) => {
  //       const message = JSON.parse(event.data);
  //       switch (message.type) {
  //         case 'ticketCount':
  //           this.ticketCout.set(message.count);
  //           break;
  //         case 'log':
  //           this.logMessage.set(message.message);
  //           break;
  //         default:
  //           console.log('Unknown message type', message.type);
  //       }
  //     };

  //     this.socket.onerror = (error) => {
  //       console.error('WebSocket error', error);
  //     };

  //     this.socket.onclose = () => {
  //       console.log('Websocket connection closed');
  //     };

  //   }
  // }

  getTicketCount(): Signal<number> {
    return this.ticketCout;
  }

  getLogMessage(): Signal<string> {
    return this.logMessage;
  }

  close(): void {
    this.socket?.close();
  }

}
