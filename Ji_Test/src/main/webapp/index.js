const username = document.getElementById("username");
const password = document.getElementById("password");
const submit = document.getElementById("submit");

const imgSrc = document.getElementById("pokeImage");
const result = document.getElementById("result");

let updatePoke = (data) =>{
    const imgloc = data.sprites.front_default;
    imgSrc.src = imgloc;
}


let fetchSubmit = (url) => {
    const xhr = new XMLHttpRequest();

    xhr.open("GET", url);

    xhr.addEventListener('loadend',()=>{
        const response = xhr.response;
        result.innerHTML = response;
        //result.innerHTML = response;
        updatePoke(data);
    } );

    xhr.send();
}


submit.addEventListener("click",function() {
    const user = username.value;
    const pass = password.value;

    const url = `http://localhost:8080/Ji_Test/Employee?reqType=${1}&user=${user}&pass=${pass}`;

    fetchSubmit(url);
});
