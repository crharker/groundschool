import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '@environments/environment';
import { User } from '@app/_models';

@Injectable({ providedIn: 'root' })
export class UserService {
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<User[]>(`${environment.apiUrl}/users`);
    }

    get(id: number) {
        return this.http.get<User>(`${environment.apiUrl}/users/${id}`);
    }

    getByUsername(username: string) {
        return this.http.get<User>(`${environment.apiUrl}/users/username/${username}`);
    }

    create(user: User) {
        return this.http.post<User>(`${environment.apiUrl}/users`, user);
    }

    update(user: User) {
        return this.http.put<User>(`${environment.apiUrl}/users`, user);
    }

    delete(id: number) {
        return this.http.delete<User>(`${environment.apiUrl}/users/${id}`);
    }

    getUsernameAvailability(username: string) {
        return this.http.get<boolean>(`${environment.apiUrl}/users/username/${username}/available`);
    }

    verifyNotificationSettings(userId: number, type: string) {
        this.http.get(`${environment.apiUrl}/users/${userId}/verify/${type}`);
    }

    updatePassword(userId: number, password: string, verificationCode: string) {
        return this.http.post<boolean>(`${environment.apiUrl}/users/${userId}/password/${verificationCode}`, password);
    }

    passwordReset(email: string) {
        return this.http.post<boolean>(`${environment.apiUrl}/users/password/reset`, { email });
    }

    logout() {
        return this.http.post(`${environment.apiUrl}/users/logout`, null);
    }

    invite(email: string) {
        this.http.post(`${environment.apiUrl}/users/invite`, email);
    }

}