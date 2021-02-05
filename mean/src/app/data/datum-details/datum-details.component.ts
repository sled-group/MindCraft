import { Component, Input } from '@angular/core';
import { Datum } from '../datum';
import { DatumService } from '../datum.service';

@Component({
  selector: 'datum-details',
  templateUrl: './datum-details.component.html',
  styleUrls: ['./datum-details.component.css']
})

export class DatumDetailsComponent {
  @Input()
  datum: Datum;

  @Input()
  createHandler: Function;
  @Input()
  updateHandler: Function;
  @Input()
  deleteHandler: Function;

  constructor (private datumService: DatumService) {}

  createDatum(datum: Datum) {
    this.datumService.createDatum(datum).then((newDatum: Datum) => {
      this.createHandler(newDatum);
    });
  }

  updateDatum(datum: Datum): void {
    this.datumService.updateDatum(datum).then((updatedDatum: Datum) => {
      this.updateHandler(updatedDatum);
    });
  }

  deleteDatum(datumId: String): void {
    this.datumService.deleteDatum(datumId).then((deletedDatumId: String) => {
      this.deleteHandler(deletedDatumId);
    });
  }
}