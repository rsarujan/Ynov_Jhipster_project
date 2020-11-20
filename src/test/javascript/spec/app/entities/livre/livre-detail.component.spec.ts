import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProBiblioTestModule } from '../../../test.module';
import { LivreDetailComponent } from 'app/entities/livre/livre-detail.component';
import { Livre } from 'app/shared/model/livre.model';

describe('Component Tests', () => {
  describe('Livre Management Detail Component', () => {
    let comp: LivreDetailComponent;
    let fixture: ComponentFixture<LivreDetailComponent>;
    const route = ({ data: of({ livre: new Livre(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ProBiblioTestModule],
        declarations: [LivreDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(LivreDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LivreDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load livre on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.livre).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
