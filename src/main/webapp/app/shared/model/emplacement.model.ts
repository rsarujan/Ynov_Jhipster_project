export interface IEmplacement {
  id?: number;
  nomEmplacement?: string;
}

export class Emplacement implements IEmplacement {
  constructor(public id?: number, public nomEmplacement?: string) {}
}
