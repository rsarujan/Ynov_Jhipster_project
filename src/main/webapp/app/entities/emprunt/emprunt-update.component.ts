import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IEmprunt, Emprunt } from 'app/shared/model/emprunt.model';
import { EmpruntService } from './emprunt.service';
import { IExemplaire } from 'app/shared/model/exemplaire.model';
import { ExemplaireService } from 'app/entities/exemplaire/exemplaire.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

type SelectableEntity = IExemplaire | IUser;

@Component({
  selector: 'jhi-emprunt-update',
  templateUrl: './emprunt-update.component.html',
})
export class EmpruntUpdateComponent implements OnInit {
  isSaving = false;
  exemplaires: IExemplaire[] = [];
  users: IUser[] = [];
  dateEmpruntDp: any;

  editForm = this.fb.group({
    id: [],
    dateEmprunt: [null, [Validators.required]],
    exemplaire: [],
    user: [],
  });

  constructor(
    protected empruntService: EmpruntService,
    protected exemplaireService: ExemplaireService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ emprunt }) => {
      this.updateForm(emprunt);

      this.exemplaireService
        .query({ filter: 'emprunt-is-null' })
        .pipe(
          map((res: HttpResponse<IExemplaire[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IExemplaire[]) => {
          if (!emprunt.exemplaire || !emprunt.exemplaire.id) {
            this.exemplaires = resBody;
          } else {
            this.exemplaireService
              .find(emprunt.exemplaire.id)
              .pipe(
                map((subRes: HttpResponse<IExemplaire>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IExemplaire[]) => (this.exemplaires = concatRes));
          }
        });

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));
    });
  }

  updateForm(emprunt: IEmprunt): void {
    this.editForm.patchValue({
      id: emprunt.id,
      dateEmprunt: emprunt.dateEmprunt,
      exemplaire: emprunt.exemplaire,
      user: emprunt.user,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const emprunt = this.createFromForm();
    if (emprunt.id !== undefined) {
      this.subscribeToSaveResponse(this.empruntService.update(emprunt));
    } else {
      this.subscribeToSaveResponse(this.empruntService.create(emprunt));
    }
  }

  private createFromForm(): IEmprunt {
    return {
      ...new Emprunt(),
      id: this.editForm.get(['id'])!.value,
      dateEmprunt: this.editForm.get(['dateEmprunt'])!.value,
      exemplaire: this.editForm.get(['exemplaire'])!.value,
      user: this.editForm.get(['user'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmprunt>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
