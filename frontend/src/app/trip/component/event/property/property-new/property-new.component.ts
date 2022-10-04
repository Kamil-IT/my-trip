import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-property-new',
  templateUrl: './property-new.component.html',
  styleUrls: ['./property-new.component.scss']
})
export class PropertyNewComponent implements OnInit {

  @Input()
  isSaveModeActive = true;

  @Output()
  bookTitleCreated = new EventEmitter<Map<string, string>>();
  value = '';
  key = '';
  clicked = false;

  constructor() {
  }

  ngOnInit(): void {
  }

  addProperty(): void {
    let property = new Map<string, string>([['key', this.key], ['value', this.value]]);
    this.bookTitleCreated.emit(property)
  }

}
