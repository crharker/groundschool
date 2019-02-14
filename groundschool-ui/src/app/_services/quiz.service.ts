import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '@environments/environment';
import { Quiz } from '@app/_models';

@Injectable({ providedIn: 'root' })
export class QuizService {
    constructor(private http: HttpClient) { }
  
    get(id: number) {
      return this.http.get(`${environment.apiUrl}/quizzes/${id}`);
    }

    create(quiz: Quiz) {
      return this.http.post(`${environment.apiUrl}/quizzes`, quiz);
    }

    update(quiz: Quiz) {
      return this.http.put(`${environment.apiUrl}/quizzes`, quiz);
    }

    delete(id: number) {
      return this.http.delete(`${environment.apiUrl}/quizzes/${id}`);
    }

    getAll() {
      return this.http.get<Quiz[]>(`${environment.apiUrl}/quizzes`);
    }

    start(quizId: number) {
      return this.http.post(`${environment.apiUrl}/quizzes/${quizId}/start`, null);
    }

    complete(quizId: number) {
      return this.http.post(`${environment.apiUrl}/quizzes/${quizId}/complete`, null);
    }

    addQuestion(quizId: number, questionId: number) {
      return this.http.post(`${environment.apiUrl}/quizzes/${quizId}/add/${questionId}`, null);
    }

    removeQuestion(quizId: number, questionId: number) {
      return this.http.post(`${environment.apiUrl}/quizzes/${quizId}/remove/${questionId}`, null);
    }

}