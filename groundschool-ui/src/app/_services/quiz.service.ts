import { Injectable } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Http, Response, Headers, RequestOptions } from "@angular/http";

// Import RxJs required methods
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

import { environment } from '../../environments/environment';
import { Quiz } from '../_models/quiz';

@Injectable()
export class QuizService {
    backendUrl = environment.apiUrl;
  
    constructor(private http: Http) { }
  
    get(id: number): Observable<Quiz> {
        let headers = new Headers({
          "Accept": "application/json",
          "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
  
        return this.http.get(this.backendUrl + "/quizzes/" + id, options)
          .map((res: Response) => Object.assign(new Quiz(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    create(quiz: Quiz): Observable<Quiz> {
        let body = JSON.stringify(quiz);
        let headers = new Headers({
            "Accept": "application/json",
            "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
      
        return this.http.post(this.backendUrl + "/quizzes", body, options)
          .map((res: Response) => Object.assign(new Quiz(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    update(quiz: Quiz): Observable<Quiz> {
        let body = JSON.stringify(quiz);
        let headers = new Headers({
            "Accept": "application/json",
            "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
        
        return this.http.put(this.backendUrl + "/quizzes", body, options)
          .map((res: Response) => Object.assign(new Quiz(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
      }

    delete(id: number): Observable<Quiz> {
        let headers = new Headers({
            "Accept": "application/json",
            "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
        
        return this.http.delete(this.backendUrl + "/quizzes/" + id, options)
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    getAll(): Observable<Quiz[]> {
        let headers = new Headers({
          "Accept": "application/json",
          "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
  
        return this.http.get(this.backendUrl + "/quizzes", options)
          .map((res: Response) => Object.assign(new Quiz(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    start(quizId: number): Observable<Quiz> {
      let headers = new Headers({
        "Accept": "application/json",
        "Content-Type": "application/json"
      });
      let options = new RequestOptions({ headers: headers });

      return this.http.post(this.backendUrl + "/quizzes/" + quizId + "/start", options)
        .map((res: Response) => Object.assign(new Quiz(), res.json()))
        .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    complete(quizId: number): Observable<Quiz> {
      let headers = new Headers({
        "Accept": "application/json",
        "Content-Type": "application/json"
      });
      let options = new RequestOptions({ headers: headers });

      return this.http.post(this.backendUrl + "/quizzes/" + quizId + "/complete", options)
        .map((res: Response) => Object.assign(new Quiz(), res.json()))
        .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    addQuestion(quizId: number, questionId: number): Observable<Quiz> {
      let headers = new Headers({
        "Accept": "application/json",
        "Content-Type": "application/json"
      });
      let options = new RequestOptions({ headers: headers });

      return this.http.post(this.backendUrl + "/quizzes/" + quizId + "/add/" + questionId, options)
        .map((res: Response) => Object.assign(new Quiz(), res.json()))
        .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    removeQuestion(quizId: number, questionId: number): Observable<Quiz> {
      let headers = new Headers({
        "Accept": "application/json",
        "Content-Type": "application/json"
      });
      let options = new RequestOptions({ headers: headers });

      return this.http.post(this.backendUrl + "/quizzes/" + quizId + "/remove/" + questionId, options)
        .map((res: Response) => Object.assign(new Quiz(), res.json()))
        .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

}