import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


export interface sof{
  a:string;
  b:string;
  confirm:string;
}

@Injectable({
  providedIn: 'root'
})

export class EmpAdminreqService {

  constructor(private http: HttpClient ) { }

  logout(){

    this.http.get<sof>(`http://localhost:8080/Ji_Test/ERServlet?reqType=3`,{withCredentials:true}).subscribe(data => {
      if(data.confirm === 'false'){
        return false;
      }
      else{
        return true;
      }
    });
  }

  empRea(amount: number, desc: string,type:number, reciept: string){
    console.log("YOUOOUOOUOUOUO");
    return this.http.get<sof>(`http://localhost:8080/Ji_Test/ERServlet?reqType=4&reimb_amount=${amount}&reimb_description=${desc}&reimb_type_id=${type}&reimb_recept=${reciept}`,{withCredentials:true}).toPromise();
  }

  adminReq(id: number,status:number){
    console.log("DFADFAFDF");
    return this.http.get<sof>(`http://localhost:8080/Ji_Test/ERServlet?reqType=5&reimb_id=${id}&reimb_status=${status}`,{withCredentials:true}).toPromise();
  }
}
