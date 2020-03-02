import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


export interface userPass{
  username: string;
  password: string;
  confirm: string;
  name: string;
}


@Injectable({
  providedIn: 'root'
})


export class LoginService {

  constructor(private http: HttpClient) { }


  loginCheck(): any{
    const url = "http://localhost:8080/Ji_Test/ERServlet?reqType=0";
    this.http.get<userPass>(url,{withCredentials:true}).subscribe(data => {
      console.log(data.confirm);
      return data.confirm;
    })
  }

  loginConfirm(input1:string, input2:string): Promise<userPass> {
    const url = `http://localhost:8080/Ji_Test/ERServlet?reqType=1&user=${input1}&pass=${input2}`;
    return this.http.get<userPass>(url,{withCredentials:true}).toPromise();
  }
}
