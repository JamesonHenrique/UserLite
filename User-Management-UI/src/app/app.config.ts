import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { HttpClientModule, withFetch } from '@angular/common/http'; // Add this import statement

import { routes } from './app.routes';
import { provideHttpClient } from '@angular/common/http';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';

import { provideAnimations } from '@angular/platform-browser/animations'
import { provideToastr } from 'ngx-toastr';
export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes), provideHttpClient(), HttpClientModule, provideAnimationsAsync(),  provideAnimations(),
    provideToastr(),
    provideHttpClient(withFetch())]
};
