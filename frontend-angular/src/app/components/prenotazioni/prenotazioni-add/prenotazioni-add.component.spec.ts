import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrenotazioniAddComponent } from './prenotazioni-add.component';

describe('PrenotazioniAddComponent', () => {
  let component: PrenotazioniAddComponent;
  let fixture: ComponentFixture<PrenotazioniAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrenotazioniAddComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PrenotazioniAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
