import { Injectable } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Http, Response, Headers, RequestOptions } from "@angular/http";

// Import RxJs required methods
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

import { environment } from '../../environments/environment';
import { Event } from '../_models/event';

@Injectable()
export class EventService {
    backendUrl = environment.apiUrl;
  
    constructor(private http: Http) { }
  
    get(id: number): Observable<Event> {
        let headers = new Headers({
          "Accept": "application/json",
          "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
  
        return this.http.get(this.backendUrl + "/events/" + id, options)
          .map((res: Response) => Object.assign(new Event(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    create(event: Event): Observable<Event> {
        let body = JSON.stringify(event);
        let headers = new Headers({
            "Accept": "application/json",
            "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
      
        return this.http.post(this.backendUrl + "/events", body, options)
          .map((res: Response) => Object.assign(new Event(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    update(event: Event): Observable<Event> {
        let body = JSON.stringify(event);
        let headers = new Headers({
            "Accept": "application/json",
            "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
        
        return this.http.put(this.backendUrl + "/events", body, options)
          .map((res: Response) => Object.assign(new Event(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
      }

    delete(id: number): Observable<Event> {
        let headers = new Headers({
            "Accept": "application/json",
            "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
        
        return this.http.delete(this.backendUrl + "/events/" + id, options)
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    getAll(): Observable<Event[]> {
        let headers = new Headers({
          "Accept": "application/json",
          "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
  
        return this.http.get(this.backendUrl + "/events", options)
          .map((res: Response) => Object.assign(new Event(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    rsvp(eventId: number, userId: number, confirm: boolean): void {
        let headers = new Headers({
          "Accept": "application/json",
          "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
  
        this.http.get(this.backendUrl + "/events/" + eventId + "/rsvp/" + userId + "/" + confirm + "/WEB", options)
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    register(eventId: number, userId: number): void {
        let headers = new Headers({
          "Accept": "application/json",
          "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
  
        this.http.get(this.backendUrl + "/events/" + eventId + "/register/" + userId, options)
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    unregister(eventId: number, userId: number): void {
        let headers = new Headers({
          "Accept": "application/json",
          "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
  
        this.http.get(this.backendUrl + "/events/" + eventId + "/unregister/" + userId, options)
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    getCheckinCode(eventId: number): Observable<string> {
        let headers = new Headers({
          "Accept": "application/json",
          "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
  
        return this.http.get(this.backendUrl + "/events/" + eventId + "/checkincode", options)
        .map((res: Response) => Object.assign(new String(), res.json()))
        .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    assignLessonPlan(eventId: number, lessonPlanId: number): void {
        let headers = new Headers({
          "Accept": "application/json",
          "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
  
        this.http.post(this.backendUrl + "/events/" + eventId + "/assign/lessonplan/" + lessonPlanId, options)
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    unassignLessonPlan(eventId: number, lessonPlanId: number): void {
        let headers = new Headers({
          "Accept": "application/json",
          "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
  
        this.http.post(this.backendUrl + "/events/" + eventId + "/unassign/lessonplan/" + lessonPlanId, options)
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    checkin(eventId: number, userId: number, code: string): Observable<Boolean> {
        let headers = new Headers({
          "Accept": "application/json",
          "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
  
        return this.http.post(this.backendUrl + "/events/" + eventId + "/checkin/" + userId + "/" + code, options)
          .map((res: Response) => Object.assign(new Boolean(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    start(eventId: number): Observable<Event> {
        let headers = new Headers({
          "Accept": "application/json",
          "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
  
        return this.http.post(this.backendUrl + "/events/" + eventId + "/start", options)
          .map((res: Response) => Object.assign(new Event(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    complete(eventId: number): Observable<Event> {
        let headers = new Headers({
          "Accept": "application/json",
          "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
  
        return this.http.post(this.backendUrl + "/events/" + eventId + "/complete", options)
          .map((res: Response) => Object.assign(new Event(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

}