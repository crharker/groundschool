import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '@environments/environment';
import { LessonPlan } from '@app/_models';

@Injectable({ providedIn: 'root' })
export class LessonPlanService {
    constructor(private http: HttpClient) { }
  
    get(id: number) {
      return this.http.get(`${environment.apiUrl}/lessonplans/${id}`);
    }

    create(lessonplan: LessonPlan) {
      return this.http.post(`${environment.apiUrl}/lessonplans`, lessonplan);
    }

    update(lessonplan: LessonPlan) {
      return this.http.put(`${environment.apiUrl}/lessonplans`, lessonplan);
    }

    delete(id: number) {
      return this.http.delete(`${environment.apiUrl}/lessonplans/${id}`);
    }

    getAll() {
      return this.http.get<LessonPlan[]>(`${environment.apiUrl}/lessonplans`);
    }

}