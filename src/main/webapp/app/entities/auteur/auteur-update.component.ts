import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAuteur, Auteur } from 'app/shared/model/auteur.model';
import { AuteurService } from './auteur.service';
import { ILivre } from 'app/shared/model/livre.model';
import { LivreService } from 'app/entities/livre/livre.service';

@Component({
  selector: 'jhi-auteur-update',
  templateUrl: './auteur-update.component.html',
})
export class AuteurUpdateComponent implements OnInit {
  isSaving = false;
  livres: ILivre[] = [];

  editForm = this.fb.group({
    id: [],
    auteur: [null, [Validators.required]],
    livres: [],
  });

  constructor(
    protected auteurService: AuteurService,
    protected livreService: LivreService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ auteur }) => {
      this.updateForm(auteur);

      this.livreService.query().subscribe((res: HttpResponse<ILivre[]>) => (this.livres = res.body || []));
    });
  }

  updateForm(auteur: IAuteur): void {
    this.editForm.patchValue({
      id: auteur.id,
      auteur: auteur.auteur,
      livres: auteur.livres,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const auteur = this.createFromForm();
    if (auteur.id !== undefined) {
      this.subscribeToSaveResponse(this.auteurService.update(auteur));
    } else {
      this.subscribeToSaveResponse(this.auteurService.create(auteur));
    }
  }

  private createFromForm(): IAuteur {
    return {
      ...new Auteur(),
      id: this.editForm.get(['id'])!.value,
      auteur: this.editForm.get(['auteur'])!.value,
      livres: this.editForm.get(['livres'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAuteur>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ILivre): any {
    return item.id;
  }

  getSelected(selectedVals: ILivre[], option: ILivre): ILivre {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
