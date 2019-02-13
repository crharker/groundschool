import { Injectable } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Http, Response, Headers, RequestOptions } from "@angular/http";

// Import RxJs required methods
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

import { environment } from '../../environments/environment';
import { LessonPlan } from '../_models/lessonplan';

@Injectable()
export class LessonPlanService {
    backendUrl = environment.apiUrl;
  
    constructor(private http: Http) { }
  
    get(id: number): Observable<LessonPlan> {
        let headers = new Headers({
          "Accept": "application/json",
          "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
  
        return this.http.get(this.backendUrl + "/lessonplans/" + id, options)
          .map((res: Response) => Object.assign(new LessonPlan(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    create(lessonplan: LessonPlan): Observable<LessonPlan> {
        let body = JSON.stringify(lessonplan);
        let headers = new Headers({
            "Accept": "application/json",
            "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
      
        return this.http.post(this.backendUrl + "/lessonplans", body, options)
          .map((res: Response) => Object.assign(new LessonPlan(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    update(lessonplan: LessonPlan): Observable<LessonPlan> {
        let body = JSON.stringify(lessonplan);
        let headers = new Headers({
            "Accept": "application/json",
            "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
        
        return this.http.put(this.backendUrl + "/lessonplans", body, options)
          .map((res: Response) => Object.assign(new LessonPlan(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
      }

    delete(id: number): Observable<LessonPlan> {
        let headers = new Headers({
            "Accept": "application/json",
            "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
        
        return this.http.delete(this.backendUrl + "/lessonplans/" + id, options)
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    getAll(): Observable<LessonPlan[]> {
        let headers = new Headers({
          "Accept": "application/json",
          "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
  
        return this.http.get(this.backendUrl + "/lessonplans", options)
          .map((res: Response) => Object.assign(new LessonPlan(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

}