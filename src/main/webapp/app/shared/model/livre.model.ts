import { IEmplacement } from 'app/shared/model/emplacement.model';
import { IExemplaire } from 'app/shared/model/exemplaire.model';
import { ITheme } from 'app/shared/model/theme.model';
import { IAuteur } from 'app/shared/model/auteur.model';

export interface ILivre {
  id?: number;
  titre?: string;
  description?: string;
  isbn?: string;
  code?: string;
  emplacement?: IEmplacement;
  exemplaires?: IExemplaire[];
  themes?: ITheme[];
  auteurs?: IAuteur[];
}

export class Livre implements ILivre {
  constructor(
    public id?: number,
    public titre?: string,
    public description?: string,
    public isbn?: string,
    public code?: string,
    public emplacement?: IEmplacement,
    public exemplaires?: IExemplaire[],
    public themes?: ITheme[],
    public auteurs?: IAuteur[]
  ) {}
}
