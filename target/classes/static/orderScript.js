        function sortedText(){
            var text = $('#ordenado').val();
            var link = document.getElementById("download-txt");
            $.ajax({
                type:"POST",
                url:"/api/ordem",
                data:text,
                contentType:"application/text; charset=utf-8",
                success:function(data){
                    console.log(data);
                    link.style.display="block";
                }
                
            });
        }
        function writeAuto(elemento){
          const textArray = elemento.innerHTML.split('');
          elemento.innerHTML = ' ';
          textArray.forEach(function(letra,i){
            setTimeout(function(){
              elemento.innerHTML += letra;
            }, 75 * i)
            
          });
          
        }
        const titulo = document.querySelector('.centralizado');
        writeAuto(titulo);