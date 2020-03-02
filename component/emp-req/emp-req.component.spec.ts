import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpReqComponent } from './emp-req.component';

describe('EmpReqComponent', () => {
  let component: EmpReqComponent;
  let fixture: ComponentFixture<EmpReqComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmpReqComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmpReqComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
