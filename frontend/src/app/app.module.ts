import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './core/component/footer/footer.component';
import { HeaderComponent } from './core/component/header/header.component';
import { TripNewComponent } from './trip/trip/trip-new/trip-new.component';
import { TripItemComponent } from './trip/trip/trip-item/trip-item.component';
import { TripOverviewComponent } from './trip/trip/trip-overview/trip-overview.component';
import { EventItemComponent } from './trip/event/event-item/event-item.component';
import { EventOverviewComponent } from './trip/event/event-overview/event-overview.component';
import {RouterModule} from "@angular/router";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    TripNewComponent,
    TripItemComponent,
    TripOverviewComponent,
    EventItemComponent,
    EventOverviewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot([
      {path: '', redirectTo: '/home', pathMatch: 'full'},
      {path: 'home', component: TripOverviewComponent},
      // TODO: add redirect
      // {path: 'search', component: SearchOverviewComponent},
      // {path: 'shoppingcard', component: ShoppingCartOverviewComponent}

    ]),
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
