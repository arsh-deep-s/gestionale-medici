import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrenotazioniCancelComponent } from './prenotazioni-cancel.component';

describe('PrenotazioniCancelComponent', () => {
  let component: PrenotazioniCancelComponent;
  let fixture: ComponentFixture<PrenotazioniCancelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrenotazioniCancelComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PrenotazioniCancelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
