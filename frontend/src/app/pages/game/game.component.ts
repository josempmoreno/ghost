import { GhostService } from './../../shared/services/ghost.service';
import { Game } from './../../shared/model/game';
import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl } from '@angular/forms';


@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {

  game: Game = {'letters': '', 'word': '', 'gameLevel': ''};
  gameForm: FormGroup;
  gameLevel: string;
  word = '' ;
  cpuWin = false;
  userWin = false;
  letters: FormControl;
  notWordFounds = false;
  constructor(private router: Router,
    private route: ActivatedRoute,
    private ghostService: GhostService) {

    this.route.queryParams.subscribe(params => {
        this.gameLevel = params['gameLevel'];
    });
  }

  ngOnInit() {
    this.createFormControls();
    this.createForm();
  }

  createFormControls() {
    this.letters = new FormControl('');
  }

  createForm() {
    this.gameForm = new FormGroup({
      letters: this.letters
    });
  }

  backToHome() {
    this.router.navigate(['/']);
  }


  findNextLetter() {
    if (this.gameForm.valid) {

      this.word = this.word.concat(this.letters.value);
      this.game.letters = this.word;
      this.game.gameLevel = this.gameLevel;
      this.letters.setValue('');

      this.ghostService.findNextLetter(this.game).subscribe(gameResponse => {
        if (gameResponse.letters == null && gameResponse.word != null) {
          // CPU Win
          this.word = gameResponse.word;
          this.cpuWin = true;
          if (gameResponse.word === '') {
            this.notWordFounds = true;
          }
        } else if (gameResponse.letters == null && gameResponse.word == null) {
          // User Win
          this.userWin = true;
        } else {
          // continue the game
          this.word = this.word + gameResponse.letters;
        }
      });

    }
  }
}
