import { Game } from './../model/game';

import {Injectable, group} from '@angular/core';
import {Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';

import {HttpClient, HttpHeaders} from '@angular/common/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { API_URL } from '../../config';


@Injectable()
export class GhostService {


  constructor(private http: HttpClient) {
  }



  findNextLetter(game: Game): Observable<Game> {
   let httpHeaders = new HttpHeaders();
   httpHeaders = httpHeaders.set('Content-Type', 'application/json; charset=utf-8');

    const options = {
      headers: httpHeaders
    };
    return this.http
   .post(API_URL + '/game', game, options)
   .map((response: any) => {
    console.log(response);
      return <Game>response;
    });
  }

}
