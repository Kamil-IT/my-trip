import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './core/component/footer/footer.component';
import { HeaderComponent } from './core/component/header/header.component';
import { TripNewComponent } from './trip/component/trip/trip-new/trip-new.component';
import { TripItemComponent } from './trip/component/trip/trip-item/trip-item.component';
import { TripOverviewComponent } from './trip/component/trip/trip-overview/trip-overview.component';
import { EventItemComponent } from './trip/component/event/event-item/event-item.component';
import { EventOverviewComponent } from './trip/component/event/event-overview/event-overview.component';
import {RouterModule} from "@angular/router";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TripEditComponent } from './trip/component/trip/trip-edit/trip-edit.component';
import { UserOverviewComponent } from './user/component/user/user-overview/user-overview.component';
import {TripService} from "./trip/services/TripService";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { EventEditComponent } from './trip/component/event/event-edit/event-edit.component';
import { EventNewComponent } from './trip/component/event/event-new/event-new.component';
import { PropertyEditComponent } from './trip/component/event/property/property-edit/property-edit.component';
import { PropertyNewComponent } from './trip/component/event/property/property-new/property-new.component';
import { ParticipantComponent } from './trip/component/trip/trip-participant/participant.component';
import {EventService} from "./trip/services/EventService";
import { UserDetailComponent } from './user/component/user/user-detail/user-detail.component';
import {UserService} from "./user/service/UserService";
import { LoginComponent } from './core/component/login/login.component';
import {AuthService} from "./core/service/AuthService";
import {HeaderService} from "./core/service/HeaderService";
import { ProfileOverviewComponent } from './user/component/profile/profile-overview/profile-overview.component';
import {CurrentUserPrivilegesService} from "./core/service/CurrentUserPrivilegesService";
import { EventWeatherTableComponent } from './trip/component/event/event-weather-table/event-weather-table.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    TripNewComponent,
    TripItemComponent,
    TripOverviewComponent,
    EventItemComponent,
    EventOverviewComponent,
    TripEditComponent,
    UserOverviewComponent,
    EventEditComponent,
    EventNewComponent,
    PropertyEditComponent,
    PropertyNewComponent,
    ParticipantComponent,
    UserDetailComponent,
    LoginComponent,
    ProfileOverviewComponent,
    EventWeatherTableComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot([
      {path: '', redirectTo: '/home', pathMatch: 'full'},
      {path: 'home', component: TripOverviewComponent},
      {path: 'trip/:id', component: TripEditComponent},
      {path: 'user', component: UserOverviewComponent},
      {path: 'login', component: LoginComponent},
      {path: 'profile', component: ProfileOverviewComponent},
    ]),
    NgbModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [AuthService, HeaderService, TripService, EventService, UserService, HttpClient, CurrentUserPrivilegesService],
  bootstrap: [AppComponent]
})
export class AppModule { }
