import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSingInComponent } from './admin-sing-in.component';

describe('AdminSingInComponent', () => {
  let component: AdminSingInComponent;
  let fixture: ComponentFixture<AdminSingInComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminSingInComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminSingInComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
