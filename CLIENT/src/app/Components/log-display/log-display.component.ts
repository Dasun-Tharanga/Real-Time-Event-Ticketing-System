import { Component, OnInit } from '@angular/core';
import { signal, computed } from '@angular/core';
import { WebsocketService } from '../../Services/websocket/websocket.service';

@Component({
  selector: 'app-log-display',
  standalone: false,

  templateUrl: './log-display.component.html',
  styleUrl: './log-display.component.css'
})
export class LogDisplayComponent {

}
