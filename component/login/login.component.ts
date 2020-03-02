import { Component, OnInit } from '@angular/core';
import { LoginService, userPass} from 'src/app/service/login.service'
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  ngOnInit(): void {
    this.http.get<userPass>('http://localhost:8080/Ji_Test/ERServlet?reqType=0',{withCredentials:true}).subscribe(data => {
      if( data.confirm === 'false'){
        this.router.navigate(['login']);
      }

      else{

        if( data.username === '2'){
          this.router.navigate(['admin']);
        }
        if( data.username === '1'){
          this.router.navigate(['emp']);
        }

      }
    }, 
      error => console.error("ERROR", error)
    );

  }

  userpass = {
    user: ' ',
    pass: ' ',
    confirm: ' '
  }


  verify: Number = 1;

  checkLogin: string =  ' ';
  userName:string = ' ';
  passWord:string = ' ';

  constructor(private loginserve: LoginService, private router: Router, private http:HttpClient) { }

  async submit(){
    const result:userPass = await this.loginserve.loginConfirm(this.userName,this.passWord);
    if(result.confirm === 'yes'){

      this.userpass.confirm = result.confirm;
      this.userpass.user = result.username;
      this.userpass.pass = result.password;

      this.verify = 1;

      if(result.password == '1'){
        this.router.navigate(['emp']);
      }
      
      else{
        this.router.navigate(['admin']);
      }
    }

    else{
      this.verify = 0;
    }
  }

}
