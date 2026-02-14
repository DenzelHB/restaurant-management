import { ApplicationConfig, importProvidersFrom, provideZoneChangeDetection } from '@angular/core';
import { PreloadAllModules, provideRouter, withPreloading, withViewTransitions } from '@angular/router';
import { DatePipe } from '@angular/common';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { HttpClient, provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';

import { environment } from '../environments/environment'; 
import { OverlayContainer } from '@angular/cdk/overlay';
import { CustomOverlayContainer } from './theme/utils/custom-overlay-container';
import { provideTranslateService } from '@ngx-translate/core';
import { provideTranslateHttpLoader } from '@ngx-translate/http-loader';

import { InMemoryWebApiModule } from 'angular-in-memory-web-api';
import { UsersData } from './common/data/user-data';
import { DateAdapter, MAT_DATE_FORMATS, MAT_NATIVE_DATE_FORMATS, NativeDateAdapter } from '@angular/material/core';

import { progressInterceptor } from 'ngx-progressbar/http';

export const appConfig: ApplicationConfig = {
  providers: [
    // provideZoneChangeDetection({ eventCoalescing: true }), 
    provideHttpClient(withFetch(), withInterceptors([progressInterceptor])),
    provideRouter(
      routes,
      withViewTransitions(),
      withPreloading(PreloadAllModules),  // comment this line for enable lazy-loading
    ),    
    provideClientHydration(),
    provideAnimationsAsync(),
    provideTranslateService({
      loader: provideTranslateHttpLoader({ prefix: environment.url + "./i18n/", suffix: ".json" }),
    }),    
    importProvidersFrom([ 
      InMemoryWebApiModule.forRoot(UsersData, { passThruUnknownUrl: true, delay: 1000 })
    ]),
    DatePipe,
    { provide: OverlayContainer, useClass: CustomOverlayContainer },
    { provide: DateAdapter, useClass: NativeDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: MAT_NATIVE_DATE_FORMATS },       
  ]
};
