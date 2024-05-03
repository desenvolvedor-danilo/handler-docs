let form = document.getElementById("form");
let upload = document.getElementById("upload");
let link = document.getElementById("download");
let div = document.getElementById("modal-body");
form.addEventListener("submit",sendForm,false);
function sendForm(evt){
 let formaData, ajax, pct;
 formaData = new FormData(evt.target);
 ajax= new XMLHttpRequest();
 ajax.onreadystatechange = function(){
   if(ajax.readyState==4){
     form.reset();
     upload = ajax.response; 
     link.style.display="block";
     div.style.display="block";
   }else{
     upload.style.display="block";
   }
   ajax.upload.addEventListener('progress',function(evt){
     pct = Math.round((evt.loaded/evt.total)*100);
     upload.setAttribute("value",pct);
     
     
   },false);
 }
 ajax.open('POST',"/api/arquivo")
 ajax.send(formaData);
 
 }