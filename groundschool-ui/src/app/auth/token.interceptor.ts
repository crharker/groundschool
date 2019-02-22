import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { AuthenticationService } from '@app/_services';
import { Observable } from 'rxjs';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  private token: string;

  constructor(
    private authenticationService: AuthenticationService
  ) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('intercept called...');
    this.token = localStorage.getItem('token');
    if (this.token) {
      console.log('adding ' + this.token + ' to header');
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.token}`
        }
      });
      }
    return next.handle(request);
  }
}
