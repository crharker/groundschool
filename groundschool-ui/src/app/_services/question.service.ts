import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '@environments/environment';
import { Question } from '@app/_models';

@Injectable({ providedIn: 'root' })
export class QuestionService {
    constructor(private http: HttpClient) { }
  
    get(id: number) {
      return this.http.get(`${environment.apiUrl}/questions/${id}`);
    }

    create(question: Question) {
      return this.http.post(`${environment.apiUrl}/questions`, question);
    }

    update(question: Question) {
      return this.http.put(`${environment.apiUrl}/questions`, question);
    }

    delete(id: number) {
      return this.http.delete(`${environment.apiUrl}/questions/${id}`);
    }

    getAll() {
      return this.http.get<Question[]>(`${environment.apiUrl}/questions`);
    }

    answer(questionId: number, userId: number, selection: string) {
      return this.http.post(`${environment.apiUrl}/questions/${questionId}/answer/${userId}/${selection}`, null);
    }

    assignReferenceMaterial(questionId: number, referenceMaterialId: number) {
      this.http.post(`${environment.apiUrl}/questions/${questionId}/assign/referencematerial/${referenceMaterialId}`, null);
    }

    unassignReferenceMaterial(questionId: number, referenceMaterialId: number) {
      this.http.post(`${environment.apiUrl}/questions/${questionId}/unassign/referencematerial/${referenceMaterialId}`, null);
    }

}