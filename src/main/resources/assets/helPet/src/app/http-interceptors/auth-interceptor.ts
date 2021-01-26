// import { AuthService } from '../auth.service';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest, HttpEvent, HttpResponse } from '@angular/common/http';
// import { JwtHelperService } from '@auth0/angular-jwt';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private router: Router) {}

    intercept(req: HttpRequest<any>, next: HttpHandler) {
        // Get the auth token from the service.
        const authToken = localStorage.getItem('token');

        // Filter out requests that need to be sent without Authorization in header
        const reqToFilter = [
            'auth',
        ];
        const splittedStr = req.url.split('/');
        const endPart = splittedStr[splittedStr.length - 2] + '/' + splittedStr[splittedStr.length - 1];

        // Clone the request and replace the original headers with
        // cloned headers, updated with the authorization.

        let authReq;
        if (reqToFilter.indexOf(endPart) === -1) {
            authReq = req.clone({
                headers: req.headers.set('helpet-token', `${authToken}`)
            });
            return next.handle(authReq).pipe(
                map((event: HttpResponse<any>) => {
                    if (event instanceof HttpResponse && event.headers.get('helpet-token') !== null) {
                        localStorage.setItem('token', event.headers.get('helpet-token'));
                    }
                    if (event instanceof HttpResponse && event.status === 403) {
                        localStorage.removeItem('token');
                        this.router.navigate(['/pets']);
                    }
                    return event;
                }));
        }

        return next.handle(req);
        // send cloned request with header to the next handler.
    }
}
