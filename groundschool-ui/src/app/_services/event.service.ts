import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '@environments/environment';
import { Event } from '@app/_models';

@Injectable({ providedIn: 'root' })
export class EventService {
    constructor(private http: HttpClient) { }
  
    get(id: number) {
      return this.http.get(`${environment.apiUrl}/events/${id}`);
    }

    create(event: Event) {
      return this.http.post(`${environment.apiUrl}/events`, event);
    }

    update(event: Event) {
      return this.http.put(`${environment.apiUrl}/events`, event);
    }

    delete(id: number) {
      return this.http.delete(`${environment.apiUrl}/events/${id}`);
    }

    getAll() {
      return this.http.get<Event[]>(`${environment.apiUrl}/events`);
    }

    upcoming(count: number) {
      return this.http.get<Event[]>(`${environment.apiUrl}/events/upcoming/${count}`);
    }

    rsvp(eventId: number, userId: number, confirm: boolean) {
      this.http.get(`${environment.apiUrl}/events/${eventId}/rsvp/${userId}/${confirm}/WEB`);
    }

    register(eventId: number, userId: number) {
      this.http.get(`${environment.apiUrl}/events/${eventId}/register/${userId}`);
    }

    unregister(eventId: number, userId: number) {
      this.http.get(`${environment.apiUrl}/events/${eventId}/unregister/${userId}`);
    }

    getCheckinCode(eventId: number) {
      return this.http.get(`${environment.apiUrl}/events/${eventId}/checkincode`);
    }

    assignLessonPlan(eventId: number, lessonPlanId: number) {
      this.http.post(`${environment.apiUrl}/events/${eventId}/assign/lessonplan/${lessonPlanId}`, null);
    }

    unassignLessonPlan(eventId: number, lessonPlanId: number) {
      this.http.post(`${environment.apiUrl}/events/${eventId}/unassign/lessonplan/${lessonPlanId}`, null);
    }

    checkin(eventId: number, userId: number, code: string) {
      return this.http.post(`${environment.apiUrl}/events/${eventId}/checkin/${userId}/${code}`, null);
    }

    start(eventId: number) {
      return this.http.post(`${environment.apiUrl}/events/${eventId}/start`, null);
    }

    complete(eventId: number) {
      return this.http.post(`${environment.apiUrl}/events/${eventId}/complete`, null);
    }

}