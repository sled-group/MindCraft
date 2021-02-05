import { Injectable } from '@angular/core';
import { Datum } from './datum';
import { Http, Response } from '@angular/http';

@Injectable()
export class DatumService {
    private dataUrl = '/api/data';

    constructor (private http: Http) {}

    // get("/api/data")
    getData(): Promise<void | Datum[]> {
      return this.http.get(this.dataUrl)
                 .toPromise()
                 .then(response => response.json() as Datum[])
                 .catch(this.handleError);
    }

    // post("/api/data")
    createDatum(newDatum: Datum): Promise<void | Datum> {
      return this.http.post(this.dataUrl, newDatum)
                 .toPromise()
                 .then(response => response.json() as Datum)
                 .catch(this.handleError);
    }

    // get("/api/data/:id") endpoint not used by Angular app

    // delete("/api/data/:id")
    deleteDatum(delDatumId: String): Promise<void | String> {
      return this.http.delete(this.dataUrl + '/' + delDatumId)
                 .toPromise()
                 .then(response => response.json() as String)
                 .catch(this.handleError);
    }

    // put("/api/data/:id")
    updateDatum(putDatum: Datum): Promise<void | Datum> {
      var putUrl = this.dataUrl + '/' + putDatum._id;
      return this.http.put(putUrl, putDatum)
                 .toPromise()
                 .then(response => response.json() as Datum)
                 .catch(this.handleError);
    }

    private handleError (error: any) {
      let errMsg = (error.message) ? error.message :
      error.status ? `${error.status} - ${error.statusText}` : 'Server error';
      console.error(errMsg); // log to console instead
    }
}
