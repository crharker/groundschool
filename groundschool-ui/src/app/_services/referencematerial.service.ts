import { Injectable } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Http, Response, Headers, RequestOptions } from "@angular/http";

// Import RxJs required methods
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

import { environment } from '../../environments/environment';
import { ReferenceMaterial } from '../_models/referencematerial';

@Injectable()
export class ReferenceMaterialService {
    backendUrl = environment.apiUrl;
  
    constructor(private http: Http) { }
  
    get(id: number): Observable<ReferenceMaterial> {
        let headers = new Headers({
          "Accept": "application/json",
          "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
  
        return this.http.get(this.backendUrl + "/referencematerials/" + id, options)
          .map((res: Response) => Object.assign(new ReferenceMaterial(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    create(referencematerial: ReferenceMaterial): Observable<ReferenceMaterial> {
        let body = JSON.stringify(referencematerial);
        let headers = new Headers({
            "Accept": "application/json",
            "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
      
        return this.http.post(this.backendUrl + "/referencematerials", body, options)
          .map((res: Response) => Object.assign(new ReferenceMaterial(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    update(referencematerial: ReferenceMaterial): Observable<ReferenceMaterial> {
        let body = JSON.stringify(referencematerial);
        let headers = new Headers({
            "Accept": "application/json",
            "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
        
        return this.http.put(this.backendUrl + "/referencematerials", body, options)
          .map((res: Response) => Object.assign(new ReferenceMaterial(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
      }

    delete(id: number): Observable<ReferenceMaterial> {
        let headers = new Headers({
            "Accept": "application/json",
            "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
        
        return this.http.delete(this.backendUrl + "/referencematerials/" + id, options)
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    getAll(): Observable<ReferenceMaterial[]> {
        let headers = new Headers({
          "Accept": "application/json",
          "Content-Type": "application/json"
        });
        let options = new RequestOptions({ headers: headers });
  
        return this.http.get(this.backendUrl + "/referencematerials", options)
          .map((res: Response) => Object.assign(new ReferenceMaterial(), res.json()))
          .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

}