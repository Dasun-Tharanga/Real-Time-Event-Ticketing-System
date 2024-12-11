import { Component, OnInit } from '@angular/core';
import { LogService } from '../../Services/log/log.service';

@Component({
  selector: 'app-log-display',
  standalone: false,

  templateUrl: './log-display.component.html',
  styleUrl: './log-display.component.css'
})
export class LogDisplayComponent implements OnInit {
  logs!: () => { timestamp: string, message: string }[];


  constructor(private logService: LogService) { }

  ngOnInit(): void {
    // Readonly signalfrom the LogService
    this.logs = this.logService.logs;
  }

  addSampleLog() {
    this.logService.addLog("hi");
  }

  clearAllLogs() {
    this.logService.clearLogs();
  }

}
