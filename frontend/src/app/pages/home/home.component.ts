import { Component, OnInit } from '@angular/core';
import { Router, NavigationExtras } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  startGame(gameLevel: string) {
    const navigationExtras: NavigationExtras = {
      queryParams: {
          'gameLevel': gameLevel
       }
  };
    this.router.navigate(['/game'], navigationExtras) ;
  }

}
