import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrenotazioniGetComponent } from './prenotazioni-get.component';

describe('PrenotazioniGetComponent', () => {
  let component: PrenotazioniGetComponent;
  let fixture: ComponentFixture<PrenotazioniGetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrenotazioniGetComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PrenotazioniGetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
