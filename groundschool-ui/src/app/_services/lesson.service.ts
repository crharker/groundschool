import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '@environments/environment';
import { Lesson } from '@app/_models';

@Injectable({ providedIn: 'root' })
export class LessonService {
    constructor(private http: HttpClient) { }
  
    get(id: number) {
      return this.http.get(`${environment.apiUrl}/lessons/${id}`);
    }

    create(lesson: Lesson) {
      return this.http.post(`${environment.apiUrl}/lessons`, lesson);
    }

    update(lesson: Lesson) {
      return this.http.put(`${environment.apiUrl}/lessons`, lesson);
    }

    delete(id: number) {
      return this.http.delete(`${environment.apiUrl}/lessons/${id}`);
    }

    getAll() {
      return this.http.get<Lesson[]>(`${environment.apiUrl}/lessons`);
    }

    getAllAttendedLessons(userId: number) {
      return this.http.get<Lesson[]>(`${environment.apiUrl}/lessons/${userId}/attended`);
    }

}