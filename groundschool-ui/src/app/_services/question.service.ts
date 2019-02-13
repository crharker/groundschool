import { Injectable } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Http, Response, Headers, RequestOptions } from "@angular/http";

// Import RxJs required methods
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

import { environment } from '../../environments/environment';
import { Question } from '../_models/question';

@Injectable()
export class QuestionService {
    backendUrl = environment.apiUrl;
  
    constructor(private http: Http) { }
  
    get(id: number): Observable<Question> {
        let headers = new Headers({
          "Accept": "application/json",
          "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
  
        return this.http.get(this.backendUrl + "/questions/" + id, options)
          .map((res: Response) => Object.assign(new Question(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    create(question: Question): Observable<Question> {
        let body = JSON.stringify(question);
        let headers = new Headers({
            "Accept": "application/json",
            "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
      
        return this.http.post(this.backendUrl + "/questions", body, options)
          .map((res: Response) => Object.assign(new Question(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    update(question: Question): Observable<Question> {
        let body = JSON.stringify(question);
        let headers = new Headers({
            "Accept": "application/json",
            "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
        
        return this.http.put(this.backendUrl + "/questions", body, options)
          .map((res: Response) => Object.assign(new Question(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
      }

    delete(id: number): Observable<Question> {
        let headers = new Headers({
            "Accept": "application/json",
            "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
        
        return this.http.delete(this.backendUrl + "/questions/" + id, options)
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    getAll(): Observable<Question[]> {
        let headers = new Headers({
          "Accept": "application/json",
          "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
  
        return this.http.get(this.backendUrl + "/questions", options)
          .map((res: Response) => Object.assign(new Question(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    answer(questionId: number, userId: number, selection: string): Observable<Boolean> {
      let headers = new Headers({
        "Accept": "application/json",
        "Content-Type": "application/json"
      });
      let options = new RequestOptions({ headers: headers });

      return this.http.post(this.backendUrl + "/questions/" + questionId + "/answer/" + userId + "/" + selection, options)
        .map((res: Response) => Object.assign(new Boolean(), res.json()))
        .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    assignReferenceMaterial(questionId: number, referenceMaterialId: number): void {
      let headers = new Headers({
        "Accept": "application/json",
        "Content-Type": "application/json"
      });
      let options = new RequestOptions({ headers: headers });

      this.http.post(this.backendUrl + "/questions/" + questionId + "/assign/referencematerial/" + referenceMaterialId, options)
        .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    unassignReferenceMaterial(questionId: number, referenceMaterialId: number): void {
      let headers = new Headers({
        "Accept": "application/json",
        "Content-Type": "application/json"
      });
      let options = new RequestOptions({ headers: headers });

      this.http.post(this.backendUrl + "/questions/" + questionId + "/unassign/referencematerial/" + referenceMaterialId, options)
        .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

}