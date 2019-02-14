import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '@environments/environment';
import { ReferenceMaterial } from '@app/_models';

@Injectable({ providedIn: 'root' })
export class ReferenceMaterialService {
    constructor(private http: HttpClient) { }
  
    get(id: number) {
      return this.http.get(`${environment.apiUrl}/referencematerials/${id}`);
    }

    create(referencematerial: ReferenceMaterial) {
      return this.http.post(`${environment.apiUrl}/referencematerials`, referencematerial);
    }

    update(referencematerial: ReferenceMaterial) {
      return this.http.put(`${environment.apiUrl}/referencematerials`, referencematerial);
      }

    delete(id: number) {
      return this.http.delete(`${environment.apiUrl}/referencematerials/${id}`);
    }

    getAll() {
      return this.http.get<ReferenceMaterial[]>(`${environment.apiUrl}/referencematerials`);
    }

}