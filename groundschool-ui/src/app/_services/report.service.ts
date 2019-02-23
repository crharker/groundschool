import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '@environments/environment';

@Injectable({ providedIn: 'root' })
export class ReportService {
    constructor(private http: HttpClient) { }
  
    quizCompletion(quizId: number) {
      return this.http.get(`${environment.apiUrl}/reports/quizzes/${quizId}/completion`);
    }

    quizResults(quizId: number) {
      return this.http.get(`${environment.apiUrl}/reports/quizzes/${quizId}/results`);
    }

    quizUserResults(quizId: number, userId: number) {
      return this.http.get(`${environment.apiUrl}/reports/quizzes/${quizId}/user/${userId}/results`);
    }

}