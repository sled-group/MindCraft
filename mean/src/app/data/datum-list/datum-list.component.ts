import { Component, OnInit } from '@angular/core';
import { Datum } from '../datum';
import { DatumService } from '../datum.service';
import { DatumDetailsComponent } from '../datum-details/datum-details.component';

@Component({
  selector: 'datum-list',
  templateUrl: './datum-list.component.html',
  styleUrls: ['./datum-list.component.css'],
  providers: [DatumService]
})

export class DatumListComponent implements OnInit {

  data: Datum[]
  selectedDatum: Datum

  constructor(private datumService: DatumService) { }

  ngOnInit() {
     this.datumService
      .getData()
      .then((data: Datum[]) => {
        this.data = data.map((datum) => {
          if (!datum.state) {
            datum.state = '';
          }
          if (!datum.player) {
          	datum.player = '';
          }
          return datum;
        });
      });
  }

  private getIndexOfDatum = (datumId: String) => {
    return this.data.findIndex((datum) => {
      return datum._id === datumId;
    });
  }

  selectDatum(datum: Datum) {
    this.selectedDatum = datum
  }

  createNewDatum() {
    var datum: Datum = {
      player: '',
      state: '',
    };

    // By default, a newly-created datum will have the selected state.
    this.selectDatum(datum);
  }

  deleteDatum = (datumId: String) => {
    var idx = this.getIndexOfDatum(datumId);
    if (idx !== -1) {
      this.data.splice(idx, 1);
      this.selectDatum(null);
    }
    return this.data;
  }

  addDatum = (datum: Datum) => {
    this.data.push(datum);
    this.selectDatum(datum);
    return this.data;
  }

  updateDatum = (datum: Datum) => {
    var idx = this.getIndexOfDatum(datum._id);
    if (idx !== -1) {
      this.data[idx] = datum;
      this.selectDatum(datum);
    }
    return this.data;
  }
}