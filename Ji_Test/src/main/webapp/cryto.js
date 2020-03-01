var Crypto = require('crypto-js')

Crypto.HmacSHA256("jiykong", "user").toString()
console.log("username " + Crypto.HmacSHA1("jiykong", "user").toString())
console.log("password " + Crypto.HmacSHA1("1234", "password").toString())
console.log("username " + Crypto.HmacSHA1("eobu", "user").toString())
console.log("password " + Crypto.HmacSHA1("5678", "password").toString())