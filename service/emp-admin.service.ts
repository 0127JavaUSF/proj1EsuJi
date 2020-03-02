import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


export interface reimbInfo{
  reimb_id : number;
  reimb_amount : number;
  reimb_status : string;
  reimb_desc : string;
  reimb_Sub_Time : Date;
  reimb_App_Time : Date;
  rimb_rec : string;
  reimb_expType : string;
}

@Injectable({
  providedIn: 'root'
})

export class EmpAdminService {

  constructor(private http: HttpClient) { }

  employeeCheck(){
    const url = 'http://localhost:8080/Ji_Test/ERServlet?reqType=2';
    return this.http.get<reimbInfo[]>(url,{withCredentials:true}).toPromise();
  }
  
  adminCheck(){
    const url = 'http://localhost:8080/Ji_Test/ERServlet?reqType=2';
    return this.http.get<reimbInfo[]>(url,{withCredentials:true}).toPromise();
  }
}
