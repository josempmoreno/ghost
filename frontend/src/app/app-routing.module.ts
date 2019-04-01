import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import { GameComponent } from './pages/game/game.component';
import { HomeComponent } from './pages/home/home.component';

@NgModule({
  imports: [
    RouterModule.forRoot([
        {
          path: '',
          component: HomeComponent
        },
        {
          path: 'game',
          component: GameComponent,
        }
      ]
    )
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
