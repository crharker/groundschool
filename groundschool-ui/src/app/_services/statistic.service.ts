import { Injectable } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Http, Response, Headers, RequestOptions } from "@angular/http";

// Import RxJs required methods
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

import { environment } from '../../environments/environment';
import { Statistic } from '../_models/statistic';

@Injectable()
export class StatisticService {
    backendUrl = environment.apiUrl;
  
    constructor(private http: Http) { }
  
    get(id: number): Observable<Statistic> {
        let headers = new Headers({
          "Accept": "application/json",
          "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
  
        return this.http.get(this.backendUrl + "/statistics/" + id, options)
          .map((res: Response) => Object.assign(new Statistic(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    create(statistic: Statistic): Observable<Statistic> {
        let body = JSON.stringify(statistic);
        let headers = new Headers({
            "Accept": "application/json",
            "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
      
        return this.http.post(this.backendUrl + "/statistics", body, options)
          .map((res: Response) => Object.assign(new Statistic(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    update(statistic: Statistic): Observable<Statistic> {
        let body = JSON.stringify(statistic);
        let headers = new Headers({
            "Accept": "application/json",
            "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
        
        return this.http.put(this.backendUrl + "/statistics", body, options)
          .map((res: Response) => Object.assign(new Statistic(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
      }

    delete(id: number): Observable<Statistic> {
        let headers = new Headers({
            "Accept": "application/json",
            "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
        
        return this.http.delete(this.backendUrl + "/statistics/" + id, options)
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    getAll(): Observable<Statistic[]> {
        let headers = new Headers({
          "Accept": "application/json",
          "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
  
        return this.http.get(this.backendUrl + "/statistics", options)
          .map((res: Response) => Object.assign(new Statistic(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    getByUser(userId: number): Observable<Statistic[]> {
      let headers = new Headers({
        "Accept": "application/json",
        "Content-Type": "application/json"
      });
      let options = new RequestOptions({ headers: headers });

      return this.http.get(this.backendUrl + "/statistics/user/" + userId, options)
        .map((res: Response) => Object.assign(new Statistic(), res.json()))
        .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  getByEvent(eventId: number): Observable<Statistic[]> {
    let headers = new Headers({
      "Accept": "application/json",
      "Content-Type": "application/json"
    });
    let options = new RequestOptions({ headers: headers });

    return this.http.get(this.backendUrl + "/statistics/event/" + eventId, options)
      .map((res: Response) => Object.assign(new Statistic(), res.json()))
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
}

getByQuestion(questionId: number): Observable<Statistic[]> {
  let headers = new Headers({
    "Accept": "application/json",
    "Content-Type": "application/json"
  });
  let options = new RequestOptions({ headers: headers });

  return this.http.get(this.backendUrl + "/statistics/question/" + questionId, options)
    .map((res: Response) => Object.assign(new Statistic(), res.json()))
    .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
}

getByQuiz(quizId: number): Observable<Statistic[]> {
  let headers = new Headers({
    "Accept": "application/json",
    "Content-Type": "application/json"
  });
  let options = new RequestOptions({ headers: headers });

  return this.http.get(this.backendUrl + "/statistics/quiz/" + quizId, options)
    .map((res: Response) => Object.assign(new Statistic(), res.json()))
    .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
}

}