import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) {
  }
  getBuilders() {
    const url = 'http://localhost:8888/builders';
    return this.http.get(url);
  }
}
