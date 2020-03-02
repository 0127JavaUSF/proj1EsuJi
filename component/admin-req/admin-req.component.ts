import { Component, OnInit } from '@angular/core';
import { LoginService, userPass } from 'src/app/service/login.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { EmpAdminreqService } from '/home/jiykong/proj1/src/app/service/emp-admin-req.service';

@Component({
  selector: 'app-admin-req',
  templateUrl: './admin-req.component.html',
  styleUrls: ['./admin-req.component.css']
})
export class AdminReqComponent implements OnInit {

  id:number;
  types:number;
  
  stillLoggedin = {
    a: ' ',
    b: ' ',
    confirm: 'true'
  };
  
  constructor(private loginserve: LoginService, private http:HttpClient, private route:Router,private serveReq: EmpAdminreqService) { }

  ngOnInit(): void {
    this.http.get<userPass>('http://localhost:8080/Ji_Test/ERServlet?reqType=0',{withCredentials:true}).subscribe(data => {
      if( data.confirm === 'false'){
        this.route.navigate(['login']);
      }

      else{

        if( data.username != '2'){
          this.route.navigate(['emp']);
        }
      }
    }, 
      error => console.error("ERROR", error)
    );

  }

  async submit(){
    this.stillLoggedin = await this.serveReq.adminReq(this.id,this.types);
    this.route.navigate(['admin'])
  }  

  adHome(){
    this.route.navigate(['admin'])
  }

  reqHome(){
    this.route.navigate(['adminReq'])
  }

  logOut(){
    this.serveReq.logout();
    this.route.navigate(['login'])
  }

}
