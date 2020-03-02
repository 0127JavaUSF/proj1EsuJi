import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { LoginService, userPass} from 'src/app/service/login.service'
import { reimbInfo, EmpAdminService } from 'src/app/service/emp-admin.service';
import { HeaderComponent } from 'src/app/component/header/header.component';
import { EmpAdminreqService } from '/home/jiykong/proj1/src/app/service/emp-admin-req.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})

export class AdminComponent implements OnInit {
  // result.getInt("reimb_id"), result.getInt("reimb_amount"), status, result.getString("reimb_description"), result.getTimestamp("reimb_submitted"), result.getString("reimb_receipt")

  reimbArr: reimbInfo[] = []

  stillLoggedin = {
    a: ' ',
    b: ' ',
    confirm: 'true'
  };

  verify = 0;
  name:string;

  constructor(private empadmin: EmpAdminService, private http:HttpClient, private route:Router, private serveReq:EmpAdminreqService) { }

  async getList(){
    this.reimbArr = await this.empadmin.employeeCheck();
    console.log(this.reimbArr);
  }

  ngOnInit(): void {
    this.http.get<userPass>('http://localhost:8080/Ji_Test/ERServlet?reqType=0',{withCredentials:true}).subscribe(data => {
      if( data.confirm === 'false'){
        this.route.navigate(['login']);
      }

      else{

        if( data.username != '2'){
          this.route.navigate(['emp']);
        }

        else{
          this.name = data.name;
          this.getList();
        }
      }
    }, 
      error => console.error("ERROR", error)
    );

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

