import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';

export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
  const authToken = localStorage.getItem('jwtToken');
  const loginUrl = 'http://localhost:8080/auth/login';
  const signUpUrl = 'http://localhost:8080/auth/signup';

    // Verifica se l'URL della richiesta è quello della login
    if (req.url === loginUrl || req.url === signUpUrl) {
      // Se è la login, passa la richiesta originale senza aggiungere il token
      return next(req).pipe(
        catchError((err: any) => {
          handleHttpError(err);
          return throwError(() => err);
        })
      );
    }

  // Clone the request and add the authorization header
  const authReq = req.clone({
    setHeaders: {
      Authorization: `Bearer ${authToken}`
    }
  });

  // Pass the cloned request with the updated header to the next handler
  
  // return next(authReq);

  return next(authReq).pipe(
    catchError((err: any) => {
      if (err instanceof HttpErrorResponse) {
        // Handle HTTP errors
        if (err.status === 401) {
          // Specific handling for unauthorized errors         
          console.error('Unauthorized request:', err);
          // You might trigger a re-authentication flow or redirect the user here
        } else {
          // Handle other HTTP error codes
          console.error('HTTP error:', err);
        }
      } else {
        // Handle non-HTTP errors
        console.error('An error occurred:', err);
      }

      // Re-throw the error to propagate it further
      return throwError(() => err); 
    })
  );;
};

function handleHttpError(err: any) {
  throw new Error('Function not implemented.');
}
