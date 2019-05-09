import { Component, OnInit } from '@angular/core';
import { BookService } from './../book.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.sass']
})
export class BookComponent implements OnInit {

  builders: Object;
  constructor(private dataService: BookService) { }

  ngOnInit() {
	  this.dataService.getBuilders().subscribe(
	    data => {
	      console.log(this.builders);
	      this.builders = data;
        console.log(this.builders);
	    });
  }
}
