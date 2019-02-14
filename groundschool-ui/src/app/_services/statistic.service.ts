import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '@environments/environment';
import { Statistic } from '@app/_models';

@Injectable({ providedIn: 'root' })
export class StatisticService {
    constructor(private http: HttpClient) { }
  
    get(id: number) {
      return this.http.get(`${environment.apiUrl}/statistics/${id}`);
    }

    create(statistic: Statistic) {
      return this.http.post(`${environment.apiUrl}/statistics`, statistic);
    }

    update(statistic: Statistic) {
      return this.http.put(`${environment.apiUrl}/statistics`, statistic);
    }

    delete(id: number) {
      return this.http.delete(`${environment.apiUrl}/statistics/${id}`);
    }

    getAll() {
      return this.http.get<Statistic[]>(`${environment.apiUrl}/statistics`);
    }

    getByUser(userId: number) {
      return this.http.get<Statistic[]>(`${environment.apiUrl}/statistics/user/${userId}`);
    }

    getByEvent(eventId: number) {
      return this.http.get<Statistic[]>(`${environment.apiUrl}/statistics/event/${eventId}`);
    }

    getByQuestion(questionId: number) {
      return this.http.get<Statistic[]>(`${environment.apiUrl}/statistics/question/${questionId}`);
    }

    getByQuiz(quizId: number) {
      return this.http.get<Statistic[]>(`${environment.apiUrl}/statistics/quiz/${quizId}`);
    }

}