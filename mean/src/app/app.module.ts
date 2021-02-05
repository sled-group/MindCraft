import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DatumDetailsComponent } from './data/datum-details/datum-details.component';
import { DatumListComponent } from './data/datum-list/datum-list.component';

@NgModule({
  declarations: [
    AppComponent,
    DatumDetailsComponent,
    DatumListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
