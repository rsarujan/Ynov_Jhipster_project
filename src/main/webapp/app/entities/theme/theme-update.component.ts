import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITheme, Theme } from 'app/shared/model/theme.model';
import { ThemeService } from './theme.service';
import { ILivre } from 'app/shared/model/livre.model';
import { LivreService } from 'app/entities/livre/livre.service';

@Component({
  selector: 'jhi-theme-update',
  templateUrl: './theme-update.component.html',
})
export class ThemeUpdateComponent implements OnInit {
  isSaving = false;
  livres: ILivre[] = [];

  editForm = this.fb.group({
    id: [],
    theme: [null, [Validators.required, Validators.maxLength(50)]],
    livres: [],
  });

  constructor(
    protected themeService: ThemeService,
    protected livreService: LivreService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ theme }) => {
      this.updateForm(theme);

      this.livreService.query().subscribe((res: HttpResponse<ILivre[]>) => (this.livres = res.body || []));
    });
  }

  updateForm(theme: ITheme): void {
    this.editForm.patchValue({
      id: theme.id,
      theme: theme.theme,
      livres: theme.livres,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const theme = this.createFromForm();
    if (theme.id !== undefined) {
      this.subscribeToSaveResponse(this.themeService.update(theme));
    } else {
      this.subscribeToSaveResponse(this.themeService.create(theme));
    }
  }

  private createFromForm(): ITheme {
    return {
      ...new Theme(),
      id: this.editForm.get(['id'])!.value,
      theme: this.editForm.get(['theme'])!.value,
      livres: this.editForm.get(['livres'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITheme>>): void {
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
