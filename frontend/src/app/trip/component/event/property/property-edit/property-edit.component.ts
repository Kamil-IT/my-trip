import {Component, Input, OnInit} from '@angular/core';
import {KeyValue} from "@angular/common";
import {Property} from "../../../../model/Event";

@Component({
  selector: 'app-property-edit',
  templateUrl: './property-edit.component.html',
  styleUrls: ['./property-edit.component.scss']
})
export class PropertyEditComponent implements OnInit {

  @Input()
  property: Property = {key: "", value: ""};

  constructor() { }

  ngOnInit(): void {
  }

  save() {

  }
}
