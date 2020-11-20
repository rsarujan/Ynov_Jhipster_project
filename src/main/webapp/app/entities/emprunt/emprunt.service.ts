import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEmprunt } from 'app/shared/model/emprunt.model';

type EntityResponseType = HttpResponse<IEmprunt>;
type EntityArrayResponseType = HttpResponse<IEmprunt[]>;

@Injectable({ providedIn: 'root' })
export class EmpruntService {
  public resourceUrl = SERVER_API_URL + 'api/emprunts';

  constructor(protected http: HttpClient) {}

  create(emprunt: IEmprunt): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(emprunt);
    return this.http
      .post<IEmprunt>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(emprunt: IEmprunt): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(emprunt);
    return this.http
      .put<IEmprunt>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEmprunt>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEmprunt[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(emprunt: IEmprunt): IEmprunt {
    const copy: IEmprunt = Object.assign({}, emprunt, {
      dateEmprunt: emprunt.dateEmprunt && emprunt.dateEmprunt.isValid() ? emprunt.dateEmprunt.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateEmprunt = res.body.dateEmprunt ? moment(res.body.dateEmprunt) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((emprunt: IEmprunt) => {
        emprunt.dateEmprunt = emprunt.dateEmprunt ? moment(emprunt.dateEmprunt) : undefined;
      });
    }
    return res;
  }
}
