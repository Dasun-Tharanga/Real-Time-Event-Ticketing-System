import { Injectable, signal } from '@angular/core';
import { timestamp } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LogService {

  constructor() { }

  // Signal to store the list of logs
  private logsSignal = signal<{ timestamp: string; message: string }[]>([]);

  get logs() {
    return this.logsSignal.asReadonly();
  }


  // Method to add a log

  addLog(message: string) {
    const newLog = { timestamp: new Date().toISOString(), message };
    this.logsSignal.update((logs) => [...logs, newLog]);
  }

  // Method to clear all logs

  clearLogs() {
    this.logsSignal.set([]);
  }

}
