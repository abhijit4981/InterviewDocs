import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageonecompanyComponent } from './manageonecompany.component';

describe('ManageonecompanyComponent', () => {
  let component: ManageonecompanyComponent;
  let fixture: ComponentFixture<ManageonecompanyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageonecompanyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageonecompanyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
