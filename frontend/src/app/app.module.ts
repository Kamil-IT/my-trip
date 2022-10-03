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
import { UserOverviewComponent } from './user/user-overview/user-overview.component';
import {TripService} from "./trip/services/TripService";
import {HttpClient, HttpClientModule} from "@angular/common/http";

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
    UserOverviewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot([
      {path: '', redirectTo: '/home', pathMatch: 'full'},
      {path: 'home', component: TripOverviewComponent},
      // TODO: add redirect
      {path: 'trip', component: TripEditComponent},
      // {path: 'shoppingcard', component: ShoppingCartOverviewComponent}

    ]),
    NgbModule,
    HttpClientModule
  ],
  providers: [TripService, HttpClient],
  bootstrap: [AppComponent]
})
export class AppModule { }
