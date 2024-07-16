import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MediciGetComponent } from './medici-get.component';

describe('MediciGetComponent', () => {
  let component: MediciGetComponent;
  let fixture: ComponentFixture<MediciGetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MediciGetComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MediciGetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
