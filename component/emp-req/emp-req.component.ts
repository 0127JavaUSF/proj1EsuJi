import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginService, userPass} from 'src/app/service/login.service'
import { Router } from '@angular/router';
import { EmpAdminreqService } from '/home/jiykong/proj1/src/app/service/emp-admin-req.service';

@Component({
  selector: 'app-emp-req',
  templateUrl: './emp-req.component.html',
  styleUrls: ['./emp-req.component.css']
})

export class EmpReqComponent implements OnInit {
  
  Amount:number;
  Description:string;
  type:number;
  Url:string;

  
  stillLoggedin = {
    a: ' ',
    b: ' ',
    confirm: 'true'
  };
  
  constructor(private loginserve: LoginService, private http:HttpClient, private route:Router, private serveReq: EmpAdminreqService) { }

  ngOnInit(): void {
    this.http.get<userPass>('http://localhost:8080/Ji_Test/ERServlet?reqType=0',{withCredentials:true}).subscribe(data => {
      if( data.confirm === 'false'){
        this.route.navigate(['login']);
      }

      else{

        if( data.username != '1'){
          this.route.navigate(['admin']);
        }
      }
    }, 
      error => console.error("ERROR", error)
    );

  }


  async submit(){
    this.stillLoggedin = await this.serveReq.empRea(this.Amount,this.Description,this.type,this.Url);
    this.route.navigate(['emp'])
  }  
  
  empHome(){
    this.route.navigate(['emp'])
  }
  reqHome(){
    this.route.navigate(['empReq'])
  }
  logOut(){
    this.serveReq.logout();
    this.route.navigate(['login'])
  }
}
