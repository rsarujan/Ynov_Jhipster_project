import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'livre',
        loadChildren: () => import('./livre/livre.module').then(m => m.ProBiblioLivreModule),
      },
      {
        path: 'auteur',
        loadChildren: () => import('./auteur/auteur.module').then(m => m.ProBiblioAuteurModule),
      },
      {
        path: 'emplacement',
        loadChildren: () => import('./emplacement/emplacement.module').then(m => m.ProBiblioEmplacementModule),
      },
      {
        path: 'theme',
        loadChildren: () => import('./theme/theme.module').then(m => m.ProBiblioThemeModule),
      },
      {
        path: 'exemplaire',
        loadChildren: () => import('./exemplaire/exemplaire.module').then(m => m.ProBiblioExemplaireModule),
      },
      {
        path: 'emprunt',
        loadChildren: () => import('./emprunt/emprunt.module').then(m => m.ProBiblioEmpruntModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class ProBiblioEntityModule {}
