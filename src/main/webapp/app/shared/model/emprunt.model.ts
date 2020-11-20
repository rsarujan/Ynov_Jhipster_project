import { Moment } from 'moment';
import { IExemplaire } from 'app/shared/model/exemplaire.model';
import { IUser } from 'app/core/user/user.model';

export interface IEmprunt {
  id?: number;
  dateEmprunt?: Moment;
  exemplaire?: IExemplaire;
  user?: IUser;
}

export class Emprunt implements IEmprunt {
  constructor(public id?: number, public dateEmprunt?: Moment, public exemplaire?: IExemplaire, public user?: IUser) {}
}
